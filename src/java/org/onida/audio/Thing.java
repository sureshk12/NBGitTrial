/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Suresh
 */
public class Thing {
    private String serialNumber;
    private String activationCode;
    private String awsCode;
    private String mobileCode;
    private String macCode;
    private int dynamo;
    
    public void Thing(){
        
    }
    
    public void createThing(String serial, String activation, String aws, String mobile, String mac, int dynamoStatus){
        this.serialNumber = serial;
        this.activationCode = activation;
        this.awsCode = aws;
        this.mobileCode = mobile;
        this.macCode = mac;
        this.dynamo = dynamoStatus;
    }
    
    /*
        This function 
    
    */
    public static String storeThing(String mac) {
        ArrayList arrList = ThingsDashBoard.getNonRegThings(ThingsDashBoard.thingsDbName, true);
        String activation = getRandNum(5);
        String aws = getRandNum(16);
        String mobile = getRandNum(16);
        //Generate Serial number
        Thing thg = (Thing)arrList.get(0);
        int serNum = Integer.parseInt(thg.getActivationCode())+1;//actually activation code in first row has no of devices
        String sNum = Integer.toString(serNum + 100000);
        //Generate data String
//        Date today = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
        String arrFormat = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String tStr = ft.format(today);
        LocalDate cDate = LocalDate.now();
        String cDStr = cDate.toString();
        String yStr = cDStr.substring(2,4);
        int mPos = Integer.parseInt(cDStr.substring(5,7));
        String mStr = arrFormat.substring(mPos, mPos+1);
        int dPos = Integer.parseInt(cDStr.substring(8,10));
        String dStr = arrFormat.substring(dPos, dPos+1);

        String serial = ThingsDashBoard.thingsName.toUpperCase() + yStr + mStr + dStr + sNum.substring(1,6);
        
        String retMsg = "";
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onida_iot", "root", "Vidya@100");            
            stmt = conn.createStatement();
            PreparedStatement st;
            
            //Store new Thing
            st = conn.prepareStatement("INSERT into "+ThingsDashBoard.thingsDbName+" (SerialNum,Activation,Aws,Mobile,Mac,Dynamo) VALUES (?,?,?,?,?,?)");
            st.setString(1, serial);//Serial
            st.setString(2, activation);//Activation
            st.setString(3, aws);//Aws
            st.setString(4, mobile);//Mobile
            st.setString(5, mac);//Mac
            st.setInt(6, 0);//Mac 
            st.executeUpdate();
            //Store new Count
            String serNumStr = Integer.toString(serNum);
            st = conn.prepareStatement("UPDATE "+ThingsDashBoard.thingsDbName+" SET Activation='"+serNumStr+"' WHERE SerialNum='0000'");
            st.executeUpdate();
            //retMsg = "OK"; 
            retMsg = serial +","+activation+","+aws+","+mobile+","+mac;
            
            st.close();
            stmt.close();
            conn.close(); 
        } catch (SQLException ex) {
            System.out.println(" System Error");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("System error");
        }
        return retMsg;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @return the activationCode
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * @return the awsCode
     */
    public String getAwsCode() {
        return awsCode;
    }

    /**
     * @return the mobileCode
     */
    public String getMobileCode() {
        return mobileCode;
    }

    /**
     * @return the macCode
     */
    public String getMacCode() {
        return macCode;
    }

    /**
     * @return the dynamo
     */
    public int isDynamo() {
        return getDynamo();
    }
    
    public static String getRandNum(int x) {
        String result = "";
        try {
            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] randomBytes = new byte[x];//was 128
            secureRandomGenerator.nextBytes(randomBytes);
            //StringBuilder result = new StringBuilder();

            for (byte temp : randomBytes) {
                //result.append(String.format("%02x", temp));
                result += String.format("%02x", temp);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e){
            result = "ERROR";
        }
        return result;        
    }

    /**
     * @return the dynamo
     */
    public int getDynamo() {
        return dynamo;
    }
    
    
}
    



