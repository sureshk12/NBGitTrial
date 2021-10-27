/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.util.ArrayList;

/**
 *
 * @author Suresh
 */
public class Product {
    
    private int id;
    private String name;
    private String code;
    private String description;
    
    public String setProduct(String name, String code, String description) {
        String retValue = "OK";
        
        this.name = name;
        this.code = code;
        this.description = description;
        
        return retValue;
    }
    
    public String createNewProduct(Product x) {
        DatabaseHelper db = new DatabaseHelper();
        return db.createProduct(x);        
    }
    
    public Product getProduct(String name) {
        DatabaseHelper db = new DatabaseHelper();
        return db.getProduct(this, name);
    }
    
    public ArrayList<Product> getAllProducts() {
        DatabaseHelper db = new DatabaseHelper();
        return db.getAllProduct();
    }    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.description = Description;
    }
    
}
