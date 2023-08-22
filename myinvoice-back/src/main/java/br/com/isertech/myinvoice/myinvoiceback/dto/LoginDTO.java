package br.com.isertech.myinvoice.myinvoiceback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}

