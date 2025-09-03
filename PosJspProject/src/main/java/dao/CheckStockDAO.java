package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.ConnectDB;
import dto.PaymentDTO;
import dto.ProductDTO;

public class CheckStockDAO {
    // PRODUCT 재고 목록 조회 및 리스트에 담기
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        
        String sql = "select prod_id, prod_name, company, stock, price, is_adult, expiration from products order by prod_id";
        
        try(Connection conn = ConnectDB.getConnectionDB();
                PreparedStatement pre = conn.prepareStatement(sql);
                ResultSet rs = pre.executeQuery()) {
            
            while(rs.next()) {
                int id = rs.getInt("prod_id");
                String name = rs.getString("prod_name");
                String company = rs.getString("company");
                Date exp = rs.getDate("expiration");
                char adult = rs.getString("is_adult").charAt(0);
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");

                // 제품 객체 생성
                ProductDTO product = new ProductDTO(id, name, company, exp, adult, price, stock);
                // 리스트에 추가
                list.add(product);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // 재고 업데이트
 // 특정 제품 재고 갱신
    public void updateStock(int prodId, int newStock) {
        String sql = "UPDATE products SET stock = ? WHERE prod_id = ?";

        try (Connection conn = ConnectDB.getConnectionDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newStock);
            pstmt.setInt(2, prodId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}