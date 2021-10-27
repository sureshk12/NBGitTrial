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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    
    
    public ArrayList<String> getCompanyNames() {
        ArrayList<String> retData = new ArrayList<>();       
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
    
    public ArrayList<String> getProductNames() {
        ArrayList<String> retData = new ArrayList<>();       
        try {
            ResultSet resultset;
            resultset = stmt.executeQuery("SELECT * FROM product");
            
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
    
    public ArrayList<String> getModelNames(String compStr, String prodStr) {
        ArrayList<String> retData = new ArrayList<>();
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
    
    public String getThingsCode(String compStr, String prodStr, String modelStr) {
        String thingCode = "";
        //Get comp_code
        ResultSet resultset;
        try {
//            resultset = stmt.executeQuery("select * from company WHERE comp_name='"+compStr+"'");
//            while(resultset.next()) {
//                thingCode += resultset.getString("comp_code");
//            }
//            resultset = stmt.executeQuery("select * from product WHERE prod_name='"+prodStr+"'");
//            while(resultset.next()) {
//                thingCode += resultset.getString("prod_code");
//            }
            thingCode = getDatabaseName(compStr, prodStr);
            if(!thingCode.equals("ERROR")) {
                resultset = stmt.executeQuery("select * from model WHERE model_name='"+modelStr+"' AND model_comp_name='"+compStr+"' AND model_prod_name='"+prodStr+"'");
                while(resultset.next()) {
                    thingCode += resultset.getString("model_uniqueid");
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        return thingCode;
    }
    
    public String getDatabaseName(String compName, String prodName) {
        String returnVal = "ERROR";       
        ResultSet resultset;
        try {
            resultset = stmt.executeQuery("SELECT * FROM company WHERE comp_name='"+ compName+"'");
            if(resultset.next()) {
                String companyCode = resultset.getString("comp_code");
                resultset = stmt.executeQuery("SELECT * FROM product WHERE prod_name='"+ prodName+"'");
                if(resultset.next()) {
                    returnVal = companyCode + resultset.getString("prod_code");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnVal;
    }
    
    public String updatedynamoLocal(String dbName, String serial) {
        String returnVal = "ERROR";
        try {
            stmt.executeUpdate("UPDATE `"+ dbName + "` SET `Dynamo` = '1' WHERE SerialNum='"+ serial +"'");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return returnVal;
    }
    
/*    
    public String setData(ArrayList sqlData) {
        //String compName, String compPers, String compEmail, String compPhone, String compAddr
        String retMsg = "";
        try {        
            PreparedStatement st;
            switch(sqlData.get(10).toString()) {
                case "1":
                    //Company Creation
                    ResultSet resultset = stmt.executeQuery("SELECT * FROM company WHERE comp_name='"+sqlData.get(0).toString()+"'");
                    if(resultset.next()) {
                    //Check if company exists                        
                       retMsg = "ERROR Company name <b>" + sqlData.get(0).toString() + "</b> is already registered"; 
                    }  else {
                        resultset = stmt.executeQuery("SELECT * FROM company WHERE comp_code='"+sqlData.get(1).toString()+"'");
                        if(resultset.next()) {
                            retMsg = "ERROR Company code <b>" + sqlData.get(1).toString() + "</b> is already registered";
                        } else {
                            st = conn.prepareStatement("INSERT into company (comp_name,comp_code,comp_pers, comp_email, comp_phone, comp_addr) VALUES (?,?,?,?,?,?)");
                            st.setString(1, sqlData.get(0).toString());//company_name
                            st.setString(2, sqlData.get(1).toString());//company_code
                            st.setString(3, sqlData.get(2).toString());//company_person
                            st.setString(4, sqlData.get(3).toString());//company_email
                            st.setString(5, sqlData.get(4).toString());//company_phone
                            st.setString(6, sqlData.get(5).toString());//company_addr
                            retMsg = "Created <b>" + sqlData.get(0).toString() + "<b> Company Sucessfully"; 
                            st.executeUpdate();
                            st.close();
                        }
                    }
                    break;
                case "2":
                    //Product Creation
                    resultset = stmt.executeQuery("SELECT * FROM product WHERE prod_name='"+sqlData.get(6).toString()+"'");
                    if(resultset.next()) {
                        //Check if product exists
                        retMsg = "ERROR Product name <b>" + sqlData.get(6).toString() + "</b> is already registered"; 
                    } else {
                        resultset = stmt.executeQuery("SELECT * FROM product WHERE prod_code='"+sqlData.get(7).toString()+"'");
                        if(resultset.next()){
                            retMsg = "ERROR Product code <b>" + sqlData.get(7).toString() + "</b> is already registered";
                        } else {
                            st = conn.prepareStatement("INSERT into product (prod_name,prod_code,prod_desc) VALUES (?,?,?)");
                            st.setString(1, sqlData.get(6).toString());//product_name
                            st.setString(2, sqlData.get(7).toString());//product_code
                            st.setString(2, sqlData.get(8).toString());//product_description
                            retMsg = "Created <b>" + sqlData.get(6).toString() + "</b> Product sucessfully"; 
                            st.executeUpdate();
                            st.close();
                        }
                    }
                    break;
                case "3":
                    //Create Model
                    resultset = stmt.executeQuery("SELECT * FROM model WHERE model_name='"+ sqlData.get(9).toString()+"' AND model_comp_name='"+sqlData.get(13).toString()+"' AND model_prod_name='"+sqlData.get(14).toString()+"'");
                    if(resultset.next()) {
                        //Create model only when Company, Product, Model number combination does not exists
                        retMsg = "ERROR Model with Company <b>" + sqlData.get(13).toString() + "</b> Product <b>" + sqlData.get(14).toString() + "</b> and Model <b>" + sqlData.get(9).toString() + "</b> is already registed"; 
                    } else {
                        resultset = stmt.executeQuery("SELECT * FROM model WHERE model_comp_name='"+sqlData.get(13).toString()+"' AND model_prod_name='"+sqlData.get(14).toString()+"'");
                        int sizeResultSet = 0;
                        while(resultset.next()) {
                            sizeResultSet++;
                        }
                        String modelUniqueId = Integer.toString(sizeResultSet + 1);
                       
                        st = conn.prepareStatement("INSERT into model (model_name,model_comp_name,model_prod_name,model_uniqueid) VALUES (?,?,?,?)");
                        String modelName = sqlData.get(9).toString();//model_name
                        String compName = sqlData.get(13).toString();//dropDown1=company
                        String prodName = sqlData.get(14).toString();//dropDown2=product
                        st.setString(1, modelName);
                        st.setString(2, compName);
                        st.setString(3, prodName);
                        st.setString(4, modelUniqueId);
                        st.executeUpdate();
                        st.close();                       
                        //Form the Database name
                        String companyCode;
                        String productCode;
                        String dynamoDbName = getDatabaseName(compName, prodName);
                        if(!dynamoDbName.equals("ERROR")) {
                            //Create Database on AWS
                            String retValue = CreateDbTable.createTable(dynamoDbName, compName, prodName);
                            if(retValue.equals("OK")) {
                                //create Local database
                                retValue = createLocalDatabase(dynamoDbName, compName, prodName);
                            }                            
                            if(retValue.equals("OK")) {
                                retMsg = "Created <b>" + modelName + " Model with Database name " +dynamoDbName + "</b> Sucessfully";
                            } else {
                                retMsg = "Error Could not create table";
                            }
                        }                                         
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
    

    

    


*/    
    
    
    
    
    
    
    
    
    
    
    
    //New routines.....
    
    /* 
        if the company and code does not exist, creates company data in DB 
        Returns success msg if sucessful, Error msg if unsucessful
    */
    public String createCompany(Company x) {
        //Company Creation
        String retValue = "Created Company with name " + x.getName();
        try {        
            PreparedStatement st;
            //Check if company exists 
            ResultSet resultset = stmt.executeQuery("SELECT * FROM company WHERE comp_name='"+ x.getName() + "' AND comp_code='" + x.getCode() +"'");
            if(resultset.next()) {                       
               retValue = "ERROR Company name <b>" + x.getName() + "</b> and Company Code <b>"+ x.getCode() + "</b> are already registered"; 
            }  else {
                //Store Company in Database
                st = conn.prepareStatement("INSERT into company (comp_name, comp_code, comp_pers, comp_email, comp_phone, comp_addr) VALUES (?,?,?,?,?,?)");
                st.setString(1, x.getName());//company_name
                st.setString(2, x.getCode());//company_code
                st.setString(3, x.getRep_name());//company_person
                st.setString(4, x.getRep_email());//company_email
                st.setString(5, x.getRep_phone_no());//company_phone
                st.setString(6, x.getAddress());//company_addr
                retValue = "Created <b>" + x.getName() + "<b> Company Sucessfully"; 
                st.executeUpdate();
                st.close();                
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
                retValue = "ERROR could not create Company with name " + x.getName();
            }
        }        
        return retValue;
    }
    
    /* 
        if the product and code does not exist, creates product data in DB 
        Returns success msg if sucessful, Error msg if unsucessful
    */
    public String createProduct(Product x) {
        //Company Creation
        String retValue = "Created Product with Name "+ x.getName();
        try {        
            PreparedStatement st;
            //Check if Product exists
            ResultSet resultset = stmt.executeQuery("SELECT * FROM product WHERE prod_name='"+ x.getName() + "' AND prod_code='" + x.getCode() +"'");
            if(resultset.next()) {                        
               retValue = "ERROR Product name <b>" + x.getName() + "</b> and Product Code <b>"+ x.getCode() + "</b> are already registered"; 
            }  else {
                //Store Product
                st = conn.prepareStatement("INSERT into product (prod_name, prod_code, prod_desc) VALUES (?,?,?)");
                st.setString(1, x.getName());//product_name
                st.setString(2, x.getCode());//product_code
                st.setString(3, x.getDescription());//product_description
                retValue = "Created <b>" + x.getName() + "<b> Product Sucessfully"; 
                st.executeUpdate();
                st.close();                
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
                retValue = "ERROR could not create Product with name " + x.getName();
            }
        }        
        return retValue;
    }
    
    /* 
        if the model, company, product cobination does not exist, creates 
        Model data in DB 
        Returns success msg if sucessful, Error msg if unsucessful
    */
    
    public String createModel(Model x) {
        //Company Creation
        String retValue = "";//"Created Model with Name "+ x.getName();
        String dynamoDbName = "";
        try {        
            PreparedStatement st;            
            ResultSet resultset = stmt.executeQuery("SELECT * FROM model WHERE model_name='"+ x.getName() + "' AND model_comp_name='" + x.getCompany() + "' AND model_prod_name='" + x.getProduct() + "'");
            if(resultset.next()) {
            //Check if combination company, product and model exists                        
               retValue = "ERROR Model <b>" + x.getName() + "</b>, Product <b>" + x.getProduct() + "</b> and Company <b>" + x.getCompany()+ "</b> already registered"; 
            }  else {
                st = conn.prepareStatement("INSERT into model (model_name, model_prod_name, model_comp_name, model_uniqueid, model_db_name) VALUES (?,?,?,?,?)");
                st.setString(1, x.getName());//model_name
                st.setString(2, x.getProduct());//product_code
                st.setString(3, x.getCompany());//Company code
                st.setInt(4, x.getUniqueId());//Unique ID
                st.setString(5, x.getDbname());                
                st.executeUpdate();
                st.close();         
            }
            stmt.close();
            conn.close();
            //retValue = "Created Model <b>" + x.getName() + "</b>, Product <b>" + x.getProduct() + "</b>, Company <b>" + x.getCompany()+ "</b> and DB <b>" + dynamoDbName + "</b> Sucessfully"; 
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
                retValue = "ERROR could not create Model <b>" + x.getName() + "</b>, Product <b>" + x.getProduct() + "</b>and Company <b>" + x.getCompany() + "</b> Sucessfully"; 
            }
        }        
        return retValue;
    }
    
    public Company getCompany(Company retCompany, String name) {
        try {
            ResultSet resultset = stmt.executeQuery("SELECT * FROM company Where comp_name='" + name + "'");
            if(resultset.next()) {
                retCompany.setId(resultset.getInt(1));
                retCompany.setName(resultset.getString(2));
                retCompany.setCode(resultset.getString(3));
                retCompany.setRep_name(resultset.getString(4));
                retCompany.setRep_email(resultset.getString(5));
                retCompany.setRep_phone_no(resultset.getString(6));
                retCompany.setAddress(resultset.getString(7));
            }            
        }catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        }       
        return retCompany;
    }
    
    public Product getProduct(Product retProduct, String name) {
        try {
            ResultSet resultset = stmt.executeQuery("SELECT * FROM product Where prod_name='" + name + "'");
            if(resultset.next()) {
                retProduct.setId(resultset.getInt(1));
                retProduct.setName(resultset.getString(2));
                retProduct.setCode(resultset.getString(3));
                retProduct.setDescription(resultset.getString(4));
            }            
        }catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        }       
        return retProduct;
    }
    
    public ArrayList<Company> getAllCompany() {
        ArrayList<Company> retData = new ArrayList<>();
        try {
            ResultSet resultset = stmt.executeQuery("select * from company");
            while(resultset.next()) {
                Company x = new Company();
                x.setName(resultset.getString("comp_name"));
                x.setCode(resultset.getString("comp_code"));
                x.setRep_name(resultset.getString("comp_pers"));
                x.setRep_email(resultset.getString("comp_email"));
                x.setRep_phone_no(resultset.getString("comp_phone"));
                x.setAddress(resultset.getString("comp_addr"));      
                retData.add(x);                
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        } 
        return retData;
    }
    
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> retData = new ArrayList<>();
        try {
            ResultSet resultset = stmt.executeQuery("select * from product");
            while(resultset.next()) {
                Product x = new Product();
                x.setName(resultset.getString("prod_name"));
                x.setCode(resultset.getString("prod_code"));
                x.setDescription(resultset.getString("prod_desc"));
                retData.add(x);                
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        } 
        return retData;
    }
    
    public ArrayList<Model> getAllModels() {
        ArrayList<Model> retData = new ArrayList<>();
        try {
            ResultSet resultset = stmt.executeQuery("select * from model");
            while(resultset.next()) {
                Model x = new Model();
                x.setName(resultset.getString("model_name"));
                x.setProduct(resultset.getString("model_prod_name"));
                x.setCompany(resultset.getString("model_comp_name"));
                x.setUniqueId(resultset.getInt("model_uniqueid"));
                x.setDbname(resultset.getString("model_db_name"));
                retData.add(x);                
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        } 
        return retData;
    }
    
    public String createLocalDatabase(String dBName, String compName, String prodName) {
        String retValue = "";
        String tNameLower = dBName.toLowerCase();// For Local Database the table should be Lower case only
        try {
            stmt.executeUpdate("CREATE TABLE `"+ tNameLower + "` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`SerialNum` VARCHAR(18) NOT NULL," +
                            "`Activation` VARCHAR(10) NOT NULL," +
                                    "`Aws` VARCHAR(32) NOT NULL," +
                                            "`Mobile` VARCHAR(32) NOT NULL," +
                                                    "`Mac` VARCHAR(17) NOT NULL," +
                                                        "`Dynamo` INT NOT NULL," +
                                                            "PRIMARY KEY (`id`)," +
                                                            "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
            
            PreparedStatement st;
            st = conn.prepareStatement("INSERT into "+tNameLower+" (SerialNum,Activation,Aws,Mobile,Mac,Dynamo) VALUES (?,?,?,?,?,?)");
            st.setString(1, "0000");//Serial
            st.setString(2, "0");//Activation
            st.setString(3, compName);//Aws
            st.setString(4, prodName);//Mobile
            st.setString(5, "NA");//Mac
            st.setInt(6, 1);//Dynamo 1 = No need Dynamo
            st.executeUpdate();
            st.close();
            stmt.close();        
        } catch (SQLException ex){
            if(ex.getMessage() != null) {
                System.out.println("SQL Error" + ex.getMessage());
            }
        }
        return retValue;
    }    
}
