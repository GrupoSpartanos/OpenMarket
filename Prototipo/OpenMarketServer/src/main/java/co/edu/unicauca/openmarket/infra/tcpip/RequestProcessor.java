package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.User;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.edu.unicauca.openmarket.domain.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragjc.software.openmarketcommons.infra.JsonError;
import com.ragjc.software.openmarketcommons.infra.Protocol;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RequestProcessor {


    private static ProductRequestProcessor productRequestProcessor;
    private static CategoryRequestProcessor categoryRequestProcessor;
    private static UserRequestProcessor userRequestProcessor;

    public RequestProcessor(){
        
    }

    public String processRequest(String requestJson) {
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response = "";

        switch (protocolRequest.getResource()) {
            case "product":
                response = productRequestProcessor.processRequest(protocolRequest);
                break;
            case "products":
                response = productRequestProcessor.processGetProducts();
                break;
            case "products_name":
                response = productRequestProcessor.processGetProductsByName(protocolRequest);
                break;
            case "products_description":
                response = productRequestProcessor.processGetProductsByDescription(protocolRequest);
                break;
            case "category":
                response = categoryRequestProcessor.processRequest(protocolRequest);
                break;
            case "categories":
                response = categoryRequestProcessor.processGetCategories();
                break;
            case "user":
                response = userRequestProcessor.processRequest(protocolRequest);
                break;
        }

        return response;
    }

    public void setProductRequestProcessor(ProductRequestProcessor productRequestProcessor) {
        this.productRequestProcessor = productRequestProcessor;
    }

    public void setCategoryRequestProcessor(CategoryRequestProcessor categoryRequestProcessor) {
        this.categoryRequestProcessor = categoryRequestProcessor;
    }

    public void setUserRequestProcessor(UserRequestProcessor userRequestProcessor) {
        this.userRequestProcessor = userRequestProcessor;
    }
    
    
    
    
    
}