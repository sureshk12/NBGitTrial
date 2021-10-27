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
 * @author Suresh
 */
public class CreateModelServlet extends HttpServlet {

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
            String retValue = "";
            
            String company = request.getParameter("company");
            String product = request.getParameter("product");
            String model = request.getParameter("model");          
            
            Company c = new Company();
            c.getCompany(company);
            Product p = new Product();
            p.getProduct(product);
            if(c.getName() != null || p.getName() != null) {
                String dbName = c.getCode()+p.getCode();
                Model m = new Model();
                
                //Check if combination of company, product, model is already existing
                //also check if DB available
                int chkDb = 0; // 0 = db not avaible to be created, 1 = db available
                ArrayList<Model> models = m.getAllModels();
                for(int i = 0; i < models.size(); i++) {
                    if(models.get(i).getDbname().equals(dbName)) {
                        chkDb++;//if DB availble set flag to 1
                    }
                    if(models.get(i).getCompany().equals(c.getName())) {
                        if(models.get(i).getProduct().equals(p.getName())) {
                            if(models.get(i).getName().equals(model)){
                                //Error
                                retValue = "Error : Comapany <b>" + c.getName() + "</b> product <b>" + p.getName() + "</b> Model <b>" + model + "</b> Existing";
                            }
                        }
                    }
                }                
                //Create Model
                if(retValue.equals("")){
                    m.setModel(model, c.getName(), p.getName(), chkDb + 1, dbName);
                    retValue = m.createModel(m);//retValue = "" if Error!!!                    
                }
                //Create Dynamo Data base
                if(retValue.equals("")) {
                    if(chkDb == 0) {
                        retValue = CreateDbTable.createTable(dbName, c.getName(), p.getName());
                        if(retValue.equals("")) {
                            DatabaseHelper db = new DatabaseHelper();
                            retValue = db.createLocalDatabase(dbName, c.getName(), p.getName());
                        }
                    }
                }               
            } else {
                retValue = "Error in Codes";
            }            
            
            if(!retValue.equals("")) {
                RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/createModel.jsp");
                request.setAttribute("msg", retValue);
                RequetsDispatcherObj.forward(request, response);
            } else {
                RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/create.jsp");
                request.setAttribute("msg", "Created Model with Company <b>" + c.getName() + "</b>, Product <b>" + p.getName() + "</b> and Model <b>" + model + "</b><br>");
                RequetsDispatcherObj.forward(request, response);
            } 
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
