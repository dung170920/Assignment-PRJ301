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
import java.util.List;
import sample.dtos.CategoryDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(stm != null){
            stm.close();
        }
        if(con != null){
            con.close();
        }
        
    }
    
    public CategoryDTO getCategoryById(String id) throws Exception{
        CategoryDTO result = null;
        
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "Select *"
                        + " from Category where categoryID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    String name = rs.getString("categoryName");
                    
                    result = new CategoryDTO(id, name);
                }
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    
    public List<CategoryDTO> getAllCategory() throws Exception{
        List<CategoryDTO> list = null;
        try{
            con = DBUtils.makeConnection();
            if (con != null){
                String sql = "Select * from Category";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while(rs.next()){
                    String id = rs.getString("categoryID");
                    String name = rs.getString("categoryName");
                    CategoryDTO dto = new CategoryDTO(id, name);
                    list.add(dto);
                }
            }
        }finally{
            closeConnection();
        }
        return list;
    }
}

