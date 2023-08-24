package br.com.isertech.myinvoice.myinvoiceback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {

    private Long number;
    private LocalDate date;
    private String companyId;
    private Long clientId;
    private Long total;
    private String currency;
    private boolean isPublished;
    private List<ItemDTO> items;

}
