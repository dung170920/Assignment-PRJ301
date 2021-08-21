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
import javax.servlet.http.HttpSession;
import sample.daos.AccountDAO;
import sample.dtos.AccountDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "InfoServlet", urlPatterns = {"/InfoServlet"})
public class InfoServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            AccountDTO u = (AccountDTO) session.getAttribute("user");
            if (u != null) {
                String action = request.getParameter("action");
                if (action == null) {
                    String message = request.getParameter("message");
                    if (message != null) {
                        request.setAttribute("message", message);
                    }
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                } else if (action.equals("change")) {
                    String fullname = request.getParameter("name");
                    String pass = request.getParameter("changePass");
                    String confirmPass = request.getParameter("confirm");
                    AccountDAO dao = new AccountDAO();

                    if (fullname.trim().isEmpty()) {
                        u.setFullname(u.getFullname());
                    } else {
                        u.setFullname(fullname);
                    }
                    if (pass.trim().isEmpty()) {
                        u.setPassword(u.getPassword());
                    } else {
                        u.setPassword(pass);
                    }
                    if (confirmPass.trim().isEmpty() && !pass.isEmpty()) {
                        request.setAttribute("message", "Confirm password can not empty");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    }
                    if (!pass.equals(confirmPass)) {
                        request.setAttribute("message", "Password and confirm password are not same");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    }
                    String message = "";
                    if (dao.update(u) == true) {
                        message = "Update successful";
                    } else {
                        message = "Update failed";
                    }
                    session.setAttribute("user", u);
                    response.sendRedirect("InfoServlet?message=" + message);
                }
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
