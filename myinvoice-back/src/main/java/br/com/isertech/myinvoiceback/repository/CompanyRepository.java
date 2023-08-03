package br.com.isertech.myinvoiceback.repository;


import br.com.isertech.myinvoiceback.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllById(String userId);
}
