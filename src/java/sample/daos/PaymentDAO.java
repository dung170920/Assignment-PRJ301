/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.dtos.BookDTO;
import sample.dtos.PaymentDTO;
import sample.dtos.PaymentDetailDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class PaymentDAO {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean storePayment(PaymentDTO payment) throws Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into Payment(userID, dateCreate, totalPayment, address, phoneNumber, status) "
                        + "Values(?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, payment.getUserId());
                stm.setDate(2, payment.getDateCreate());
                stm.setFloat(3, payment.getTotalPayment());
                stm.setString(4, payment.getAddress());
                stm.setString(5, payment.getPhoneNumber());
                stm.setInt(6, payment.getStatus());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean storePaymentDetail(PaymentDetailDTO detail) throws Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into PaymentDetail(paymentId, bookId, quantity, subTotal) "
                        + "Values(?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, detail.getPaymentId());
                stm.setString(2, detail.getBook().getBookId());
                stm.setInt(3, detail.getQuantity());
                stm.setFloat(4, detail.getSubTotal());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getPaymentId(PaymentDTO payment) throws Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select paymentId from Payment where userID=? and dateCreate=? and totalPayment=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, payment.getUserId());
                stm.setDate(2, payment.getDateCreate());
                stm.setFloat(3, payment.getTotalPayment());
                rs = stm.executeQuery();
                while (rs.next()) {
                    int paymentId = rs.getInt("paymentId");
                    return paymentId;
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public ArrayList<PaymentDTO> getPaymentByUserId(String userId) throws Exception {
        ArrayList<PaymentDTO> list = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from Payment where userID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    PaymentDTO payment = new PaymentDTO(rs.getInt("paymentId"), userId,
                            rs.getString("address"), rs.getString("phoneNumber"));
                    list.add(payment);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<PaymentDetailDTO> getPaymentDetail(int paymentId) throws Exception {
        ArrayList<PaymentDetailDTO> list = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from PaymentDetail where paymentId=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, paymentId);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                BookDAO dao = new BookDAO();
                while (rs.next()) {
                    BookDTO book = dao.getBookById(rs.getString("bookId"));
                    PaymentDetailDTO detail = new PaymentDetailDTO(paymentId, book,
                            rs.getInt("quantity"), rs.getFloat("subTotal"));
                    list.add(detail);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<PaymentDTO> getAllPayment() throws Exception {
        ArrayList<PaymentDTO> list = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from Payment";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    PaymentDTO payment = new PaymentDTO(rs.getInt("paymentId"), rs.getString("userID"),
                            rs.getString("address"), rs.getString("phoneNumber"));
                    list.add(payment);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public PaymentDetailDTO checkBook(String bookId) throws Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from PaymentDetail where bookId=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BookDAO bookDAO = new BookDAO();
                    BookDTO book = bookDAO.getBookById(bookId);
                    PaymentDetailDTO detail = new PaymentDetailDTO(rs.getInt("paymentId"), book,
                            rs.getInt("quantity"), rs.getFloat("subTotal"));
                    return detail;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    

}
