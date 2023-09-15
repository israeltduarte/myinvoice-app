package br.com.isertech.myinvoice.myinvoiceback.service;

import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.CompanyDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.CompanyNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.UserNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.repository.CompanyRepository;
import br.com.isertech.myinvoice.myinvoiceback.util.CompanyTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;

    public CompanyService(UserService userService, CompanyRepository companyRepository) {
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    public Page<Company> getAllCompanies(Pageable pageable) {

        Page<Company> companies = companyRepository.findAll(pageable);
        log.info("CompanyService - getAllCompanies() - Page<Company>={}", companies);

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

    public Page<Company> getAllCompaniesByUserId(String id, Pageable pageable) {

        MIUser user = userService.getUserById(id);
        Page<Company> companies = companyRepository.findAllByUserId(user.getId(), pageable);
        log.info("CompanyService - getAllCompaniesByUserId() - Page<Company>={}", companies);

        return companies;
    }
}
