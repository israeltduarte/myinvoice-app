package br.com.isertech.myinvoice.myinvoiceback.service;

import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.CompanyDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.CompanyNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.repository.CompanyRepository;
import br.com.isertech.myinvoice.myinvoiceback.util.CompanyTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;

    public CompanyService(UserService userService, CompanyRepository companyRepository) {
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {

        List<Company> companies = companyRepository.findAll();
        log.info("CompanyService - getAllCompanies() - List<Company>={}", companies);

        return companies;
    }

    public Company addCompany(CompanyDTO dto) {

        MIUser user = userService.getUserById(dto.getUserId());
        Company company = CompanyTransformer.fromDTO(dto);
        company.setUser(user);

        company = companyRepository.save(company);
        log.info("CompanyService - addCompany() - Company={}", company);

        return company;
    }

    public Company getCompanyById(String companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(Messages.COMPANY_NOT_FOUND));
        log.info("CompanyService - getCompanyById() - Company={}", company);

        return company;
    }

    public void deleteCompany(String companyId) {

        companyRepository.deleteById(companyId);
        log.info("CompanyService - deleteCompany()");
    }

    public void deleteAllCompanies() {

        companyRepository.deleteAll();
        log.info("CompanyService - deleteAllCompanies()");
    }

    public List<Company> getAllCompaniesByUserId(String id) {

        MIUser user = userService.getUserById(id);
        List<Company> companies = companyRepository.findAllByUserId(user.getId());
        log.info("CompanyService - getAllCompaniesByUserId() - List<Company>={}", companies);

        return companies;
    }
}
