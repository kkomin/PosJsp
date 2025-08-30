package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.ConnectDB;
import dto.ProductDTO;

public class SearchDAO {
    public List<ProductDTO> searchByName(String name) {
        String sql = "select * from products where upper(prod_name) like ?";
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        
        if(name == null) name ="";
        
        try(Connection conn = ConnectDB.getConnectionDB();
                PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("PROD_ID"), 
                        rs.getString("PROD_NAME").trim(),
                        rs.getString("COMPANY"),
                        rs.getDate("EXPIRATION"),
                        rs.getString("IS_ADULT").charAt(0),
                        rs.getInt("PRICE"),
                        rs.getInt("STOCK"));
                list.add(product);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<ProductDTO> searchByCompany(String company) {
        String sql = "select * from products where upper(company) like ?";
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        
        try(Connection conn = ConnectDB.getConnectionDB();
                PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + company.toUpperCase() + "%");
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("PROD_ID"), 
                        rs.getString("PROD_NAME"),
                        rs.getString("COMPANY").trim(),
                        rs.getDate("EXPIRATION"),
                        rs.getString("IS_ADULT").charAt(0),
                        rs.getInt("PRICE"),
                        rs.getInt("STOCK"));
                list.add(product);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
