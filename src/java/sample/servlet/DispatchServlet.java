/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MIMI
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";   
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String ADMIN_CONTROLLER = "AdminServlet";
    private final String USER_CONTROLLER = "UserServlet";
    private final String CART_CONTROLLER = "CartServlet";
    private final String CATEGORY_CONTROLLER = "CategoryServlet";
    private final String DETAIL_CONTROLLER = "DetailServlet";
    private final String HISTORY_CONTROLLER = "HistoryServlet";
    private final String INFO_CONTROLLER = "InfoServlet";
    private final String PAYMENT_CONTROLLER = "PaymentServlet";
    private final String SIGNUP_CONTROLLER = "SignUpServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String CREATE_FORM_CONTROLLER = "CreateFormServlet";
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) {
                //do nothing
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Delete")) {
                String id = request.getParameter("id");
                url = DELETE_CONTROLLER + "?id=" + id;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("CreateForm")) {
                url = CREATE_FORM_CONTROLLER;
            } else if (button.equals("Cart")) {
                url = CART_CONTROLLER;
            } else if (button.equals("Remove")) {
                url = CART_CONTROLLER + "?action=remove";
            } else if (button.equals("Add")) {
                String id = request.getParameter("id");
                url = CART_CONTROLLER + "?action=add&id=" + id;
            } else if (button.equals("Sub")) {
                String id = request.getParameter("id");
                url = CART_CONTROLLER + "?action=sub&id=" + id;
            } else if (button.equals("Admin")) {
                String index = request.getParameter("i");
                if (index == null) {
                    url = ADMIN_CONTROLLER;
                } else {
                    url = ADMIN_CONTROLLER + "?i=" + index;
                }
            } else if (button.equals("User")) {
                String index = request.getParameter("i");
                if (index == null) {
                url = USER_CONTROLLER;
                } else {
                    url = USER_CONTROLLER + "?i=" + index;
                }
            } else if (button.equals("Category")) {
                String cid = request.getParameter("cid");
                url = CATEGORY_CONTROLLER + "?cid=" + cid;
            } else if (button.equals("Detail")) {
                url = DETAIL_CONTROLLER;
            } else if (button.equals("History")) {
                url = HISTORY_CONTROLLER;
            } else if (button.equals("HistoryDetail")) {
                String id = request.getParameter("id");
                url = HISTORY_CONTROLLER + "?action=detail" + "&id=" + id;
            }else if (button.equals("Info")) {
                url = INFO_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Payment")) {
                url = PAYMENT_CONTROLLER;  
            } else if (button.equals("SignUp")) {
                url = SIGNUP_CONTROLLER;
            } else if (button.equals("Change")) {
                url = INFO_CONTROLLER + "?action=change";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            log("Error at " + this.getServletName() + ": " + ex.getMessage());
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
