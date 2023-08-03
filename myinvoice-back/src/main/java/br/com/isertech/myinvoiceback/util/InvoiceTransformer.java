package br.com.isertech.myinvoiceback.util;

import br.com.isertech.myinvoiceback.dto.InvoiceDTO;
import br.com.isertech.myinvoiceback.entity.Invoice;

public class InvoiceTransformer {

    private InvoiceTransformer() {}

    public static Invoice fromDTO(InvoiceDTO dto) {
        return Invoice.builder()
                .number(dto.getNumber())
                .date(dto.getDate())
                .total(dto.getTotal())
                .isPublished(dto.isPublished())
                .currency(dto.getCurrency())
                .build();
    }
}
