/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sures
 */
public class Service {
    //private static final String apiKey="0ae09eed4f3c024451ads12d1gsgsg1sg";//change this with yours
    private static final String baseUrl="https://uaxjpzyru2.execute-api.us-east-1.amazonaws.com/live/kumar";
    //The constant url should be stored in config file
    
    //Get the weather of the specific city
    public static String getOnidaIotDataString() throws RuntimeException{  
        
        //define a variable to store the weather api url and set beijing as it's default value
        String baiduUrl = baseUrl;  

        StringBuilder strBuf = new StringBuilder();  
        
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        try{  
            //Declare the connection to weather api url
            URL url = new URL(baiduUrl);  
            conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-api-key","0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");
            
//            conn.setRequestProperty("aws_access_key_id","AKIATVYOXXILILYBEV44");
//            conn.setRequestProperty("aws_secret_access_key","OX5nZXsygKXmXBIUZrRCVl7LNcG5TWI9QuZbeaSeapikey");

            //Send Data
            conn.setDoOutput(true);
            String jsonInputString = "{'name': 'Suresh', 'job': 'Programmer'}";
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : " + conn.getResponseCode());
            }
            
            //Read the content from the defined connection
            //Using IO Stream with Buffer raise highly the efficiency of IO
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;  
            while ((output = reader.readLine()) != null)  
                strBuf.append(output);  
        }catch(MalformedURLException e) {  
            e.printStackTrace();   
        }catch(IOException e){  
            e.printStackTrace();   
        } 
        finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }

        return strBuf.toString();  
    }    
}
