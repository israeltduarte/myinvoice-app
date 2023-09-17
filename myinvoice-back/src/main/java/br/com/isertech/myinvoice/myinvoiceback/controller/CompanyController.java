package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.dto.CompanyDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import br.com.isertech.myinvoice.myinvoiceback.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/companies")
@Transactional
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Page<Company>> getAllCompanies(Pageable pageable) {

        Page<Company> companies = companyService.getAllCompanies(pageable);
        if (!companies.isEmpty()) {
            for (Company company : companies.toList()) {
                company.add(linkTo(methodOn(CompanyController.class).getCompanyById(company.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody CompanyDTO companyDTO) {

        Company company = companyService.addCompany(companyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String companyId) {

        Company company = companyService.getCompanyById(companyId);

        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String companyId) {

        companyService.deleteCompany(companyId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCompanies() {

        companyService.deleteAllCompanies();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}