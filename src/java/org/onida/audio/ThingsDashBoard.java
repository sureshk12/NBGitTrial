/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Suresh
 */
public class ThingsDashBoard {
    public static String company;
    public static String product;
    public static String model;
    public static String thingsName; 
    public static String thingsDbName;
    public static ArrayList<Thing> arrDisplayThings = new ArrayList<>();

    
    public void ThingsDashBoard() {
        //Constructor              
    }
    
    public static ArrayList getNonRegThings(String thingsDbName, boolean count) {
        ArrayList<Thing> nonRegThings = new ArrayList<>();
        Connection conn;
        Statement stmt;     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onida_iot", "root", "Vidya@100");            
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + thingsDbName);
            //ResultSet rs = stmt.executeQuery("select * from company");
            //ResultSet rs = stmt.executeQuery("SELECT * FROM company WHERE comp_name='"+ compName+"'");
   
            while(rs.next()) {
                Thing thing = new Thing();
                thing.createThing(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                nonRegThings.add(thing);
                if(count) {
                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();           
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("System error");
        }         
        
        return nonRegThings;
    }
    
}
