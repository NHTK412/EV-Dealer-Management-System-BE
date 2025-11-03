package com.example.evsalesmanagement.service;

import java.beans.Transient;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.repository.EmployeeRepository;
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

    @Transactional
    public QuoteResponseDTO getQuoteById(Integer quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));
        return new QuoteResponseDTO(quote);
    }

    @Transactional
    public QuoteResponseDTO createQuote(QuoteRequestDTO quoteRequestDTO) {
        Quote quote = convertDTOtoEnity(quoteRequestDTO);

        quoteRepository.save(quote);
        return new QuoteResponseDTO(quote);
    }

    private Quote convertDTOtoEnity(QuoteRequestDTO quoteRequestDTO) {
        Quote quote = new Quote();

        quote.setEmployee(employeeRepository
                .findById(quoteRequestDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không tồn tại")));

        quote.setStatus(quoteRequestDTO.getStatus());
        quote.setQuotationDetails(
                quoteRequestDTO.getQuotationDetailRequestDTOs()
                        .stream()
                        .map((quotationDetailRequestDTO) -> {
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

                            quotationDetail.setQuote(quote);

                            quotationDetail.setVehicleTypeDetail(vehicleTypeDetailRepository
                                    .findById(quotationDetailRequestDTO.getVehicleTypeDetailId())
                                    .orElseThrow(() -> new ResourceNotFoundException("Mã xe không tồn tại")));

                            return quotationDetail;

                        }).collect(Collectors.toList()));
        return quote;
    }
}
