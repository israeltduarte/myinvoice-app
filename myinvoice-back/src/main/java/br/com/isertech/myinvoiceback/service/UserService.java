package br.com.isertech.myinvoiceback.service;

import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoiceback.entity.Role;
import br.com.isertech.myinvoiceback.error.exception.UserNotFoundException;
import br.com.isertech.myinvoiceback.repository.UserRepository;
import br.com.isertech.myinvoiceback.util.MIUserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<MIUser> getAllUsers(Pageable pageable) {

        Page<MIUser> users = userRepository.findAll(pageable);
        log.info("UserService - getAllUsers() - Page<MIUser>={}", users);

        return users;
    }

    public MIUser addUser(UserDTO dto) {

        MIUser user = getNewUserEntityReady(dto);
        user = userRepository.save(user);
        log.info("UserService - addUser() - MIUser = {}", user);

        return user;
    }

    private MIUser getNewUserEntityReady(UserDTO dto) {

        LocalDateTime time = LocalDateTime.now();
        List<Role> roles = roleService.checkAndGetRoles(dto.getRoles());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        MIUser user = MIUserTransformer.fromDTO(dto);
        user.setCreated(time);
        user.setUpdated(time);
        user.setRoles(roles);

        return user;
    }

    public MIUser getUserById(String id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));
        log.info("UserService - getUserById() - UserDTO={}", user);

        return user;
    }

    public boolean existsByUsername(String username) {

        var exists = userRepository.findByUsername(username).isPresent();
        log.warn("UserService - existsByUsername() - " + Messages.USERNAME_ALREADY_EXISTS);

        return exists;
    }

    public boolean existsByEmail(String email) {

        var exists = userRepository.findByEmail(email).isPresent();
        log.warn("UserService - existsByEmail() - " + Messages.EMAIL_ALREADY_EXISTS);

        return exists;
    }

    public MIUser updateUserById(UserDTO dto, String id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));

        List<Role> roles;

        user = MIUserTransformer.fromDTO(user, dto);

        if (null != dto.getRoles() && !dto.getRoles().isEmpty()) {
            roles = roleService.checkAndGetRoles(dto.getRoles());
            user.setRoles(roles);
        }

        user = userRepository.save(user);
        log.info("UserService - updateUserById() - MIUser={}", user);

        return user;
    }

    public void deleteUserById(String id) {

        MIUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));
//        try {
//            weightClient.deleteAllWeights(id);
//        } catch (Exception e) {
//            log.error("Failed to reach out to weight-service.");
//            throw new WeightClientException(Messages.WEIGHT_CLIENT_EXCEPTION);
//        }

        userRepository.delete(user);

        log.info("UserService - deleteUserById() - ".concat(Messages.USER_DELETED));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("UserService - deleteAllUsers() - ".concat(Messages.USERS_DELETED));
    }

}
