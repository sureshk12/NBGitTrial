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
    
    public static String createTable(String tableName) {
    
        String result="Invalid Operation";
        JSONObject js = new JSONObject();
        try {
        js.put("createNewTable", "YES");
        js.put("dbName", "ONTV");
        js.put("createFirstRow", "NO");
        js.put("company", "Onida");
        js.put("product", "Television");
        js.put("createNewThings", "NO");
        js.put("numOfDevices", 2);
        //js.put("", "");
        } catch (JSONException e) {
            e.toString();
        }
        try {
            Service srTable = new Service();
            Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");                        
            Service.setJsonInputString(js.toString());
            //Service.setApiKey("0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");                        
            result = Service.getOnidaIotDataString();
        }catch(RuntimeException e) {
            e.toString();
            return "ERROR could not create Table";
        }
        System.out.print(result + "<br><br><br>");        
        try {
            Thread.sleep(15000);
            Service srFirstLine = new Service();
            Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");    
            js.put("createNewTable", "NO");
            js.put("createFirstRow", "YES");
            Service.setJsonInputString(js.toString());
            result = Service.getOnidaIotDataString();
            System.out.print(result + "<br>");
        } catch (InterruptedException | RuntimeException | JSONException e) {
            e.toString();
            return "Error Could not create the First line";
        }
        return "OK";
    }    
}
