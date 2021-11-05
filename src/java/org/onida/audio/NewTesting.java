/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suresh
 */
public class NewTesting extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String serSecCode = "ONTV121B400001";
            String actSecCode = "7361a29a1e";
            String mobSecCode = "8ae86069038b26de078fffeafdd2e464";
            String awsSecCode = "5d83f6ecbe415fc83f21e92acb76508f";        
            String macIdSecCode = "3C:71:BF:9D:9B:EC";
            String randStrSecCode = "11579532";
            int step = 4;
            int stepAwsMod = 7;
            
            String retValue = getSecurityCode(serSecCode, actSecCode, mobSecCode, awsSecCode, macIdSecCode, randStrSecCode, step, stepAwsMod);
            out.print(retValue);
        }
    }
    
    //Create Security Code
    private String getSecurityCode(String serSecCode, String actSecCode, String mobSecCode, String awsSecCode, String macIdSecCode, String randStrSecCode, int step, int stepAwsMod) {
        //SecureRandom random = new SecureRandom();
        //int rand = random.nextInt();
        //String randStrSecCode = Integer.toString(rand);
        //randStrSecCode = randStrSecCode.substring((randStrSecCode.length() - 8),randStrSecCode.length());
        //System.out.println(randStrSecCode);
        
        /*
        ONLY FOR TESTING        
        randStrSecCode = "12345678";//testing
        */
        
        String shaMacRanCode = getShaCode(serSecCode, actSecCode, macIdSecCode, randStrSecCode, "34");
        String shaAwsRanCode = getShaCode(serSecCode, actSecCode, awsSecCode, randStrSecCode, "34");
        String shaMobRanCode = getShaCode(serSecCode, actSecCode, mobSecCode, randStrSecCode, "34");
        String shaAwsMacCode = getShaCode(serSecCode, actSecCode, awsSecCode, macIdSecCode, "34");
        String shaMobMacCode = getShaCode(serSecCode, actSecCode, mobSecCode, macIdSecCode, "34");
        String shaDigest = shaMacRanCode + shaAwsRanCode + shaMobRanCode + shaAwsMacCode + shaMobMacCode;
        System.out.println("shaDigest before                 = " + shaDigest);
        //Generate random step
        //int step = random.nextInt(4);        
        
        /*
        ONLY FOR TESTING         
        step = 4;//TESTING
        */
        
        //step = step + 3;
        String stepStr = Integer.toString(step);        
        char[] stepChar = stepStr.toCharArray();
        System.out.println("Step = " + stepStr);
        //convert to Chararray
        char[] shaDigestChar = shaDigest.toCharArray();        
        //Encode Step @ position 3
        shaDigestChar[3] = stepChar[0];
//        System.out.println("shaDigestChar after step @ pos 3 = " + String.valueOf(shaDigestChar));
        //Encode RandomCode starting position 3 in steps of step
        char[] randCharSecCode = randStrSecCode.toCharArray();
        int insertPos = 3;
        for(int x = 0; x < 8; x++) {
            insertPos = insertPos + step;
            shaDigestChar[insertPos] = randCharSecCode[x];
        }
        //System.out.println("shaDigestChar after Random code  = " + String.valueOf(shaDigestChar));
        //Encode AwsCode, MobCode
        //Generate random step
        //int stepAwsMod = random.nextInt(7);
          
        /*
        ONLY FOR TESTING        
        stepAwsMod = 7;//TESTING
        */
        
        //stepAwsMod = stepAwsMod + 1;
        //Store step in location [2] in shaDigestchar
        shaDigestChar[2] = Integer.toString(stepAwsMod).toCharArray()[0];        
        char[] awsCharSecCode = awsSecCode.toCharArray();
        char[] mobCharSecCode = mobSecCode.toCharArray(); 
        for(int x = 0; x < 32; x++) {
            insertPos = insertPos + step;
            shaDigestChar[insertPos] =  awsCharSecCode[x];
            //insertPos = insertPos + stepAwsMod;
            shaDigestChar[insertPos + stepAwsMod] =  mobCharSecCode[x];
        }
        System.out.println("shaDigest after AWS amd Mob      = " + String.valueOf(shaDigestChar));
        String shaDigestCharStr = String.valueOf(shaDigestChar);
        return shaDigestCharStr;
        
    }
    
        //Create SHA-256 with SerialNumber, ActivationCode, MacId/AwsCode/MobileCode, randString/macId
    private String getShaCode(String serCode, String actCode, String secCode, String randCode, String splCode) {
        StringBuilder hexString = new StringBuilder();
        String shaCode = "";
        String shaPayLoad = "";
        //Create Pay Load
        String specialStr = "ksauvrae";//Special code
        for (int x = 0; x < 8; x++)
        {
            shaPayLoad = shaPayLoad + specialStr.substring(x, x + 1) + actCode.substring(x, x + 1) + randCode.substring(x, x + 1) + serCode.substring(x, x + 1) + secCode.substring(x, x + 1);
        }
        shaPayLoad = shaPayLoad + secCode + splCode;
//        System.out.println(shaPayLoad);
        try {
            MessageDigest mdAlgorithm = MessageDigest.getInstance("SHA-256");
            mdAlgorithm.update(shaPayLoad.getBytes());
            byte[] digest = mdAlgorithm.digest();

            for (int i = 0; i < digest.length; i++) {
                shaPayLoad = Integer.toHexString(0xFF & digest[i]);

                if (shaPayLoad.length() < 2) {
                    shaPayLoad = "0" + shaPayLoad;
                }
                hexString.append(shaPayLoad);
            }
            shaCode = hexString.toString();            
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
        
//        System.out.println(shaCode);
//        System.out.println();
        return shaCode;
    }
    
    
    
    
    
    
    
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
