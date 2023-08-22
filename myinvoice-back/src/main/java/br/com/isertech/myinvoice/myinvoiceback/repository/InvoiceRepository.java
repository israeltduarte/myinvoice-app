package br.com.isertech.myinvoice.myinvoiceback.repository;

import br.com.isertech.myinvoice.myinvoiceback.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface InvoiceRepository extends JpaRepository<Invoice, String> {
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
