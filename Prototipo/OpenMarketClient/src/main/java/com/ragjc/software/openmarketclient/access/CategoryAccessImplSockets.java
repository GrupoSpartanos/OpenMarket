/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragjc.software.openmarketcommons.domain.Category;
import com.ragjc.software.openmarketclient.domain.infra.OpenMarketSocket;
import com.ragjc.software.openmarketcommons.infra.JsonError;
import com.ragjc.software.openmarketcommons.infra.Protocol;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryAccessImplSockets implements ICategoryAccess {

    private OpenMarketSocket mySocket;

    public CategoryAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean save(Category category) throws Exception {
        String jsonResponse = null;
        String requestJson = doCreateCategoryRequestJson(category);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean edit(Long id, Category category) throws Exception {
        String jsonResponse = null;
        String requestJson = doEditCategoryRequestJson(id.toString(), category);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doDeleteCategoryRequestJson(id.toString());
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return true;
            }
        }
    }

    @Override
    public Category findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindCategoryRequestJson(id.toString());
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return jsonToCategory(jsonResponse);
            }
        }
    }

    @Override
    public List<Category> findbyName(String name) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindNameCategoriesRequestJson(name);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return jsonToCategories(jsonResponse);
            }
        }
    }

    @Override
    public List<Category> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllCategoriesRequestJson();
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                return jsonToCategories(jsonResponse);
            }
        }
    }

    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        StringBuilder messages = new StringBuilder();
        for (JsonError error : errors) {
            messages.append(error.getMessage());
        }
        return messages.toString();
    }

    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        return gson.fromJson(jsonError, JsonError[].class);
    }

    private String doFindCategoryRequestJson(String idCategory) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("get");
        protocol.addParameter("id", idCategory);
        return new Gson().toJson(protocol);
    }

    private String doFindAllCategoriesRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("categories");
        protocol.setAction("get");
        return new Gson().toJson(protocol);
    }

    private String doFindNameCategoriesRequestJson(String name) {
        Protocol protocol = new Protocol();
        protocol.setResource("categories_name");
        protocol.setAction("get");
        protocol.addParameter("name", name);
        return new Gson().toJson(protocol);
    }

    private String doCreateCategoryRequestJson(Category category) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("post");
        protocol.addParameter("name", category.getName());
        return new Gson().toJson(protocol);
    }

    private String doEditCategoryRequestJson(String idCategory, Category editCategory) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("put");
        protocol.addParameter("id", idCategory);
        protocol.addParameter("name", editCategory.getName());
        return new Gson().toJson(protocol);
    }

    private String doDeleteCategoryRequestJson(String idCategory) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("delete");
        protocol.addParameter("id", idCategory);
        return new Gson().toJson(protocol);
    }

    private Category jsonToCategory(String jsonCategory) {
        return new Gson().fromJson(jsonCategory, Category.class);
    }

    private List<Category> jsonToCategories(String jsonCategories) {
        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<Category>>() {
        }.getType();
        return gson.fromJson(jsonCategories, founderListType);
    }
}