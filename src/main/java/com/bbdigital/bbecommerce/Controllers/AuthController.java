package com.bbdigital.bbecommerce.Controllers;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;

import com.bbdigital.bbecommerce.Models.ERole;
import com.bbdigital.bbecommerce.Models.Role;
import com.bbdigital.bbecommerce.Models.User;
import com.bbdigital.bbecommerce.Repositories.RoleRepository;
import com.bbdigital.bbecommerce.Repositories.UserRepository;
import com.bbdigital.bbecommerce.Security.Jwt.JwtUtils;
import com.bbdigital.bbecommerce.Services.UserService;
import com.bbdigital.bbecommerce.common.payload.Exception.BussinesRuleException;
import com.bbdigital.bbecommerce.common.payload.Request.LoginRequest;
import com.bbdigital.bbecommerce.common.payload.Request.SignupRequest;
import com.bbdigital.bbecommerce.common.payload.Response.JwtResponse;
import com.bbdigital.bbecommerce.common.payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtUserServiceToken = userService.generateJwtUserServiceToken(loginRequest);
        return ResponseEntity.ok(jwtUserServiceToken);
    }

    @PostMapping("/signin_admin")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) throws BussinesRuleException {
        JwtResponse jwtUserServiceToken = userService.generateAdminJwtUserServiceToken(loginRequest);
        return ResponseEntity.ok(jwtUserServiceToken);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }


        // Create new user's account
        User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getAddress(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), "1");
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_EDITOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    /*
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println(signUpRequest.getEmail());
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        User userCreated = userService.signUp(signUpRequest);
        ShoppingCar sp = new ShoppingCar();
        shoppingCarService.create(sp);
        userCreated.setShoppingCar(sp);
        userService.create(userCreated);
        //Send welcome email
        emailService.sendSimpleMessage(signUpRequest.getEmail(),"Hola bienvenido a resuelveAqui","Bienvenido a ResuelveAqui");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    */
}
