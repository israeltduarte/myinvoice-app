package br.com.isertech.myinvoiceback.controller;

import br.com.isertech.myinvoiceback.config.security.JwtProvider;
import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.JwtDTO;
import br.com.isertech.myinvoiceback.dto.LoginDTO;
import br.com.isertech.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoiceback.service.RoleService;
import br.com.isertech.myinvoiceback.service.UserService;
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
@RequestMapping("/auth")
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Messages.USERNAME_ALREADY_EXISTS);
        }
        if (userService.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Messages.EMAIL_ALREADY_EXISTS);
        }
        var user = userService.addUser(dto);

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
