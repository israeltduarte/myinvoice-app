package br.com.isertech.myinvoice.myinvoiceback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private String description;
    private Long hours;
    private Long hourRate;
    private Long total;

}
