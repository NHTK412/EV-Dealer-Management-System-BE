package com.example.evsalesmanagement.service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
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

        @Transactional
        public QuoteResponseDTO getQuoteById(Integer quoteId) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));
                return new QuoteResponseDTO(quote);
        }

        @Transactional
        public QuoteResponseDTO createQuote(QuoteRequestDTO quoteRequestDTO) {
                Quote quote = new Quote();

                convertDTOtoEnity(quoteRequestDTO, quote);

                quoteRepository.save(quote);
                return new QuoteResponseDTO(quote);
        }

        @Transactional
        public QuoteResponseDTO deleteQuote(Integer quoteId) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));
                quoteRepository.delete(quote);
                return new QuoteResponseDTO(quote);
        }

        @Transactional
        public QuoteResponseDTO updateQuote(Integer quoteId, QuoteRequestDTO quoteRequestDTO) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));
                // if (quote.getStatus() != "Padding") {
                // throw new ConflictException("Trạng thái hiện tại không được sửa");
                // }
                quote.getQuotationDetails().clear();

                convertDTOtoEnity(quoteRequestDTO, quote);

                quoteRepository.save(quote);

                return new QuoteResponseDTO(quote);
        }

        @Transactional
        public QuoteResponseDTO updateStatusQuote(Integer quoteId, String status) {
                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));

                quote.setStatus(status);

                quoteRepository.save(quote);

                return new QuoteResponseDTO(quote);

        }

        private void convertDTOtoEnity(QuoteRequestDTO quoteRequestDTO, Quote quote) {

                quote.setEmployee(employeeRepository
                                .findById(quoteRequestDTO.getEmployeeId())
                                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không tồn tại")));

                quote.setStatus(quoteRequestDTO.getStatus());

                List<Integer> vehicleTypeDetailIds = quoteRequestDTO.getQuotationDetailRequestDTOs().stream()
                                .map((quotationDetailRequestDTO) -> quotationDetailRequestDTO.getVehicleTypeDetailId())
                                .toList();

                List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
                                .getAllByIdWithVehicleType(vehicleTypeDetailIds);

                Map<Integer, VehicleTypeDetail> vehicleTypeDetailMap = vehicleTypeDetails
                                .stream()
                                .collect(Collectors.toMap(
                                                vehicleTypeDetail -> vehicleTypeDetail.getVehicleTypeDetailId(),
                                                vehicleTypeDetail -> vehicleTypeDetail));

                for (QuotationDetailRequestDTO quotationDetailRequestDTO : quoteRequestDTO
                                .getQuotationDetailRequestDTOs()) {
                        QuotationDetail quotationDetail = new QuotationDetail();

                        quotationDetail.setQuantity(quotationDetailRequestDTO.getQuantity());

                        quotationDetail.setDiscount(quotationDetailRequestDTO.getDiscount());

                        quotationDetail.setRegistrationTax(quotationDetailRequestDTO.getRegistrationTax());

                        quotationDetail.setLicensePlateFee(quotationDetailRequestDTO.getLicensePlateFee());

                        quotationDetail.setRegistrartionFee(quotationDetailRequestDTO.getRegistrartionFee());

                        quotationDetail.setCompulsoryInsurance(quotationDetailRequestDTO.getCompulsoryInsurance());

                        quotationDetail.setMaterialInsurance(quotationDetailRequestDTO.getMaterialInsurance());

                        quotationDetail.setRoadMaintenanceMees(quotationDetailRequestDTO.getRoadMaintenanceMees());

                        quotationDetail.setVehicleRegistrationServiceFee(
                                        quotationDetailRequestDTO.getVehicleRegistrationServiceFee());

                        quotationDetail.setDiscountPercentage(quotationDetailRequestDTO.getDiscountPercentage());

                        quotationDetail.setWholesalePrice(quotationDetailRequestDTO.getWholesalePrice());

                        List<Promotion> promotions = promotionRepository.getPromotionsByAgencyIdAndVehicleDetailsId(
                                        quote.getEmployee().getAgency().getAgencyId(),
                                        quotationDetailRequestDTO.getVehicleTypeDetailId());

                        BigDecimal discountPercent = promotions.stream().findFirst().get().getDiscountPercent();

                        // Tính giá gốc
                        BigDecimal basePrice = quotationDetailRequestDTO.getWholesalePrice();

                        // Tính tổng tiền cuối cùng
                        BigDecimal totalAmount = basePrice
                                        .add(quotationDetailRequestDTO.getRegistrationTax())
                                        .add(quotationDetailRequestDTO.getLicensePlateFee())
                                        .add(quotationDetailRequestDTO.getRegistrartionFee())
                                        .add(quotationDetailRequestDTO.getCompulsoryInsurance())
                                        .add(quotationDetailRequestDTO.getMaterialInsurance())
                                        .add(quotationDetailRequestDTO.getRoadMaintenanceMees())
                                        .add(quotationDetailRequestDTO.getVehicleRegistrationServiceFee());
                        // .subtract(quotationDetailRequestDTO.getDiscount())
                        // .subtract(quotationDetailRequestDTO.getDiscountValue());

                        quotationDetail.setTotalAmount(totalAmount);

                        // double totalAmount = (quotationDetailRequestDTO.getWholesalePrice()
                        // * quotationDetailRequestDTO.getQuantity())
                        // - quotationDetailRequestDTO.getDiscount()
                        // - (quotationDetailRequestDTO.getWholesalePrice()
                        // * quotationDetailRequestDTO.getDiscountPercentage() / 100)
                        // + quotationDetailRequestDTO.getRegistrationTax()
                        // + quotationDetailRequestDTO.getLicensePlateFee()
                        // + quotationDetailRequestDTO.getRegistrartionFee()
                        // + quotationDetailRequestDTO.getCompulsoryInsurance()
                        // + quotationDetailRequestDTO.getMaterialInsurance()
                        // + quotationDetailRequestDTO.getRoadMaintenanceMees()
                        // + quotationDetailRequestDTO.getVehicleRegistrationServiceFee();

                        // Gan quan he
                        quotationDetail.setQuote(quote);

                        quotationDetail
                                        .setVehicleTypeDetail(vehicleTypeDetailMap
                                                        .get(quotationDetailRequestDTO.getVehicleTypeDetailId()));

                        quote.getQuotationDetails().add(quotationDetail);
                }

                BigDecimal total = quote.getQuotationDetails()
                                .stream()
                                .map(QuotationDetail::getTotalAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                quote.setTotalAmount(total);

        }
}