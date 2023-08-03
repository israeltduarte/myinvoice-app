package br.com.isertech.myinvoiceback.repository;

import br.com.isertech.myinvoiceback.entity.Client;
import br.com.isertech.myinvoiceback.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface InvoiceRepository extends JpaRepository<Invoice, String> {
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
