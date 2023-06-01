package com.bbdigital.bbecommerce.common.payload.Request;

import java.util.Set;

import javax.validation.constraints.*;

public class  SignupRequest {

    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(min = 3, max = 100)
    private String address;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;


    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignupRequest(){
        super();
    }

    public SignupRequest(String firstname, String lastname, String address, String username, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.address=address;
        this.username=username;
        this.password=password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
