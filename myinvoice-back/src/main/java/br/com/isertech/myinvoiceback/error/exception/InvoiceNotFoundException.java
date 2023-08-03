package br.com.isertech.myinvoiceback.error.exception;

import java.io.Serial;

public class InvoiceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvoiceNotFoundException(String message) {
        super(message);
    }

}
