/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.BookDAO;
import sample.daos.CategoryDAO;
import sample.dtos.AccountDTO;

/**
 *
 * @author ASUS
 */

public class AdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            AccountDTO u = (AccountDTO) session.getAttribute("user");
            if (u != null && u.getRole() == 1) {
                int i = 1;
                if (request.getParameter("i") != null) {
                    i = Integer.parseInt(request.getParameter("i"));
                }
                BookDAO bookDAO = new BookDAO();
                CategoryDAO categoryDAO = new CategoryDAO();
                String cid = request.getParameter("cid");
                int count = bookDAO.count();
                int endPage = count / 3;
                if (count % 3 != 0) {
                    endPage++;
                }
                String message = request.getParameter("message");
                if (message != null) {
                    request.setAttribute("message", message);
                }
                request.setAttribute("listBook", bookDAO.getTop3Book(i));
                request.setAttribute("listCategory", categoryDAO.getAllCategory());
                request.setAttribute("tag", cid);
                request.setAttribute("index", i);
                request.setAttribute("endPage", endPage);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
