package br.com.isertech.myinvoiceback.service;

import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.CompanyDTO;
import br.com.isertech.myinvoiceback.entity.Company;
import br.com.isertech.myinvoiceback.error.exception.CompanyNotFoundException;
import br.com.isertech.myinvoiceback.repository.CompanyRepository;
import br.com.isertech.myinvoiceback.util.CompanyTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        log.info("CompanyService - getAllCompanies() - List<Company> = {}", companies);
        return companies;
    }

    public Company addCompany(CompanyDTO dto) {
        Company company = CompanyTransformer.fromDTO(dto);
        company = companyRepository.save(company);
        log.info("CompanyService - company saved");
        log.info("CompanyService - addCompany() - Company = {}", company);
        return company;
    }

    public Company getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(Messages.COMPANY_NOT_FOUND));
        log.info("CompanyService - getCompanyById() - Company = {}", company);
        return company;
    }

    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
        log.info("CompanyService - company deleted");
        log.info("CompanyService - deleteCompany()");
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();
        log.info("CompanyService - companies deleted");
        log.info("CompanyService - deleteAllCompanies()");
    }
}
