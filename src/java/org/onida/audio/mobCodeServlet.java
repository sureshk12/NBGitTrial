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
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.onida.audio.DeviceData;

/**
 *
 * @author sures
 */
public class mobCodeServlet extends HttpServlet {
    
//    private DeviceData deviceData;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        deviceData = new DeviceData();

    }

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
        PrintWriter out = response.getWriter();
        try{
            String keyValueStr = request.getParameter("key");
            String deviceSlnoStr = request.getParameter("deviceSlno");
            String activationCodeStr = request.getParameter("activationCode");
            String splNum = "76";
            String randNumberString = "";//"54467188";
            //Get Step
            int step = keyValueStr.charAt(3) - 48;//5
            //Get Randam Number
            for(int x = 0; x < 8; x++){
                randNumberString = randNumberString + keyValueStr.substring((3+step)+(x * step), (3+step)+(x * step)+1);
            }           
            //Get Code
            String codedSha256 = sha256(deviceSlnoStr, activationCodeStr, randNumberString, splNum);            
            char[] codedSha256Char = codedSha256.toCharArray();
            //Recode with Step
            codedSha256Char[3] = Integer.toString(step).charAt(0);
            //Recode with Random Number
            for(int x = 0; x < 8; x++) {
                codedSha256Char[(3+step)+(x*step)] = randNumberString.charAt(x);
            }
            String finalShaStr = String.valueOf(codedSha256Char);
            
            //Get data from server
            DeviceData deviceData = new DeviceData();

            if(keyValueStr.equals(finalShaStr)) {
                if(!deviceData.getMobileCode(deviceSlnoStr, activationCodeStr).equals("")) {
                    out.println(deviceData.getMobileCode(deviceSlnoStr, activationCodeStr));
                } else {
                    out.println("E");
                }
            } else {
                out.println("E");
            }        
        } catch (Exception e) {
            System.out.println(e.toString());
            out.println("E");
        }
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

    private String sha256(String serNo, String actCode, String randNum, String splNum) {
        //?key=a535544b50a6f401fb449406bbac770651e23e8c0c68ea82e024fd2ad88e94b8&deviceSlno=11Q4210001&activationCode=42ebaf02da
        //k451s241ae4Qub64va72rf11a080e28076
        //11Q4210001
        //42ebaf02da
        //54467188
        //5
        //76
        //a535544bd0a6fb01fb64940fbbac870655e23efc0c61ea82e024fd2ad88e94b8
        //a535544b50a6f401fb449406bbac770651e23e8c0c68ea82e024fd2ad88e94b8
        String specialStr = "ksauvrae";
        StringBuilder hexString = new StringBuilder();
        String shaCode = "";
        String payLoad = "";
        for(int x = 0; x < 8 ; x++) {
           payLoad = payLoad + specialStr.substring(x, x+1) + actCode.substring(x, x+1) +
                   randNum.substring(x, x+1) + serNo.substring(x, x+1);
        }
        payLoad = payLoad + splNum;
        String pwd = payLoad;
        try {
            MessageDigest mdAlgorithm = MessageDigest.getInstance("SHA-256");
            mdAlgorithm.update(pwd.getBytes());
            byte[] digest = mdAlgorithm.digest();

            for (int i = 0; i < digest.length; i++) {
                pwd = Integer.toHexString(0xFF & digest[i]);

                if (pwd.length() < 2) {
                    pwd = "0" + pwd;
                }
                hexString.append(pwd);
            }
            shaCode = hexString.toString();          
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
        System.out.println(shaCode);
        return shaCode;
    }
    
    
    
    
//    private String md5() {
//        StringBuilder hexString = new StringBuilder();
//        try {
//            TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
//            String dateFormat = "dd-MMM-yyyy G"; 
//            String dateFormatMd5 = "ddMMMyyyy"; 
//
//            Date todayDate = new Date();
//
//            DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
//            DateFormat md5 = new SimpleDateFormat(dateFormatMd5);
//
//            todayDateFormat.setTimeZone(timeZone);
//            md5.setTimeZone(timeZone);
//
//            String md5c = md5.format(todayDate);
//
//            //System.out.println(md5c);
//            String coding = "Suresh"+md5c.substring(8,9)+
//                    md5c.substring(1,2)+md5c.substring(6,7)+md5c.substring(7,8)+
//                    md5c.substring(3,4)+"Kaval"+md5c.substring(2,3)+
//                    md5c.substring(5,6)+md5c.substring(0,1)+md5c.substring(4,5);
//
//            //out.println(coding+"<br>");
//
//            String plainText = coding;
//            MessageDigest mdAlgorithm = MessageDigest.getInstance("SHA-1");
//            mdAlgorithm.update(plainText.getBytes());
//            byte[] digest = mdAlgorithm.digest();
//            
//            for (int i = 0; i < digest.length; i++) {
//                plainText = Integer.toHexString(0xFF & digest[i]);
//
//                if (plainText.length() < 2) {
//                    plainText = "0" + plainText;
//                }
//                hexString.append(plainText);
//            }
//            md5Code = hexString.toString();
//            //out.println("<br>"+coding);
//            //out.println("<br>"+hexString.toString());
//            System.out.println("<br>"+coding);
//            System.out.println("<br>"+hexString.toString());            
//        } catch (NoSuchAlgorithmException e) {
//            System.out.println(e.toString());
//        }
//        return hexString.toString();
//    }
//    
//    public String getTodaysDate() {
//        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
//        String dateFormat = "dd-MMM-yyyy G";            
//        DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
//        todayDateFormat.setTimeZone(timeZone);           
//        return(","+todayDateFormat.format(new Date())+",");      
//    }
}
