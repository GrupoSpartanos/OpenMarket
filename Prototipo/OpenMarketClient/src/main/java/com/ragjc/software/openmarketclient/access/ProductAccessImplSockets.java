/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragjc.software.openmarketcommons.domain.Product;
import com.ragjc.software.openmarketclient.domain.infra.OpenMarketSocket;
import com.ragjc.software.openmarketcommons.domain.Category;
import com.ragjc.software.openmarketcommons.domain.Location;
import com.ragjc.software.openmarketcommons.domain.ProductGson;
import com.ragjc.software.openmarketcommons.domain.User;
import com.ragjc.software.openmarketcommons.infra.JsonError;
import com.ragjc.software.openmarketcommons.infra.Protocol;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class ProductAccessImplSockets implements IProductAccess {

    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean save(Product newProduct) throws Exception {
        String jsonResponse = null;
        String requestJson = doCreateProductRequestJson(newProduct);
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
    public boolean edit(Long id, Product product) throws Exception {
        String jsonResponse = null;
        String requestJson = doEditProductRequestJson(id.toString(), product);
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
    public boolean delete(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(id.toString());
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer

                return true;
            }
        }
    }
    
    
    
    @Override
    public boolean buy(Long id) throws Exception {
         String jsonResponse = null;
        String requestJson = doBuyProductRequestJson(id.toString());
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer

                return true;
            }
        }
    }

    @Override
    public Product findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductRequestJson(id.toString());
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return product;
            }
        }
    }

    @Override
    public List<Product> findByName(String name) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindNameProductsRequestJson(name);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer
                List<Product> products = jsonToProducts(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
    }

    @Override
    public List<Product> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllProductsRequestJson();
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer
                List<Product> products = jsonToProducts(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
    }

    @Override
    public List<Product> findByDescription(String description) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindDescriptionProductsRequestJson(description);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer
                List<Product> products = jsonToProducts(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
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

    private String doFindProductRequestJson(String idProduct) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("id", idProduct);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private String doFindAllProductsRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("products");
        protocol.setAction("get");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private String doFindNameProductsRequestJson(String name) {
        Protocol protocol = new Protocol();
        protocol.setResource("products_name");
        protocol.setAction("get");
        protocol.addParameter("name", name);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private String doFindDescriptionProductsRequestJson(String description) {
        Protocol protocol = new Protocol();
        protocol.setResource("products_description");
        protocol.setAction("get");
        protocol.addParameter("description", description);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private String doCreateProductRequestJson(Product product) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        protocol.addParameter("price", Double.toString(product.getPrice()));
        protocol.addParameter("stock", Integer.toString(product.getStock()));
        protocol.addParameter("categoryId", Long.toString(product.getCategory().getCategoryId()));
        protocol.addParameter("latitude", Double.toString(product.getLocation().getLatitude()));
        protocol.addParameter("longitude", Double.toString(product.getLocation().getLongitude()));
        protocol.addParameter("sellerId", Long.toString(product.getSeller().getUserId()));
        protocol.addParameter("suspended", Boolean.toString(product.isSuspended()));

        return new Gson().toJson(protocol);
    }

    private String doEditProductRequestJson(String idProduct, Product editProduct) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("put");
        protocol.addParameter("id", idProduct);
        protocol.addParameter("name", editProduct.getName());
        protocol.addParameter("description", editProduct.getDescription());
        protocol.addParameter("price", Double.toString(editProduct.getPrice()));
        protocol.addParameter("stock", Integer.toString(editProduct.getStock()));
        protocol.addParameter("categoryId", Long.toString(editProduct.getCategory().getCategoryId()));
        protocol.addParameter("latitude", Double.toString(editProduct.getLocation().getLatitude()));
        protocol.addParameter("longitude", Double.toString(editProduct.getLocation().getLongitude()));
        protocol.addParameter("sellerId", Long.toString(editProduct.getSeller().getUserId()));
        protocol.addParameter("suspended", Boolean.toString(editProduct.isSuspended()));

        return new Gson().toJson(protocol);
    }

    private String doDeleteProductRequestJson(String idProduct) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("delete");
        protocol.addParameter("id", idProduct);

        return new Gson().toJson(protocol);
    }
    
    private String doBuyProductRequestJson(String idProduct) {
         Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("buy");
        protocol.addParameter("id", idProduct);

        return new Gson().toJson(protocol);
    }
    

    private Product jsonToProduct(String jsonProduct) {
        ProductGson productGson = new Gson().fromJson(jsonProduct, ProductGson.class);
        Product product = new Gson().fromJson(jsonProduct, Product.class);
        product.setCategory(new Category(productGson.getCategoryId(), ""));
        product.setLocation(new Location(productGson.getLatitude(), productGson.getLongitude()));
        User seller = new User();
        seller.setUserId(productGson.getSellerId());
        product.setSeller(seller);

        return product;
    }

    private List<Product> jsonToProducts(String jsonProducts) {
        Gson gson = new Gson();
        Type productListType = new TypeToken<List<ProductGson>>() {
        }.getType();
        List<ProductGson> productGsonList = gson.fromJson(jsonProducts, productListType);

        List<Product> productsList = new ArrayList<>();
        for (ProductGson productGson : productGsonList) {
            Product product = new Product();
            product.setProductId(productGson.getProductId());
            product.setName(productGson.getName());
            product.setDescription(productGson.getDescription());
            product.setPrice(productGson.getPrice());

            Category category = new Category(productGson.getCategoryId(), "");
            product.setCategory(category);

            Location location = new Location(productGson.getLatitude(), productGson.getLongitude());
            product.setLocation(location);

            User seller = new User();
            seller.setUserId(productGson.getSellerId());
            product.setSeller(seller);

            productsList.add(product);
        }

        return productsList;
    }

    


    

}
