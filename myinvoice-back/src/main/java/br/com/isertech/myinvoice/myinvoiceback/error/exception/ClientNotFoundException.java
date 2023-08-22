package br.com.isertech.myinvoice.myinvoiceback.error.exception;

import java.io.Serial;

public class ClientNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message);
    }
}
