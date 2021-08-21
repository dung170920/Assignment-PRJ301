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
import sample.daos.BookDAO;
import sample.dtos.AccountDTO;
import sample.dtos.BookDTO;
import sample.dtos.CartItemDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            AccountDTO u = (AccountDTO) session.getAttribute("user");
            if (u != null && u.getRole() == 0) {
                String action = request.getParameter("action");
                if (action == null) {
                    displayCart(request, response);
                } else if (action.equalsIgnoreCase("add")) {
                    addItem(request, response);
                } else if (action.equalsIgnoreCase("sub")) {
                    subItem(request, response);
                } else if (action.equalsIgnoreCase("empty")) {
                    emptyCart(request, response);
                } else if (action.equalsIgnoreCase("remove")) {
                    removeItems(request, response);
                }
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("message", "Your cart is empty now");
        }
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void subItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        int quantity = cart.get(index).getQuantity() - 1;
        if (quantity > 0) {
            cart.get(index).setQuantity(quantity);
        } else {
            cart.remove(index);
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("CartServlet");
    }

    protected void removeItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("cart");
            for (String selected : request.getParameterValues("select")) {
                int index = isExisting(selected, cart);
                cart.remove(index);
            }
            session.setAttribute("cart", cart);
            response.sendRedirect("CartServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void emptyCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("cart");
        response.sendRedirect("CartServlet");
    }

    protected void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO dao = new BookDAO();
        HttpSession session = request.getSession();
        try {
            BookDTO b = dao.getBookById(request.getParameter("id"));
            if (b.getQuantity() > 0) {
                if (session.getAttribute("cart") == null) {
                    ArrayList<CartItemDTO> cart = new ArrayList<>();
                    CartItemDTO item = new CartItemDTO(b, 1);
                    cart.add(item);
                    session.setAttribute("cart", cart);
                } else {
                    ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("cart");
                    int index = isExisting(request.getParameter("id"), cart);
                    if (index == -1) {
                        CartItemDTO item = new CartItemDTO(b, 1);
                        cart.add(item);
                    } else {
                        int quantity = cart.get(index).getQuantity() + 1;
                        cart.get(index).setQuantity(quantity);
                    }
                    session.setAttribute("cart", cart);
                }
                response.sendRedirect("CartServlet");
            } else {
                String message = "Out of stock";
                response.sendRedirect("UserServlet?message=" + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int isExisting(String id, ArrayList<CartItemDTO> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBookId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
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
