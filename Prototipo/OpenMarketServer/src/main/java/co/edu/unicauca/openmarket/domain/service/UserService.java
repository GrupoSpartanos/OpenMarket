/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.IUserRepository;
import co.edu.unicauca.openmarket.domain.User;

/**
 *
 * @author santi
 */
public class UserService {
    
    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }
    
    public boolean toRegister(String userName, String password, String name, String lastName, String email, Long phone, String role, String billingType, String birthDate, int punctuation){
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setRole(role);
        newUser.setBillingType(billingType);
        newUser.setBirthDate(birthDate);
        newUser.setPunctuation(punctuation);
        
        if(newUser.getUserName().isBlank()){
            return false;
        }
        boolean respuesta = repository.toRegister(newUser);
        //this.notificar();
        return respuesta ;
    }
            
    public User login(String userName, String password){
        return repository.login(userName, password);
    }    
}
