/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.daos.BookDAO;
import sample.dtos.BookDTO;
import javax.servlet.http.HttpSession;
import sample.daos.CategoryDAO;
import sample.dtos.AccountDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final float MIN_PRICE = 1;
    private final float MAX_PRICE = 10000;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            BookDAO bookDAO = new BookDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            AccountDTO a = (AccountDTO) session.getAttribute("user");
            String cid = request.getParameter("cid");
            String txtSearch = request.getParameter("txtSearch");
            String min = request.getParameter("min");
            String max = request.getParameter("max");

            float minPrice = MIN_PRICE;
            float maxPrice = MAX_PRICE;

            if (min != null && !min.isEmpty()) {
                try {
                    minPrice = Float.parseFloat(min);
                } catch (NumberFormatException ex) {
                    minPrice = MIN_PRICE;
                }
                if (minPrice < MIN_PRICE) {
                    minPrice = MIN_PRICE;
                }
            }

            if (max != null && !max.isEmpty()) {
                try {
                    maxPrice = Float.parseFloat(max);
                } catch (NumberFormatException ex) {
                    maxPrice = MAX_PRICE;
                }
                if (maxPrice > MAX_PRICE) {
                    maxPrice = MAX_PRICE;
                }
            }
            List<BookDTO> listBook = null;
            if (a != null) {
                if (a.getRole() == 0) {
                    if (min != null && max != null) {
                        listBook = bookDAO.getBookByPrice(minPrice, maxPrice);
                        request.setAttribute("listBook", listBook);
                    } else if (txtSearch != null) {
                        listBook = bookDAO.getBookByName(txtSearch);
                        if (!listBook.isEmpty()) {
                            request.setAttribute("listBook", listBook);
                        } else {
                            request.setAttribute("message", "Can not found book!");
                        }
                    }
                    request.setAttribute("min", min);
                    request.setAttribute("max", max);
                    request.setAttribute("txtSearch", txtSearch);
                    request.setAttribute("index", 1);
                    request.setAttribute("endPage", 1);
                    request.setAttribute("listCategory", categoryDAO.getAllCategory());
                    request.setAttribute("tag", cid);
                    request.getRequestDispatcher("user.jsp").forward(request, response);
                } else {
                    if (min != null && max != null) {
                        listBook = bookDAO.getBookByPrice(minPrice, maxPrice);
                        request.setAttribute("listBook", listBook);
                    } else if (txtSearch != null) {
                        BookDTO b = bookDAO.getBookById(txtSearch);
                        listBook = bookDAO.getBookByName(txtSearch);
                        if (b != null) {
                            listBook = new ArrayList<>();
                            listBook.add(b);
                            request.setAttribute("listBook", listBook);
                        } else if (!listBook.isEmpty()) {
                            request.setAttribute("listBook", listBook);
                        } else {
                            request.setAttribute("message", "Can not found book!");
                        }
                    }
                    request.setAttribute("min", min);
                    request.setAttribute("max", max);
                    request.setAttribute("txtSearch", txtSearch);
                    request.setAttribute("index", 1);
                    request.setAttribute("endPage", 1);
                    request.setAttribute("listCategory", categoryDAO.getAllCategory());
                    request.setAttribute("tag", cid);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
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
