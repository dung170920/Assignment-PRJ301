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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sample.daos.BookDAO;
import sample.dtos.AccountDTO;
import sample.dtos.BookDTO;
import sample.dtos.CategoryDTO;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class UpdateServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "photo";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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
                BookDAO dao = new BookDAO();
                CategoryDTO category = new CategoryDTO(categoryID);
                
                if (bookName.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    bookName = book.getBookName();
                }
                if (description.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    description = book.getDescription();
                }
                if (author.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    author = book.getAuthor();
                }
                if (Year.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    Year = String.valueOf(book.getYear());
                }
                if (quantity.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    quantity = String.valueOf(book.getQuantity());
                }
                if (price.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    price = String.valueOf(book.getPrice());
                }
                if (image.isEmpty()) {
                    BookDTO book = dao.getBookById(bookId);
                    image = book.getImg();
                }
                
                BookDTO book = new BookDTO(bookId, description, Float.parseFloat(price), bookName, author, Integer.parseInt(Year), Integer.parseInt(quantity), image, category);
                dao.update(book);
                int i = Integer.parseInt(request.getParameter("i"));
                response.sendRedirect("AdminServlet?i=" + i);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
