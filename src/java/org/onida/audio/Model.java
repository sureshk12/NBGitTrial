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
public class Model {
    
    private int id;
    private String name;
    private String company;
    private String product;
    private int uniqueId;
    private String dbname;
    
    public String setModel(String name, String company, String product, int uniqueId, String dbname) {
        String retValue = "OK";
        
        this.name = name;
        this.company = company;
        this.product = product;
        this.uniqueId = uniqueId;
        this.dbname = dbname;
        
        return retValue;
    }
    
    public String createModel(Model x) {
        DatabaseHelper db = new DatabaseHelper();
        return db.createModel(x);        
    }
    
    public ArrayList<Model> getAllModels() {
        DatabaseHelper db = new DatabaseHelper();
        return db.getAllModels();
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
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }
    
    /**
     * @return the uniqueId
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the dbname
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * @param dbname the dbname to set
     */
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }    

}
