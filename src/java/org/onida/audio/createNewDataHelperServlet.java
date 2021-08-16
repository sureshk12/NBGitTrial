/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sures
 */
public class createNewDataHelperServlet extends HttpServlet {

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
        
        String companyStr = request.getParameter("company_name");
        String companyCodeStr = request.getParameter("company_code");
        String personStr = request.getParameter("company_person");
        String emailStr = request.getParameter("company_email");
        String phoneStr = request.getParameter("company_phone");
        String addrStr = request.getParameter("company_address");
        String productStr = request.getParameter("product_name");
        String productCodeStr = request.getParameter("product_code");
        String descriptionStr = request.getParameter("product_description");
        String modelStr = request.getParameter("model_number");
        String stage = request.getParameter("stage");
        String compStr = request.getParameter("compStr");
        String prodStr = request.getParameter("prodStr");
        String compStrDrop = request.getParameter("dropDown1");
        String prodStrDrop = request.getParameter("dropDown2");
        
        ArrayList<String> sqlData = new ArrayList<String>();
        sqlData.add(request.getParameter("company_name"));//0
        sqlData.add(request.getParameter("company_code"));//1
        sqlData.add(request.getParameter("company_person"));//2
        sqlData.add(request.getParameter("company_email"));//3
        sqlData.add(request.getParameter("company_phone"));//4
        sqlData.add(request.getParameter("company_address"));//5
        sqlData.add(request.getParameter("product_name"));//6
        sqlData.add(request.getParameter("product_code"));//7
        sqlData.add(request.getParameter("product_description"));//8
        sqlData.add(request.getParameter("model_name"));//9
        sqlData.add(request.getParameter("stage"));//10
        sqlData.add(request.getParameter("compStr"));//11
        sqlData.add(request.getParameter("prodStr"));//12
        sqlData.add(request.getParameter("dropDown1"));//13
        sqlData.add(request.getParameter("dropDown2"));//14
        
        DatabaseHelper db = new DatabaseHelper();
        String retMsg = db.setData(sqlData);
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/create.jsp");
        request.setAttribute("createMsg", retMsg);
        RequetsDispatcherObj.forward(request, response);

            
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//                out.println("<head>");
//                    out.println("<title>Servlet createNewDataHelperServlet</title>");            
//                out.println("</head>");
//                out.println("<body>");
//                    out.println("<h1>Servlet createNewDataHelperServlet at " + request.getContextPath() + "</h1>");
//                    out.println("company_name : " + companyStr);
//                    out.println("<br>company_person : " + personStr);
//                    out.println("<br>company_email : " + emailStr);
//                    out.println("<br>company_phone : " + phoneStr);
//                    out.println("<br>company_address : " + addrStr);
//                    out.println("<br>product_name : " + productStr);
//                    out.println("<br>product_description : " + descriptionStr);
//                    out.println("<br>model_number : " + modelStr);
//                    out.println("<br>stage : " + stage);
//                    out.println("<br>compStr : " + compStr);
//                    out.println("<br>prodStr : " + prodStr);
//                out.println("</body>");
//            out.println("</html>");
//        } 
//        
        
        

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
