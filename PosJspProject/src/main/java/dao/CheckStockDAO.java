package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.ConnectDB;
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
}
