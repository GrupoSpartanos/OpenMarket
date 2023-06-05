/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.google.gson.Gson;
import com.ragjc.software.openmarketclient.domain.infra.OpenMarketSocket;
import com.ragjc.software.openmarketcommons.domain.Product;
import com.ragjc.software.openmarketcommons.domain.User;
import com.ragjc.software.openmarketcommons.infra.JsonError;
import com.ragjc.software.openmarketcommons.infra.Protocol;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class UserAccessImplSockets implements IUserAccess{
    
    private OpenMarketSocket mySocket; 
    
    public UserAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean toRegister(User user) throws Exception {
        String jsonResponse = null;
        String requestJson = doRegisterUserRequestJson(user);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {

                return true;
            }

        }
    }

    @Override
    public User login(String userName, String password) throws Exception {
        String jsonResponse = null;
        String requestJson = doLoginRequestJson(userName, password);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {

                User user = jsonToUser(jsonResponse);
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return user;
            }

        }
    }
    
    
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    
     private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    private String doRegisterUserRequestJson(User user) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("register");
        protocol.addParameter("username", user.getUserName());
        protocol.addParameter("password", user.getPassword());
        protocol.addParameter("name", user.getName());
        protocol.addParameter("lastName", user.getLastName());
        protocol.addParameter("email", user.getEmail());
        protocol.addParameter("phone", Long.toString(user.getPhone()));
        protocol.addParameter("role", user.getRole());
        protocol.addParameter("billingType", user.getBillingType());
        protocol.addParameter("birthDate", user.getBirthDate());
        protocol.addParameter("punctuation", Integer.toString(user.getPunctuation()));

        return new Gson().toJson(protocol);
    }
    
    private String doLoginRequestJson(String userName, String password) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("login");
        protocol.addParameter("username", userName);
        protocol.addParameter("password", password);

        return new Gson().toJson(protocol);
    }
    
    private User jsonToUser(String jsonUser) {
        return new Gson().fromJson(jsonUser, User.class);
    }
    
    
}
