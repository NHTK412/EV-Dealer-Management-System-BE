package com.example.evsalesmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.dto.quote.QuoteSummaryDTO;
import com.example.evsalesmanagement.enums.QuoteStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.CustomerRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.PromotionRepository;
import com.example.evsalesmanagement.repository.QuoteRepository;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

import jakarta.transaction.Transactional;

@Service
public class QuoteService {

        @Autowired
        QuoteRepository quoteRepository;

        @Autowired
        EmployeeRepository employeeRepository;

        @Autowired
        VehicleTypeDetailRepository vehicleTypeDetailRepository;

        @Autowired
        PromotionRepository promotionRepository;

        @Autowired
        CustomerRepository customerRepository;

        @Cacheable(value = "quote", key = "#quoteId")
        @Transactional
        public QuoteResponseDTO getQuoteById(Integer quoteId) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Quote not found with ID: " + quoteId));
                return new QuoteResponseDTO(quote);
        }

        @Transactional
        public QuoteResponseDTO createQuote(Integer emloyeeId, QuoteRequestDTO quoteRequestDTO) {
                Quote quote = new Quote();

                quote.setEmployee(employeeRepository
                                .findById(emloyeeId)
                                .orElseThrow(() -> new ResourceNotFoundException("EmployeeId not found")));

                convertDTOtoEnity(quoteRequestDTO, quote);

                quoteRepository.save(quote);

                return new QuoteResponseDTO(quote);
        }

        @CacheEvict(value = "quote", key = "#quoteId")
        @Transactional
        public QuoteResponseDTO deleteQuote(Integer quoteId) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Quote not found with ID: " + quoteId));
                quoteRepository.delete(quote);
                return new QuoteResponseDTO(quote);
        }

        @CachePut(value = "quote", key = "#quoteId")
        @Transactional
        public QuoteResponseDTO updateQuote(Integer quoteId, QuoteRequestDTO quoteRequestDTO) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Quote not found with ID: " + quoteId));
                quote.getQuotationDetails().clear();

                convertDTOtoEnity(quoteRequestDTO, quote);

                quoteRepository.save(quote);

                return new QuoteResponseDTO(quote);
        }

        @CachePut(value = "quote", key = "#quoteId")
        @Transactional
        public QuoteResponseDTO updateStatusQuote(Integer quoteId, QuoteStatusEnum status) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Quote not found with ID: " + quoteId));

                quote.setStatus(status);

                quoteRepository.save(quote);

                return new QuoteResponseDTO(quote);

        }

        private BigDecimal n(BigDecimal value) {
                return value == null ? BigDecimal.ZERO : value;
        }

        private void convertDTOtoEnity(QuoteRequestDTO quoteRequestDTO, Quote quote) {

                // Set customer
                quote.setCustomer(customerRepository
                                .findById(quoteRequestDTO.getCustomerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found")));

                quote.setStatus(quoteRequestDTO.getStatus());

                // Lấy danh sách IDs
                List<Integer> vehicleTypeDetailIds = quoteRequestDTO.getQuotationDetailRequestDTOs().stream()
                                .map(QuotationDetailRequestDTO::getVehicleTypeDetailId)
                                .toList();

                List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
                                .getAllByIdWithVehicleType(vehicleTypeDetailIds);

                Map<Integer, VehicleTypeDetail> vehicleTypeDetailMap = vehicleTypeDetails.stream()
                                .collect(Collectors.toMap(
                                                VehicleTypeDetail::getVehicleTypeDetailId,
                                                v -> v));

                if (vehicleTypeDetailMap.size() != vehicleTypeDetailIds.size()) {
                        throw new ResourceNotFoundException("Vehicle Type Detail Not Found");
                }

                for (QuotationDetailRequestDTO dto : quoteRequestDTO.getQuotationDetailRequestDTOs()) {

                        QuotationDetail quotationDetail = new QuotationDetail();

                        quotationDetail.setQuantity(dto.getQuantity());
                        quotationDetail.setRegistrationTax(n(dto.getRegistrationTax()));
                        quotationDetail.setLicensePlateFee(n(dto.getLicensePlateFee()));
                        quotationDetail.setRegistrartionFee(n(dto.getRegistrartionFee()));
                        quotationDetail.setCompulsoryInsurance(n(dto.getCompulsoryInsurance()));
                        quotationDetail.setMaterialInsurance(n(dto.getMaterialInsurance()));
                        quotationDetail.setRoadMaintenanceMees(n(dto.getRoadMaintenanceMees()));
                        quotationDetail.setVehicleRegistrationServiceFee(n(dto.getVehicleRegistrationServiceFee()));

                        // Giá gốc
                        BigDecimal basePrice = vehicleTypeDetailMap
                                        .get(dto.getVehicleTypeDetailId())
                                        .getPrice();

                        // Lấy promotions
                        List<Promotion> promotions = promotionRepository
                                        .getPromotionsByAgencyIdAndVehicleDetailsId(
                                                        quote.getEmployee().getAgency().getAgencyId(),
                                                        dto.getVehicleTypeDetailId());

                        // Tính số tiền giảm giá thực tế
                        Map<Promotion, BigDecimal> actualDiscountMap = promotions.stream()
                                        .collect(Collectors.toMap(
                                                        promotion -> promotion,
                                                        promotion -> {

                                                                if ("PERCENTAGE".equals(promotion.getPromotionType())) {
                                                                        BigDecimal percent = n(
                                                                                        promotion.getDiscountPercent());
                                                                        return basePrice.multiply(percent)
                                                                                        .divide(BigDecimal
                                                                                                        .valueOf(100));
                                                                }

                                                                return n(promotion.getDiscountAmount());
                                                        }));

                        // Tìm mức giảm giá cao nhất (best discount)
                        Map.Entry<Promotion, BigDecimal> maxDiscountPromotion = actualDiscountMap.entrySet().stream()
                                        .max(Map.Entry.comparingByValue())
                                        .orElse(null);

                        BigDecimal discountAmount;
                        BigDecimal priceAfterDiscount;

                        if (maxDiscountPromotion != null) {
                                discountAmount = n(maxDiscountPromotion.getValue());
                                priceAfterDiscount = basePrice.subtract(discountAmount);

                                Promotion best = maxDiscountPromotion.getKey();

                                if ("PERCENTAGE".equals(best.getPromotionType())) {
                                        quotationDetail.setDiscountPercentage(n(best.getDiscountPercent()));
                                } else {
                                        quotationDetail.setDiscount(n(best.getDiscountAmount()));
                                }

                        } else {
                                discountAmount = BigDecimal.ZERO;
                                priceAfterDiscount = basePrice;
                                quotationDetail.setDiscount(BigDecimal.ZERO);
                                quotationDetail.setDiscountPercentage(BigDecimal.ZERO);
                        }

                        quotationDetail.setPrice(basePrice);

                        // Tính tổng: (giá sau giảm + các phí) × số lượng
                        BigDecimal totalAmount = priceAfterDiscount
                                        .add(n(dto.getRegistrationTax()))
                                        .add(n(dto.getLicensePlateFee()))
                                        .add(n(dto.getRegistrartionFee()))
                                        .add(n(dto.getCompulsoryInsurance()))
                                        .add(n(dto.getMaterialInsurance()))
                                        .add(n(dto.getRoadMaintenanceMees()))
                                        .add(n(dto.getVehicleRegistrationServiceFee()));

                        // Nhân số lượng
                        totalAmount = totalAmount.multiply(BigDecimal.valueOf(dto.getQuantity()));

                        quotationDetail.setTotalAmount(totalAmount);

                        // Set quan hệ
                        quotationDetail.setQuote(quote);
                        quotationDetail.setVehicleTypeDetail(
                                        vehicleTypeDetailMap.get(dto.getVehicleTypeDetailId()));

                        quote.getQuotationDetails().add(quotationDetail);
                }

                // Tổng tiền toàn bộ quote
                BigDecimal total = quote.getQuotationDetails().stream()
                                .map(QuotationDetail::getTotalAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                quote.setTotalAmount(total);
        }

        @Transactional
        public List<QuoteSummaryDTO> getAllQuotes(Pageable pageable) {
                Page<Quote> quotes = quoteRepository.findAll(pageable);

                return quotes.stream()
                                .map((quote) -> new QuoteSummaryDTO(quote))
                                .toList();
        }

}