package br.com.isertech.myinvoice.myinvoiceback.repository;

import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>, JpaSpecificationExecutor<Client> {

    List<Client> findAllByUserId(String id);
}
