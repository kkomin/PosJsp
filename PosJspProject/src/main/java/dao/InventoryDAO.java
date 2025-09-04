package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDTO;

public class InventoryDAO {
	 private final Connection connection;

	    public InventoryDAO(Connection connection) {
	        this.connection = connection;
	    }

	    // PRODUCT 재고 목록 조회 및 리스트에 담기
	    public List<ProductDTO> inventory() {
	        List<ProductDTO> productList = new ArrayList<>();
	        // 모든 제품 조회
	        String selectSql = """
	                SELECT * FROM PRODUCTS ORDER BY PROD_ID ASC
	                """;
	        try (PreparedStatement pre = connection.prepareStatement(selectSql)){
	            ResultSet rs = pre.executeQuery();
	            // 전체 목록 조회
	            while(rs.next()) {
	                // PRODUCTS -> prod_id, prod_name, company, expiration, is_adult, price, stock
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
	                productList.add(product);
	            }
	            return productList;

	        } catch (SQLException e) {
	            System.out.println("INVENTORY SQL 구문 오류" + e.getMessage());
	        }
	        return productList;
	    }

	    // 입고 기록 추가
	    public void addInventoryLog(int prodId, int quantity) {
	        // INVENTORY_LOGS에 기록 추가
	        // id -> sequence로 순차적으로 생성
	        String inventoryLogSql = """
	                INSERT INTO INVENTORY_LOGS(LOG_ID, PROD_ID, QUANTITY, ARRIVAL_DATE)
	                VALUES (INVENTORY_LOGS_ID_SEQ.NEXTVAL, ?, ?, SYSDATE)
	                """;
	        try (PreparedStatement pre = connection.prepareStatement(inventoryLogSql)) {
	            // 파라미터 바인딩
	        	pre.setInt(1, prodId);
	            pre.setInt(2, quantity);

	            pre.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("INVENTORY INSERT 오류" + e.getMessage());
	        }
	    }


	    // 수량만큼 products의 재고 수량 증가
	    public void addProducts(int prodId, int stock) {
	        // PRODUCTS의 STOCK 수량 변경
	        String addProductSql = """
	                UPDATE PRODUCTS SET STOCK = STOCK + ? WHERE PROD_ID = ?
	                """;
	        try (PreparedStatement pre = connection.prepareStatement(addProductSql)) {
	            // 파라미터 바인딩
	        	pre.setInt(1, stock);
	        	pre.setInt(2, prodId);

	        	pre.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("PRODUCTS UPDATE 오류" + e.getMessage());
	        }
	    }
}
