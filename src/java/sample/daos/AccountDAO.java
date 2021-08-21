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
import sample.dtos.AccountDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class AccountDAO {

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

    public AccountDTO checkLogin(String userId, String password) throws ClassNotFoundException, SQLException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from Account where userID=? and password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    AccountDTO a = new AccountDTO(userId, password, rs.getString("fullName"), rs.getInt("roleID"));
                    return a;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public AccountDTO getAccountById(String userId) throws ClassNotFoundException, SQLException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from Account where userID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId.toUpperCase());
                rs = stm.executeQuery();
                while (rs.next()) {
                    AccountDTO a = new AccountDTO(userId, rs.getString("password"), rs.getString("fullName"), rs.getInt("roleID"));
                    return a;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public AccountDTO signUp(AccountDTO acc) throws ClassNotFoundException, SQLException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "insert into Account(userID, password, fullName, roleID)"
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, acc.getUserId());
                stm.setString(2, acc.getPassword());
                stm.setString(3, acc.getFullname());
                stm.setInt(4, acc.getRole());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return acc;
                }

            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean update(AccountDTO acc) throws ClassNotFoundException, SQLException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "update Account set fullName=?, password=? where userID= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, acc.getFullname());
                stm.setString(2, acc.getPassword());
                stm.setString(3, acc.getUserId());
                stm.executeUpdate();
                return true;

            }
        } finally {
            closeConnection();
        }
        return false;
    }
}


