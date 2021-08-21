/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sample.daos.BookDAO;
import sample.daos.CategoryDAO;
import sample.dtos.AccountDTO;
import sample.dtos.BookDTO;
import sample.dtos.CategoryDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "photo";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            AccountDTO u = (AccountDTO) session.getAttribute("user");
            if (u != null && u.getRole() == 1) {
                String image = uploadFile(request);
                String bookId = request.getParameter("id");
                String bookName = request.getParameter("name");
                String description = request.getParameter("description");
                String author = request.getParameter("author");
                String Year = request.getParameter("year");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                String categoryID = request.getParameter("category");
                
                String[] strInput = {image, bookId, bookName, description, 
                quantity, Year, author,price};
                String[] ErrMsg = new String[strInput.length];
                 CategoryDAO categoryDAO = new CategoryDAO();
                request.setAttribute("listCategory", categoryDAO.getAllCategory());
                
                boolean bIsValidate = true;
                bIsValidate = IsValidate(strInput, ErrMsg);
                if(bIsValidate == false){
                    request.setAttribute("ErrCreateMsg",ErrMsg);
                    request.getRequestDispatcher("create.jsp").forward(request, response);
                }else{ 
                    BookDAO dao = new BookDAO();
                    CategoryDTO category = new CategoryDTO(categoryID);

                    BookDTO book = new BookDTO(bookId, description, Float.parseFloat(price), bookName, author, Integer.parseInt(Year), Integer.parseInt(quantity), image, category);
                    if (dao.insert(book) == true) {
                        String message = "Create successful";
                        response.sendRedirect("AdminServlet?message=" + message);
                    } else {
                        request.getRequestDispatcher("create.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);

            int lengh = fileName.indexOf(".");
            for (int iIndex = lengh; iIndex >= 0; iIndex--) {
                if (fileName.charAt(iIndex) == 92) {
                    fileName = fileName.substring(iIndex + 1);
                    break;
                }
            }

            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private boolean IsValidate(String[] strIn, String[] strOut) throws Exception{
        
        boolean bCheck = true;
        
        int iLength = strIn.length;
       
        for(int iIndex = 0; iIndex < iLength; iIndex++){
            strOut[iIndex] = "Can not empty";
        }
        
        int iCheck = 0;
        if(!strIn[iCheck].isEmpty()){
            strOut[iCheck] = null;
        }
        
        iCheck++;
        BookDAO checkBookDAO = new BookDAO();
        
        BookDTO checkBookDTO = checkBookDAO.getBookById(strIn[iCheck]);
        if(checkBookDTO != null){        
            if (checkBookDTO.getBookId().equals(strIn[iCheck])) {
                strOut[iCheck] = "Book ID is already existed";
            } else {
                strOut[iCheck] = null;
            }
        }else if(!strIn[iCheck].isEmpty()){
            String fisrt = String.valueOf(strIn[iCheck].trim().charAt(0));
            if (!strIn[iCheck].matches("B\\d{3,9}")) {
                strOut[iCheck] = "Book ID must be with B and contain at least 3 digits";
            } else {
                strOut[iCheck] = null;
            }
        }
        
           
        //Check for Book Name
        iCheck++;
        if(checkBookDTO != null){
            if (checkBookDTO.getBookName().equals(strIn[iCheck])) {
                strOut[iCheck] = "Book Name is already existed";
            } else {
                strOut[iCheck] = null;
            }       
        }else if(!strIn[iCheck].isEmpty()){
            strOut[iCheck] = null;
        }   
        
        //Check for Description
        iCheck++;
        if(!strIn[iCheck].isEmpty()){
            if(strIn[iCheck].length() < 6){
                strOut[iCheck] = "Description must longer than 6 characters";
            }else{
                strOut[iCheck] = null;
            }
        }
        
        //Check for Quanity
        iCheck++;
        if(!strIn[iCheck].isEmpty()){
            if(!strIn[iCheck].matches("[0-9]+")){
                strOut[iCheck] = "Quanity is invalid value";
            }else{
                strOut[iCheck] = null;
            }
        }
        
         //Check for Year
        iCheck++;
        if(!strIn[iCheck].isEmpty()){
            if(!strIn[iCheck].matches("[0-9]+")){
                strOut[iCheck] = "Year is invalid value";
            }else{
                float tmpYear = Float.parseFloat(strIn[iCheck]);
                if(tmpYear < 2000 || tmpYear > 2022){
                     strOut[iCheck] = "Year is incorrect value";
                }else{
                    strOut[iCheck] = null;
                }               
            }
        }
        
        iCheck++;
        //Skip for Author
        if(!strIn[iCheck].isEmpty()){
            strOut[iCheck] = null;
        }
        
        
        //Check for Price
        iCheck++;
        if(!strIn[iCheck].isEmpty()){
            if(!strIn[iCheck].matches("[0-9]+")){
                strOut[iCheck] = "Price is invalid value";
            }else{
                strOut[iCheck] = null;
            }
        }
               
        //Check the exist of error message
        for(int iIndex =0; iIndex < iLength; iIndex++){
            if(strOut[iIndex] != null){
               bCheck = false;
               break;
            }
        }
                       
        return bCheck;
    }    
    //[10-July-2021][End] <----

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
