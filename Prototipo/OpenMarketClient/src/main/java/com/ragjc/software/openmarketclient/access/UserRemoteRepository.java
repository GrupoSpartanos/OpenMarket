/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.ragjc.software.openmarketcommons.domain.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class UserRemoteRepository implements IUserRepository{
    
    private UserAccessImplSockets userAccess;

    public UserRemoteRepository() {
        this.userAccess = new UserAccessImplSockets();
    }
    

    @Override
    public boolean toRegister(User user) {
        try {
            return userAccess.toRegister(user);
        } catch (Exception ex) {
            Logger.getLogger(UserRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public User login(String userName, String password) {
        
        try {
            return userAccess.login(userName, password);
        } catch (Exception ex) {
            Logger.getLogger(UserRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
