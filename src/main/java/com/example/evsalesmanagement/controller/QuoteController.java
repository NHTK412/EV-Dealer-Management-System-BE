package com.example.evsalesmanagement.controller;

import org.springframework.data.domain.Pageable;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.XmlUtils;
import org.docx4j.wml.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.Docx4J;
import jakarta.xml.bind.JAXBElement;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

// import java.io.ByteArrayOutputStream;
// import java.io.FileInputStream;
// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.docx4j.Docx4J;
// import org.docx4j.XmlUtils;
// import org.docx4j.model.datastorage.migration.VariablePrepare;
// import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
// import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
// import org.docx4j.wml.Tbl;
// import org.docx4j.wml.Tr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailResponseDTO;
// import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailResponseDTO;
import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.dto.quote.QuoteSummaryDTO;
import com.example.evsalesmanagement.enums.QuoteStatusEnum;
import com.example.evsalesmanagement.security.CustomerUserDetails;
import com.example.evsalesmanagement.service.QuoteService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("quote")
public class QuoteController {

    @Value("${image-upload-path}")
    private String imagePathString;

    @Autowired
    QuoteService quoteService;

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> getQuoteById(@RequestParam Integer quoteId) {
        QuoteResponseDTO quoteResponseDTO = quoteService.getQuoteById(quoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> createQuote(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestBody QuoteRequestDTO quoteRequestDTO) {

        Integer employeeId = customerUserDetails.getEmployeeId();
        QuoteResponseDTO quoteResponseDTO = quoteService.createQuote(employeeId, quoteRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @DeleteMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> deleteQuote(@PathVariable Integer quoteId) {
        QuoteResponseDTO quoteResponseDTO = quoteService.deleteQuote(quoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PutMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> updateQuote(@PathVariable Integer quoteId,
            @RequestBody QuoteRequestDTO quoteRequestDTO) {
        QuoteResponseDTO quoteResponseDTO = quoteService.updateQuote(quoteId, quoteRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PatchMapping("/{quoteId}/{status}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> updateStatusQuote(@PathVariable Integer quoteId,
            @PathVariable QuoteStatusEnum status) {
        QuoteResponseDTO quoteResponseDTO = quoteService.updateStatusQuote(quoteId, status);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/{quoteId}/down")
    public void downloadQuotationPdf(@PathVariable Integer quoteId, HttpServletResponse response) throws Exception {

        // ========== 1. Lấy dữ liệu ==========
        QuoteResponseDTO quote = quoteService.getQuoteById(quoteId);

        // ========== 2. Load template ==========
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(
                new FileInputStream("src/main/resources/templates/TemplateMau.docx"));
        MainDocumentPart mainPart = wordMLPackage.getMainDocumentPart();

        // ========== 3. FIX lỗi placeholder bị split run ==========
        String xml = mainPart.getXML().replaceAll("</w:t>\\s*<w:t>", "");
        Object newDoc = XmlUtils.unmarshalString(xml);
        mainPart.setJaxbElement((org.docx4j.wml.Document) newDoc);

        // ========== 4. Replace dữ liệu tĩnh ==========
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("customerName", quote.getCustomerName());
        placeholders.put("customerPhone", quote.getCustomerPhone());
        placeholders.put("customerEmail", quote.getCustomerEmail());
        placeholders.put("employeeName", quote.getEmployeeName());
        placeholders.put("employeePhoneNumber", quote.getEmployeePhoneNumber());
        placeholders.put("employeeEmail", quote.getEmployeeEmail());
        placeholders.put("quoteId", String.valueOf(quote.getQuoteId()));
        placeholders.put("status", quote.getStatus().getDisplayName());
        placeholders.put("quoteDate", LocalDate.now().toString());
        placeholders.put("totalAmount", String.valueOf(quote.getTotalAmount()));

        for (var e : placeholders.entrySet()) {
            xml = xml.replace("${" + e.getKey() + "}", e.getValue());
        }
        mainPart.setJaxbElement((org.docx4j.wml.Document) XmlUtils.unmarshalString(xml));

        // ========== 5. Merge bảng chi tiết ==========
        List<Object> tables = mainPart.getJAXBNodesViaXPath("//w:tbl", true);
        if (!tables.isEmpty()) {

            Object tblObj = tables.get(0);
            if (tblObj instanceof JAXBElement<?> jaxb) {
                tblObj = jaxb.getValue();
            }
            Tbl table = (Tbl) tblObj;

            List<Object> rows = table.getContent();
            Tr templateRow = null;

            // tìm row chứa placeholder
            for (Object rowObj : rows) {
                Tr r = (Tr) ((rowObj instanceof JAXBElement)
                        ? ((JAXBElement<?>) rowObj).getValue()
                        : rowObj);

                if (r.toString().contains("${vehicleTypeName}")) {
                    templateRow = r;
                    break;
                }
            }

            if (templateRow != null) {

                for (int i = 0; i < quote.getQuotationDetails().size(); i++) {
                    QuotationDetailResponseDTO detail = quote.getQuotationDetails().get(i);

                    Tr workingRow = XmlUtils.deepCopy(templateRow);

                    // Marshal row → string
                    String rowXml = XmlUtils.marshaltoString(workingRow, true);

                    Map<String, String> rowVars = Map.ofEntries(
                            Map.entry("index", String.valueOf(i + 1)),
                            Map.entry("vehicleTypeName", detail.getVehicleTypeName()),
                            Map.entry("vehicleConfiguration", detail.getVehicleConfiguration()),
                            Map.entry("vehicleColor", detail.getVehicleColor()),
                            Map.entry("vehicleVersion", detail.getVehicleVersion()),
                            Map.entry("vehicleFeatures", detail.getVehicleFeatures()),
                            Map.entry("quantity", String.valueOf(detail.getQuantity())),
                            Map.entry("vehiclePrice", String.valueOf(detail.getVehiclePrice())),
                            Map.entry("registrationTax", String.valueOf(detail.getRegistrationTax())),
                            Map.entry("licensePlateFee", String.valueOf(detail.getLicensePlateFee())),
                            Map.entry("registrartionFee", String.valueOf(detail.getRegistrartionFee())),
                            Map.entry("compulsoryInsurance", String.valueOf(detail.getCompulsoryInsurance())),
                            Map.entry("materialInsurance", String.valueOf(detail.getMaterialInsurance())),
                            Map.entry("roadMaintenanceMees", String.valueOf(detail.getRoadMaintenanceMees())),
                            Map.entry("vehicleRegistrationServiceFee",
                                    String.valueOf(detail.getVehicleRegistrationServiceFee())),
                            Map.entry("totalAmount", String.valueOf(detail.getTotalAmount())));

                    // Replace placeholder trong row
                    for (var e : rowVars.entrySet()) {
                        rowXml = rowXml.replace("${" + e.getKey() + "}", e.getValue());
                    }

                    Tr replacedRow = (Tr) XmlUtils.unmarshalString(rowXml);
                    table.getContent().add(replacedRow);
                }

                table.getContent().remove(templateRow);
            }
        }

        // ========== 6. To PDF ==========
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        Docx4J.toPDF(wordMLPackage, pdfOutputStream);
        byte[] pdfBytes = pdfOutputStream.toByteArray();

        // 1. Tạo thư mục static runtime
        String staticPath = new File(imagePathString).getAbsolutePath();
        File dir = new File(staticPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 2. Tạo file PDF trong static
        String fileName = "quotation_" + quote.getQuoteId() + ".pdf";
        File pdfFile = new File(dir, fileName);

        // 3. Ghi bytes xuống file
        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            fos.write(pdfBytes);
        }

        // ========== 7. Trả file ==========
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=quotation_" + quote.getQuoteId() + ".pdf");
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuoteSummaryDTO>>> getAllQuote(@RequestParam Integer page,
            @RequestParam Integer size) {
        // return new String();

        Pageable pageable = PageRequest.of(page - 1, size);

        List<QuoteSummaryDTO> quoteSummaryDTOs = quoteService.getAllQuotes(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteSummaryDTOs));

    }

    // @GetMapping("/employee")
    // public ResponseEntity<ApiResponse<

}
