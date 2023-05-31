/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.User;
import co.edu.unicauca.openmarket.domain.service.UserService;
import com.ragjc.software.openmarketcommons.infra.Protocol;
/**
 *
 * @author RodAlejo
 */
public class UserRequestProcessor {
    private UserService userService;

    public UserRequestProcessor(UserService userService) {
        this.userService = userService;
    }

    public String processRequest(Protocol protocolRequest) {
        String action = protocolRequest.getAction();

        switch (action) {
            case "register":
                return Boolean.toString(processRegisterUser(protocolRequest));
            case "login":
                return processLoginUser(protocolRequest);
            default:
                return "";
        }
    }

     private boolean processRegisterUser(Protocol protocolRequest) {
        String userName = protocolRequest.getParameters().get(0).getValue();
        String password = protocolRequest.getParameters().get(1).getValue();
        String name = protocolRequest.getParameters().get(2).getValue();
        String lastName = protocolRequest.getParameters().get(3).getValue();
        String email = protocolRequest.getParameters().get(4).getValue();
        Long phone = Long.parseLong(protocolRequest.getParameters().get(5).getValue());
        String role = protocolRequest.getParameters().get(6).getValue();
        String billingType = protocolRequest.getParameters().get(7).getValue();
        String birthDate = protocolRequest.getParameters().get(8).getValue();
        int punctuation = Integer.parseInt(protocolRequest.getParameters().get(9).getValue());

        return userService.toRegister(userName, password, name, lastName, email, phone, role, billingType, birthDate, punctuation);
    }

    private String processLoginUser(Protocol protocolRequest) {
        String userName = protocolRequest.getParameters().get(0).getValue();
        String password = protocolRequest.getParameters().get(1).getValue();
        User user = userService.login(userName, password);
        if (user == null) {
            String errorJson = JsonUtils.generateNotFoundErrorJson("Usuario o contraseña incorrectos");
            return errorJson;
        } else {
            return JsonUtils.objectToJSON(user);
        }
    }
}
