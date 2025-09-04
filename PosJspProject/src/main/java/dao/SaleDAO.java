package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.ConnectDB;

public class SaleDAO {
	// SALE 등록 후 SALE_ID 반환
    public int insertSale(int empId, int totalPrice, String paymentType, boolean isAdult) {
        int saleId = -1;
        String insertSql = """
                INSERT INTO SALES(SALE_ID, SALE_DATE, EMP_ID, TOTAL_PRICE, PAYMENT_TYPE, ADULT_CHECK)
                VALUES(SALE_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?)
                """;

        try(Connection conn = ConnectDB.getConnectionDB();
            PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setInt(1, empId);
            pstmt.setInt(2, totalPrice);
            pstmt.setString(3, paymentType);
            pstmt.setString(4, isAdult ? "1" : "0");

            pstmt.executeUpdate();

            // 등록 후 CURRVAL 조회
            String currSql = "SELECT SALE_SEQ.CURRVAL FROM DUAL";
            try(PreparedStatement pre = conn.prepareStatement(currSql);
                ResultSet rs = pre.executeQuery()) {
                if(rs.next()) {
                    saleId = rs.getInt(1);
                }
            }

        } catch(SQLException e) {
            System.out.println("insertSale 오류: " + e.getMessage());
        }

        return saleId;
    }

    // SALE_ITEM 등록
    public void insertSaleItem(int saleId, int prodId, int quantity, int subtotal) {
        String insertItemSql = """
                INSERT INTO SALES_ITEM(ITEM_ID, SALE_ID, PROD_ID, QUANTITY, SUBTOTAL)
                VALUES(SALE_ITEM_SEQ.NEXTVAL, ?, ?, ?, ?)
                """;

        try(Connection conn = ConnectDB.getConnectionDB();
            PreparedStatement pre = conn.prepareStatement(insertItemSql)) {

        	pre.setInt(1, saleId);
        	pre.setInt(2, prodId);
        	pre.setInt(3, quantity);
        	pre.setInt(4, subtotal);

        	pre.executeUpdate();

        } catch(SQLException e) {
            System.out.println("insertSaleItem 오류: " + e.getMessage());
        }
    }
}
