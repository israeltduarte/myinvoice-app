package br.com.isertech.myinvoiceback.service;

import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoiceback.entity.Role;
import br.com.isertech.myinvoiceback.error.exception.UserNotFoundException;
import br.com.isertech.myinvoiceback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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

        LocalDateTime time = LocalDateTime.now();
        List<Role> roles = roleService.checkAndGetRoles(dto.getRoles());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        var user = mapper.map(dto, MIUser.class);
        user.setCreated(time);
        user.setUpdated(time);
        user.setRoles(roles);

        user = userRepository.save(user);

        log.info("UserService - addUser() - MIUser = {}", user);

        return user;
    }

    public MIUser getUserById(String id) {

        MIUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO.concat(id)));
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

    public Page<WeightDTO> getAllUserWeights(String id, Pageable pageable) {

        MIUser user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO.concat(id)));

        log.info("UserService - getAllUserWeights() - MIUser={}", user);

        Page<WeightDTO> weights;

        try {
            weights = weightClient.getAllWeights(id, pageable).getBody();
        } catch (Exception e) {
            log.error("Failed to reach out to weight-service.");
            throw new WeightClientException(Messages.WEIGHT_CLIENT_EXCEPTION);
        }

        log.info("UserService - getAllUserWeights() - Page<WeightDTO>={}", weights);

        return weights;
    }

    public UserDTO updateUserById(UserDTO dto, String id) {

        MIUser user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO.concat(id)));

        log.info("UserService - updateUserById() - MIUser={}", user);

        List<Role> roles;

        mapper.map(dto, user);

        if (null != dto.getRoles() && !dto.getRoles().isEmpty()) {
            roles = roleService.checkAndGetRoles(dto.getRoles());
            user.setRoles(roles);
        }

        user = userRepository.save(user);

        UserDTO userDTO = mapper.map(user, UserDTO.class);

        log.info("UserService - updateUserById() - UserDTO={}", userDTO);

        return userDTO;
    }

    public void deleteUserById(String id) {

        MIUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));
        try {
            weightClient.deleteAllWeights(id);
        } catch (Exception e) {
            log.error("Failed to reach out to weight-service.");
            throw new WeightClientException(Messages.WEIGHT_CLIENT_EXCEPTION);
        }

        userRepository.delete(user);

        log.info("UserService - deleteUserById() - ".concat(Messages.USER_DELETED));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("UserService - deleteAllUsers() - ".concat(Messages.USERS_DELETED));
    }

}
