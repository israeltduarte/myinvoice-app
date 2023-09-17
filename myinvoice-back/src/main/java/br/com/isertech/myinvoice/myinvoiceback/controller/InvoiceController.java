package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.dto.InvoiceDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Invoice;
import br.com.isertech.myinvoice.myinvoiceback.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoices")
@Transactional
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<Page<Invoice>> getAllInvoices(Pageable pageable) {

        Page<Invoice> invoices = this.invoiceService.getAllInvoices(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }

    @PostMapping
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {

        Invoice invoice = this.invoiceService.addInvoice(invoiceDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String invoiceId) {

        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);

        return ResponseEntity.status(HttpStatus.OK).body(invoice);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String invoiceId) {

        this.invoiceService.deleteInvoice(invoiceId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllInvoices() {

        this.invoiceService.deleteAllInvoices();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
