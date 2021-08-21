/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
        
        try {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("signUpUsername");
            String password = request.getParameter("signUpPassword");
            String confirm = request.getParameter("confirmPass");
            AccountDAO dao = new AccountDAO();
            AccountDTO acc = new AccountDTO(username, password, fullname, 0);
            
            String[] ErrMsg = {
                "Full name must longer than 3 characters",
                "Please choose a unique username",
                "Password must longer than 6 characters",
                "Confirm password is not same as password"
            };
            
            boolean bIsValidate = true;
            bIsValidate = IsValidate(fullname, username, password,
            confirm, dao,ErrMsg);

           /* if (dao.getAccountById(username) == null) {
                if (password.equals(confirm)) {*/
                if(bIsValidate){
                    if (dao.signUp(acc) != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", acc);
                        response.sendRedirect("UserServlet");
                    }
                }else{
                    request.setAttribute("ErrSignUp", ErrMsg);
                }
                request.setAttribute("context", "SignUpServlet");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    
    //[7-July-2021][Start] Add validation feature ---->
    public boolean IsValidate(String fullname, String username, String password,
                            String confirm, AccountDAO dao, String[] outErrMsg) 
            throws ClassNotFoundException, SQLException
    {
        
        boolean bCheck = true;   
        
        if(fullname.isEmpty() || username.isEmpty() || password.isEmpty() 
                || confirm.isEmpty() ){
            return false;
        }
           
        int iIndex = 0;    
        if (fullname.length() < 3) {
            bCheck = false;
        }else{
            outErrMsg[iIndex] = null;
        }
        
        iIndex++;
        if (dao.getAccountById(username) != null) {
            bCheck = false;
        }else{
            outErrMsg[iIndex] = null;
        }
        

        iIndex++;
        if (password.length() < 6) {
            bCheck = false;
        }else{
            outErrMsg[iIndex] = null;  
        }
        
        iIndex++;
        if (!confirm.equals(password)) {
            bCheck = false;
        }else{
            outErrMsg[iIndex] = null;
        }
       
                  
        return bCheck;
    }
    //[7-July-2021][End] Add validation feature <----

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
