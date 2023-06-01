/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.User;

/**
 *
 * @author santi
 */
public interface IUserRepository {
    
    boolean toRegister(User user);
    
    User login (String userName, String password);
    
    
    
}
