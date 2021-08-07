/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;
/**
 *
 * @author suresh kumar
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeviceData {
    
    public void deviceData() {

    }

    public String getMobileCode(String deviceSlnoStr, String activationCodeStr) {
        String returnValue = "";        
        //http://localhost/OnidaAuDataV0//mobCodeServlet?key=24068cba94e2fef4b5e09fe7ce2cf4fc342471e2&deviceSlno=11O9190001&activationCode=ED25843042

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/suresh", "root", "Vidya@100");
            Statement stmt = conn.createStatement();            
            ResultSet resultset = stmt.executeQuery("select * from auddata");            
            while(resultset.next()) {
                if(deviceSlnoStr.equals(resultset.getString("SerialNumber"))) {
                    if(activationCodeStr.equals(resultset.getString("ActivationCode"))) {
                        returnValue = resultset.getString("MobileCode");
                    }
                }            
            }
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("System error");
        }
        return returnValue;
    }
}
