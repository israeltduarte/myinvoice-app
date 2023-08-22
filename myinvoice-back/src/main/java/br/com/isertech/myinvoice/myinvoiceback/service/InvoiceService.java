package br.com.isertech.myinvoice.myinvoiceback.service;

import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.InvoiceDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import br.com.isertech.myinvoice.myinvoiceback.entity.Invoice;
import br.com.isertech.myinvoice.myinvoiceback.entity.Item;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.InvoiceNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.repository.InvoiceRepository;
import br.com.isertech.myinvoice.myinvoiceback.util.InvoiceTransformer;
import br.com.isertech.myinvoice.myinvoiceback.util.ItemTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyService companyService;
    private final ClientService clientService;

    public InvoiceService(InvoiceRepository invoiceRepository, CompanyService companyService, ClientService clientService) {
        this.invoiceRepository = invoiceRepository;
        this.companyService = companyService;
        this.clientService = clientService;
    }

    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        log.info("InvoiceService - getAllInvoices() - List<Invoice>={}", invoices);
        return invoices;
    }

    public Invoice addInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = InvoiceTransformer.fromDTO(invoiceDTO);
        Company company = companyService.getCompanyById(invoiceDTO.getCompanyId());
        Client client = clientService.getClientById(invoiceDTO.getClientId());
        List<Item> items = ItemTransformer.fromList(invoiceDTO.getItems(), invoice);
        invoice.setCompany(company);
        invoice.setClient(client);
        invoice.setItems(items);
        invoice = invoiceRepository.save(invoice);
        log.info("InvoiceService - addInvoice() - Invoice={}", invoice);
        return invoice;
    }

    public Invoice getInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException(Messages.INVOICE_NOT_FOUND));
        log.info("InvoiceService - getInvoiceById() - Invoice={}", invoice);
        return invoice;
    }

    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
        log.info("InvoiceService - deleteInvoice()");
    }
}
