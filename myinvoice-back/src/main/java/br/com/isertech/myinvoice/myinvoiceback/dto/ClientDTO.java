package br.com.isertech.myinvoice.myinvoiceback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    private String userId;
    private String name;
    private Long number;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String contact;
    private String exchange;

}
