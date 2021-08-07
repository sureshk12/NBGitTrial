/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suresh
 */

package org.onida.audio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHelper {
    Connection conn = null;
    Statement stmt = null;
    
    public DatabaseHelper() {
        //Constructor        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onida_iot", "root", "Vidya@100");            
            stmt = conn.createStatement(); 
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("System error");
        }
    }    
    
    public ArrayList<String> getCompany() {
        ArrayList<String> retData = new ArrayList<String>();       
        try {
            ResultSet resultset = stmt.executeQuery("select * from company");
   
            while(resultset.next()) {
                retData.add(resultset.getString("comp_name"));

            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        return retData;   
    }
    
    public ArrayList<String> getProduct(String compStr) {
        ArrayList<String> retData = new ArrayList<String>();       
        try {
            ResultSet resultset = null;
            if(compStr.equals("ALL")) {
                resultset = stmt.executeQuery("SELECT * FROM product");
            } else {
                resultset = stmt.executeQuery("SELECT * FROM product");
            }
            
            while(resultset.next()) {
                retData.add(resultset.getString("prod_name"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        return retData;   
    }
    
    public ArrayList<String> getModel(String compStr, String prodStr) {
        ArrayList<String> retData = new ArrayList<String>();
        try {
            ResultSet resultset = stmt.executeQuery("SELECT * FROM model WHERE model_comp_name='"+compStr+"' AND model_prod_name='"+prodStr+"'");
            while(resultset.next()) {
                retData.add(resultset.getString("model_name"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        return retData;   
    }
    
    public String setCompany(ArrayList sqlData) {
        //String compName, String compPers, String compEmail, String compPhone, String compAddr
        String retMsg = "";
        try {        
            PreparedStatement st = null;
            switch(sqlData.get(8).toString()) {
                case "1":
                    //Check if company exists
                    ResultSet resultset = stmt.executeQuery("SELECT * FROM company WHERE comp_name='"+sqlData.get(0).toString()+"'");
                    if(resultset.next()) {
                       retMsg = "ERROR Company " + sqlData.get(0).toString() + " is already registed"; 
                    }  else {
                        st = conn.prepareStatement("INSERT into company (comp_name,comp_pers, comp_email, comp_phone, comp_addr) VALUES (?,?,?,?,?)");
                        st.setString(1, sqlData.get(0).toString());
                        st.setString(2, sqlData.get(1).toString());
                        st.setString(3, sqlData.get(2).toString());
                        st.setString(4, sqlData.get(3).toString());
                        st.setString(5, sqlData.get(4).toString());
                        retMsg = "Created " + sqlData.get(0).toString() + " Company Sucessfully"; 
                        st.executeUpdate();
                        st.close();
                    }
                    break;
                case "2":
                    resultset = stmt.executeQuery("SELECT * FROM product WHERE prod_name='"+sqlData.get(5).toString()+"'");
                    if(resultset.next()) {
                        retMsg = "ERROR Product " + sqlData.get(5).toString() + " is already registed"; 
                    } else {
                        st = conn.prepareStatement("INSERT into product (prod_name,prod_desc) VALUES (?,?)");
                        st.setString(1, sqlData.get(5).toString());
                        //st.setString(2, sqlData.get(9).toString());
                        st.setString(2, sqlData.get(6).toString());//was3
                        retMsg = "Created " + sqlData.get(5).toString() + " Product sucessfully"; 
                        st.executeUpdate();
                        st.close();
                    }
                    break;
                case "3":
                    resultset = stmt.executeQuery("SELECT * FROM model WHERE model_name='"+ sqlData.get(7).toString()+"' AND model_comp_name='"+sqlData.get(11).toString()+"' AND model_prod_name='"+sqlData.get(12).toString()+"'");
                    if(resultset.next()) {
                        retMsg = "ERROR Model with Company " + sqlData.get(11).toString() + " Product " + sqlData.get(12).toString() + " and Model " + sqlData.get(7).toString() + "is already registed"; 
                    } else {                    
                        st = conn.prepareStatement("INSERT into model (model_name,model_comp_name, model_prod_name) VALUES (?,?,?)");
                        st.setString(1, sqlData.get(7).toString());
                        st.setString(2, sqlData.get(11).toString());
                        st.setString(3, sqlData.get(12).toString());
                        retMsg = "Created " + sqlData.get(7).toString() + " Model Sucessfully";
                        st.executeUpdate();
                        st.close();
                    }
                    break;
            }           
            stmt.close();
            conn.close();            
        } catch(SQLException ex) {
           System.out.println("SQL Error"); 
        }        
        return retMsg;
    }   
}
