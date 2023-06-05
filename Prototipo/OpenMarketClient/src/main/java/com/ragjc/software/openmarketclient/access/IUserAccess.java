/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.ragjc.software.openmarketcommons.domain.User;

/**
 *
 * @author santi
 */
public interface IUserAccess {
    
    boolean toRegister(User user)  throws Exception;
    
    User login (String userName, String password)  throws Exception;
    
}
