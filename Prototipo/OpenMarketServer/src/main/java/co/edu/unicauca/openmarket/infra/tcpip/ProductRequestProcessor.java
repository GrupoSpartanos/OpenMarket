/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Location;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.User;
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
            case "buy":
                return processBuyProduct(protocolRequest);
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
        product.setPrice(Double.parseDouble(protocolRequest.getParameters().get(2).getValue()));
        product.setStock(Integer.parseInt(protocolRequest.getParameters().get(3).getValue()));
        Category category = new Category(Long.parseLong(protocolRequest.getParameters().get(4).getValue()), "");
        product.setCategory(category);
        Location location = new Location(Double.parseDouble(protocolRequest.getParameters().get(5).getValue()),
                Double.parseDouble(protocolRequest.getParameters().get(6).getValue()));
        product.setLocation(location);
        User seller = new User();

        seller.setUserId(Long.parseLong(protocolRequest.getParameters().get(7).getValue()));
        product.setSeller(seller);
        product.setSuspended(Boolean.parseBoolean(protocolRequest.getParameters().get(8).getValue()));
        System.out.println("Product: " + product.getName());
        boolean success = productService.saveProduct(product);
        return Boolean.toString(success);
    }

    private String processPutProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
        product.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        product.setStock(Integer.parseInt(protocolRequest.getParameters().get(4).getValue()));
        Category category = new Category(Long.parseLong(protocolRequest.getParameters().get(5).getValue()), "");
        product.setCategory(category);
        Location location = new Location(Double.parseDouble(protocolRequest.getParameters().get(6).getValue()),
                Double.parseDouble(protocolRequest.getParameters().get(7).getValue()));
        product.setLocation(location);
        User seller = new User();

        seller.setUserId(Long.parseLong(protocolRequest.getParameters().get(8).getValue()));
        product.setSeller(seller);
        product.setSuspended(Boolean.parseBoolean(protocolRequest.getParameters().get(9).getValue()));

        boolean success = productService.editProduct(id, product);
        return Boolean.toString(success);
    }

    private String processDeleteProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        boolean success = productService.deleteProduct(id);
        return Boolean.toString(success);
    }
    
    private String processBuyProduct(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        boolean success = productService.buyProduct(id);
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

    String processGetProductsByDescription(Protocol protocolRequest) {
        String description = protocolRequest.getParameters().get(0).getValue();
        List<Product> products = productService.findProductByDescription(description);
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
