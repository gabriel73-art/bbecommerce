package com.bbdigital.bbecommerce.Services;

import com.bbdigital.bbecommerce.Models.User;
import com.bbdigital.bbecommerce.Repositories.UserRepository;
import com.bbdigital.bbecommerce.Security.Jwt.JwtUtils;
import com.bbdigital.bbecommerce.Security.services.UserDetailsImpl;
import com.bbdigital.bbecommerce.common.payload.Exception.BussinesRuleException;
import com.bbdigital.bbecommerce.common.payload.Request.LoginRequest;
import com.bbdigital.bbecommerce.common.payload.Response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;


    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User update(Long id, User user){
        Optional<User> entity=userRepository.findById(id);
        User p=entity.get();
        String password=p.getPassword();
        user.setPassword(password);
        p=userRepository.save(user);
        return p;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> findByID(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getByUsername(String nombreUsuario){
        return userRepository.findByUsername(nombreUsuario);
    }

    public Optional<User> getByTokenPassword(String tokenPassword){
        return userRepository.findByToken(tokenPassword);
    }

    public boolean existsByUsername(String nombreUsuario){
        return userRepository.existsByUsername(nombreUsuario);
    }

    public void save(User user){
        userRepository.save(user);
    }


    public JwtResponse generateJwtUserServiceToken(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }


    public JwtResponse generateAdminJwtUserServiceToken(LoginRequest loginRequest) throws BussinesRuleException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println(roles.get(0));
        if(roles.contains("ROLE_ADMIN")){
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles);
        }else {
            throw new BussinesRuleException("1000","Login only for Admin roles");
        }

    }


}

