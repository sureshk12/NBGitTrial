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
public class Company {
    private int id;
    private String name;
    private String code;
    private String rep_name;
    private String rep_email;
    private String rep_phone_no;
    private String address;
    
    //Constructor
    void Company(){
        
    }
    
    public String setCompany(String name, String code, String repName, String repEmail, String repPhoneNum, String address) {
        String retValue = "OK";
        
        this.name = name;
        this.code = code;
        this.rep_name = repName;
        this.rep_email = repEmail;
        this.rep_phone_no = repPhoneNum;
        this.address = address;
        
        return retValue;
    }
    
    public String createNewCompany(Company x) {
        DatabaseHelper db = new DatabaseHelper();
        return db.createCompany(x);        
    }
        
    public Company getCompany(String name) {
        DatabaseHelper db = new DatabaseHelper();
        return db.getCompany(this, name);
    }
    
    public ArrayList<Company> getAllCompanies() {
        DatabaseHelper db = new DatabaseHelper();
        return db.getAllCompany();
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
     * @return the rep_name
     */
    public String getRep_name() {
        return rep_name;
    }

    /**
     * @param rep_name the rep_name to set
     */
    public void setRep_name(String rep_name) {
        this.rep_name = rep_name;
    }

    /**
     * @return the rep_email
     */
    public String getRep_email() {
        return rep_email;
    }

    /**
     * @param rep_email the rep_email to set
     */
    public void setRep_email(String rep_email) {
        this.rep_email = rep_email;
    }

    /**
     * @return the rep_phone_no
     */
    public String getRep_phone_no() {
        return rep_phone_no;
    }

    /**
     * @param rep_phone_no the rep_phone_no to set
     */
    public void setRep_phone_no(String rep_phone_no) {
        this.rep_phone_no = rep_phone_no;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
