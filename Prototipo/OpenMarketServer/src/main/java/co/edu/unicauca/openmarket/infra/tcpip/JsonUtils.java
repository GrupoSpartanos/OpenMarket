/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import com.google.gson.Gson;
import com.ragjc.software.openmarketcommons.infra.JsonError;

/**
 *
 * @author RodAlejo
 */
public class JsonUtils {
    public static String objectToJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    
    public static String generateNotFoundErrorJson(String message) {
        JsonError error = new JsonError("404","Not found", message);
        return errorToJson(error);
    }

    private static String errorToJson(JsonError error) {
        return "{ \"error\": \"" + error.getError() + "\", \"message\": \"" + error.getMessage() + "\" }";
    }
}
