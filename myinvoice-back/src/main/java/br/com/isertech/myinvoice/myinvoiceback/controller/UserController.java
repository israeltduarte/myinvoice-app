package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.service.ClientService;
import br.com.isertech.myinvoice.myinvoiceback.service.CompanyService;
import br.com.isertech.myinvoice.myinvoiceback.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ClientService clientService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Page<MIUser>> getAllUsers(Pageable pageable) {

        Page<MIUser> users = userService.getAllUsers(pageable);
        if (!users.isEmpty()) {
            for (MIUser user : users.toList()) {
                user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MIUser> getUserById(@PathVariable String id) {

        MIUser user = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}/companies")
    public ResponseEntity<List<Company>> getAllCompaniesByUserId(@PathVariable String id) {

        List<Company> companies = companyService.getAllCompaniesByUserId(id);
        if (!companies.isEmpty()) {
            for (Company company : companies) {
                company.add(linkTo(methodOn(UserController.class).getUserById(company.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/{id}/clients")
    public ResponseEntity<List<Client>> getAllClientsByUserId(@PathVariable String id) {

        List<Client> clients = clientService.getAllClientsByUserId(id);
        if (!clients.isEmpty()) {
            for (Client client : clients) {
                client.add(linkTo(methodOn(UserController.class).getUserById(client.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MIUser> updateUserById(@Valid @RequestBody UserDTO dto, @PathVariable String id) {

        MIUser user = userService.updateUserById(dto, id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {

        userService.deleteAllUsers();

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {

        userService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }
}