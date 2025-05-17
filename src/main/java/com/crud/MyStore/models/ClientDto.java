package com.crud.MyStore.models;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class ClientDto {

    private int id;  // ✅ Add this field

    @NotEmpty(message = "The first name is required")
    private String firstname;

    @NotEmpty(message = "The last name is required")
    private String lastname;

    @NotEmpty(message = "The email is required")
    private String email;

    private String phone;
    private String address;

    private LocalDateTime createdAt;

    // ✅ Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Other Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
