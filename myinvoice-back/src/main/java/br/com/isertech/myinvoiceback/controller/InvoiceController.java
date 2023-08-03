package br.com.isertech.myinvoiceback.controller;

import br.com.isertech.myinvoiceback.dto.InvoiceDTO;
import br.com.isertech.myinvoiceback.entity.Invoice;
import br.com.isertech.myinvoiceback.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@Transactional
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = this.invoiceService.getAllInvoices();
        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }

    @PostMapping
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice invoice = this.invoiceService.addInvoice(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceId) {
        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);
        return ResponseEntity.status(HttpStatus.OK).body(invoice);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
        this.invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
