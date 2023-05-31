/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import com.google.gson.Gson;
import com.ragjc.software.openmarketcommons.infra.Protocol;

/**
 *
 * @author RodAlejo
 */
public class ProductRequestProcessor {
    private ProductService productService;

    public ProductRequestProcessor(ProductService productService) {
        this.productService = productService;
    }

    public String processRequest(Protocol protocolRequest) {
        String action = protocolRequest.getAction();

        switch (action) {
            case "get":
                return processGetProduct(protocolRequest);
            case "post":
                return processPostProduct(protocolRequest);
            case "put":
                return processPutProduct(protocolRequest);
            case "delete":
                return processDeleteProduct(protocolRequest);
            default:
                return "";
        }
    }

     private String processGetProduct(Protocol protocolRequest) {
        String id = protocolRequest.getParameters().get(0).getValue();
        Product product = productService.findProductById(Long.parseLong(id));
        if (product == null) {
            String errorJson = JsonUtils.generateNotFoundErrorJson("Producto no encontrado. ID no existe");
            return errorJson;
        } else {
            return JsonUtils.objectToJSON(product);
        }
    }

    private String processPostProduct(Protocol protocolRequest) {
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(0).getValue());
        product.setDescription(protocolRequest.getParameters().get(1).getValue());

        System.out.println("Product: " + product.getName());
        return Boolean.toString(productService.saveProduct(product.getName(), product.getDescription()));
    }

    private String processPutProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
        return Boolean.toString(productService.editProduct(id, product));
    }

    private String processDeleteProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        return Boolean.toString(productService.deleteProduct(id));
    }

    public String processGetProducts() {
        return new Gson().toJson(productService.findAllProducts());
    }

    public String processGetProductsByName(Protocol protocolRequest) {
        String name = protocolRequest.getParameters().get(0).getValue();
        return new Gson().toJson(productService.findProductByName(name));
    }
    
}
