/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onida.audio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suresh
 */
public class CreateCompanySevlet extends HttpServlet {

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
        String retValue = "";
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String repName = request.getParameter("rep_name");
            String repEmail = request.getParameter("rep_email");
            String repPhone = request.getParameter("rep_phone");
            String address = request.getParameter("address");
            
            if((!retValue.equals("")) ||(name == null) || (name.equals("")) || (name.length() > 30) || (name.length() < 3)) {
                retValue = "Error in Name";
            } else if((!retValue.equals("")) ||(code == null) || (code.equals("")) || (code.length() > 2) || (name.length() < 2)) {
                retValue = "Error in Code";
            } else if((!retValue.equals("")) ||(repName == null) || (repName.equals("")) || (repName.length() > 30) || (repName.length() < 3)) {
                retValue = "Error in Representive Name";
            } else if((!retValue.equals("")) ||(repEmail == null) || (repEmail.equals("")) || (repEmail.length() > 30) || (repEmail.length() < 3)) {
                retValue = "Error in Email";
            } else if((!retValue.equals("")) || !repEmail.contains("@") || !repEmail.contains(".")) {
                retValue = "Error in Email";
            } else if((!retValue.equals("")) || repPhone.length() != 10) {
                retValue = "Error in Phone number";
            }  else if ((!retValue.equals("")) || (address.length() < 3)) {
                retValue = "Error in Address";
            }
            
            if(!retValue.equals("")) {
                RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/createCompany.jsp");
                request.setAttribute("msg", retValue);
                RequetsDispatcherObj.forward(request, response);
            } else {
                Company x = new Company();
                x.setCompany(name, code, repName, repEmail, repPhone, address);
                String msg = x.createNewCompany(x);
                RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/create.jsp");
                request.setAttribute("msg", msg);
                RequetsDispatcherObj.forward(request, response);
            }
            
            
                    
            
            
            
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CreateCompanySevlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CreateCompanySevlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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

}
