package com.ragjc.software.openmarketcommons.domain;

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class User {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private Long phone;
    private String role;
    private String billingType;
    private String birthDate;
    private int punctuation;

    public User() {
    }
    
   
    public User(Long userId, String username, String password, String name, String lastName, String email, Long phone, String role, String billingType, String birthDate, int punctuation) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.billingType = billingType;
        this.birthDate = birthDate;
        this.punctuation = punctuation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }
    
    

}
