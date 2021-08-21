/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.PaymentDAO;
import sample.dtos.AccountDTO;
import sample.dtos.PaymentDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryServlet"})
public class HistoryServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            AccountDTO u = (AccountDTO) session.getAttribute("user");
            PaymentDAO dao = new PaymentDAO();
            if (u != null) {
                if (action == null) {
                    if (u.getRole() == 0) {
                        String userId = u.getUserId();
                        ArrayList<PaymentDTO> listPayment = dao.getPaymentByUserId(userId);
                        if (!listPayment.isEmpty()) {
                            request.setAttribute("listPayment", listPayment);
                        } else {
                            request.setAttribute("message", "Don't have history of payment");
                        }
                    } else {
                        request.setAttribute("listPayment", dao.getAllPayment());
                    }
                request.getRequestDispatcher("history.jsp").forward(request, response);
                } else if (action.equals("detail")) {
                    if (u.getRole() == 0) {
                        String userId = u.getUserId();
                        request.setAttribute("listPayment", dao.getPaymentByUserId(userId));
                    } else {
                        request.setAttribute("listPayment", dao.getAllPayment());
                    }
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("listDetail", dao.getPaymentDetail(id));
                request.getRequestDispatcher("history.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "login first");
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
