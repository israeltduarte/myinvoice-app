package br.com.isertech.myinvoiceback.service;

import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.RoleDTO;
import br.com.isertech.myinvoiceback.entity.Role;
import br.com.isertech.myinvoiceback.enums.RoleType;
import br.com.isertech.myinvoiceback.error.exception.OperationFailedException;
import br.com.isertech.myinvoiceback.error.exception.RoleAlreadyExistsException;
import br.com.isertech.myinvoiceback.error.exception.RoleNotFoundException;
import br.com.isertech.myinvoiceback.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {

        RoleType roleType;
        try {
            roleType = RoleType.valueOf(roleName);
        } catch (Exception e) {
            throw new RoleNotFoundException(Messages.ROLE_NOT_FOUND_INFO.concat(roleName));
        }
        Role role = roleRepository.findByRoleName(roleType)
                .orElseThrow(() -> new RoleNotFoundException(Messages.ROLE_NOT_FOUND_INFO));
        log.info("RoleService - findByRoleName() - Role={}", role);

        return role;
    }

    public List<Role> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        log.info("RoleService - getAllRoles() - List<Role>={}", roles);

        return roles;
    }

    public Role registerRole(RoleDTO dto) {

        log.info("RoleService - registerRole() - RoleDTO={}", dto);
        Role role = Role.builder()
                .roleName(RoleType.ROLE_ADMIN)
                .build();
        try {
            role = roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            log.error(Messages.ROLE_ALREADY_EXISTS.concat(dto.getRoleName()));
            throw new RoleAlreadyExistsException(Messages.ROLE_ALREADY_EXISTS.concat(dto.getRoleName()));
        } catch (Exception e) {
            log.error(Messages.OPERATION_FAILED);
            throw new OperationFailedException(Messages.OPERATION_FAILED);
        }
        log.info("RoleService - registerRole() - Role={}", role);

        return role;
    }

    public List<Role> checkAndGetRoles(List<String> rolesDTO) {

        return rolesDTO.parallelStream()
                .map(this::findByRoleName)
                .toList();
    }
}
