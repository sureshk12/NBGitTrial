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
public class createIotThingsToBeDeleted {
    
    public void createIotThings() {
        
    }
    
    public static String createThings(String dbName, String compStr, String prodStr, String thingStr, int numThings) {
        String result="Invalid Operation";
        JSONObject js = new JSONObject();
        try {
        js.put("createNewTable", "NO");
        js.put("dbName", dbName);
        js.put("createFirstRow", "NO");
        js.put("company", compStr);
        js.put("product", prodStr);
        js.put("createNewThings", "YES");
        js.put("thingCode", thingStr);
        js.put("numOfDevices", numThings); 
        //js.put("", "");
        } catch (JSONException e) {
            e.toString();
        }
        try {
            //Service srTable = new Service();
            Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable"); 
            Service.setJsonInputString(js.toString());
            //Service.setApiKey("0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");                        
            result = Service.getOnidaIotDataString();
        }catch(RuntimeException e) {
            e.toString();
        }      
        return result;
    }
    
}
