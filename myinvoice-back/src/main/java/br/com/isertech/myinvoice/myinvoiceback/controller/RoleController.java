package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.dto.RoleDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Role;
import br.com.isertech.myinvoice.myinvoiceback.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {

        List<Role> roles = roleService.getAllRoles();

        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PostMapping
    public ResponseEntity<Role> registerRole(@RequestBody RoleDTO dto) {

        Role role = roleService.registerRole(dto);

        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

}