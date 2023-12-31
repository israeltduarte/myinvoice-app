package br.com.isertech.myinvoice.myinvoiceback.repository;

import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>, JpaSpecificationExecutor<Company> {

    List<Company> findAllByUserId(String id);

}
