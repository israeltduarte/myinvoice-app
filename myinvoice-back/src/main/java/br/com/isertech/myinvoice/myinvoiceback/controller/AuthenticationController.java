package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.config.security.JwtProvider;
import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.JwtDTO;
import br.com.isertech.myinvoice.myinvoiceback.dto.LoginDTO;
import br.com.isertech.myinvoice.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.EmailAlreadyExistsException;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.UserAlreadyExistsException;
import br.com.isertech.myinvoice.myinvoiceback.service.RoleService;
import br.com.isertech.myinvoice.myinvoiceback.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO dto) {

        if (userService.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException(Messages.USERNAME_ALREADY_EXISTS);
        }
        if (userService.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(Messages.EMAIL_ALREADY_EXISTS);
        }
        MIUser user = userService.addUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> authenticateUser(@Valid @RequestBody LoginDTO dto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(new JwtDTO(jwt));
    }

}
