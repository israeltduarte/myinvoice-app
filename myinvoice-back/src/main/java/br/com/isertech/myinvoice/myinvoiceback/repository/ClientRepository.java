package br.com.isertech.myinvoice.myinvoiceback.repository;

import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface ClientRepository extends JpaRepository<Client, String> {
public interface ClientRepository extends JpaRepository<Client, Long> {
}
