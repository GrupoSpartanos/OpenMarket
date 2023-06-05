package com.ragjc.software.openmarketcommons.domain;


/**
 *
 * @author Libardo, Julio
 */
public class Product {


    private Long productId;

    private String name;
    
    private String description;
    
    private double price;
    
    private Category category;
    
    private Location location;
    
    private User seller;
    
    private int stock;
    
    private boolean suspended;

    public Product(Long productId, String name, String description, double price, Category category, Location location, User seller, int stock, boolean suspended) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.location = location;
        this.seller = seller;
        this.stock = stock;
        this.suspended = suspended;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
    

    

    


}
