/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Suresh
 */
public class CreateDbTable {
    
    public static String createTable(String tableName, String compName, String prodName) {
        
        String retValue ="";
        JSONObject js = new JSONObject();
        try {
        js.put("createNewTable", "YES");
        js.put("dbName", tableName);
        js.put("createFirstRow", "NO");
        js.put("company", compName);
        js.put("product", prodName);
        js.put("createNewThings", "NO");
        js.put("numOfDevices", 2);
        //js.put("", "");
        } catch (JSONException e) {
            e.toString();
            return "ERROR Json";
        }
        try {
            Service srTable = new Service();
            Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");                        
            Service.setJsonInputString(js.toString());
            //Service.setApiKey("0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");                        
            String retValueStr = Service.getOnidaIotDataString();
            System.out.print(retValueStr + "<br>");
        }catch(RuntimeException e) {
            e.toString();
            retValue =  "ERROR could not create Dynamo Table(s)";
        }
        
        if(retValue.equals("")) {
            System.out.print(retValue + "<br><br><br>");        
            try {
                Thread.sleep(15000);
                Service srFirstLine = new Service();
                Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");
                js.put("createNewTable", "NO");
                js.put("createFirstRow", "YES");
                Service.setJsonInputString(js.toString());
                String retValueStr = Service.getOnidaIotDataString();
                System.out.print(retValueStr + "<br>");
            } catch (InterruptedException | RuntimeException | JSONException e) {
                e.toString();
                retValue =  "Error Could not create the First line";
            }
        }
        return retValue;
    }
    


    public static String thingsUpdate(String serial, String activation, String aws, String mobile) {
        String returnVal = "ERROR";
        JSONObject js = new JSONObject();
        try {
            js.put("createNewTable", "NO");
            js.put("dbName", (ThingsDashBoard.thingsDbName));
            js.put("createFirstRow", "NO");
            js.put("company", ThingsDashBoard.company);
            js.put("product", ThingsDashBoard.product);
            js.put("createNewThings", "YES");
            js.put("numOfDevices", "1");
            js.put("serial", serial);
            js.put("activation", activation);
            js.put("aws", aws);
            js.put("mobile", mobile);
        //js.put("", "");
        } catch (JSONException e) {
            e.toString();
            return "ERROR Json";
        }
        try {
            Service srFirstLine = new Service();
            Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");
            Service.setJsonInputString(js.toString());
            returnVal = Service.getOnidaIotDataString();
            System.out.print(returnVal + "<br>");
        } catch (RuntimeException  e) {
            e.toString();
            return "ERROR Could not create Thing";
        }
        return returnVal;
    }

}
