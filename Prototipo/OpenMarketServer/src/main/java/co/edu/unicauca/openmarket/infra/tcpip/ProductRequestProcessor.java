/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ragjc.software.openmarketcommons.infra.Protocol;
import java.util.List;

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
            JsonObject json = new JsonObject();
            json.addProperty("productId", product.getProductId());
            json.addProperty("name", product.getName());
            json.addProperty("description", product.getDescription());
            json.addProperty("price", product.getPrice());
            json.addProperty("stock", product.getStock());
            json.addProperty("categoryId", product.getCategory().getCategoryId());
            json.addProperty("latitude", product.getLocation().getLatitude());
            json.addProperty("longitude", product.getLocation().getLongitude());
            json.addProperty("sellerId", product.getSeller().getUserId());
            json.addProperty("suspended", product.isSuspended());
            return json.toString();
        }
    }

    private String processPostProduct(Protocol protocolRequest) {
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(0).getValue());
        product.setDescription(protocolRequest.getParameters().get(1).getValue());

        System.out.println("Product: " + product.getName());
        boolean success = productService.saveProduct(product);
        return Boolean.toString(success);
    }

    private String processPutProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());

        boolean success = productService.editProduct(id, product);
        return Boolean.toString(success);
    }

    private String processDeleteProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        boolean success = productService.deleteProduct(id);
        return Boolean.toString(success);
    }

    public String processGetProducts() {
        List<Product> products = productService.findAllProducts();
        JsonArray jsonArray = new JsonArray();
        for (Product product : products) {
            JsonObject json = new JsonObject();
            json.addProperty("productId", product.getProductId());
            json.addProperty("name", product.getName());
            json.addProperty("description", product.getDescription());
            json.addProperty("price", product.getPrice());
            json.addProperty("stock", product.getStock());
            json.addProperty("categoryId", product.getCategory().getCategoryId());
            json.addProperty("latitude", product.getLocation().getLatitude());
            json.addProperty("longitude", product.getLocation().getLongitude());
            json.addProperty("sellerId", product.getSeller().getUserId());
            json.addProperty("suspended", product.isSuspended());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    public String processGetProductsByName(Protocol protocolRequest) {
        String name = protocolRequest.getParameters().get(0).getValue();
        List<Product> products = productService.findProductByName(name);
        JsonArray jsonArray = new JsonArray();
        for (Product product : products) {
            JsonObject json = new JsonObject();
            json.addProperty("productId", product.getProductId());
            json.addProperty("name", product.getName());
            json.addProperty("description", product.getDescription());
            json.addProperty("price", product.getPrice());
            json.addProperty("stock", product.getStock());
            json.addProperty("categoryId", product.getCategory().getCategoryId());
            json.addProperty("latitude", product.getLocation().getLatitude());
            json.addProperty("longitude", product.getLocation().getLongitude());
            json.addProperty("sellerId", product.getSeller().getUserId());
            json.addProperty("suspended", product.isSuspended());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
    
}
