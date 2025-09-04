package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.ConnectDB;
import dto.SalesDTO;

public class getSalesDAO {
	public List<SalesDTO> getSalesByDate(String date) {
		List<SalesDTO> sales = new ArrayList<SalesDTO>();
		
		String getSaleSql = """
				SELECT s.sale_id, s.emp_id, s.sale_date, e.user_name, s.total_price, s.payment_type
                FROM SALES s
                JOIN EMPLOYEES e ON s.emp_id = e.emp_id
                WHERE TO_CHAR(s.sale_date, 'YYYYMMDD') = ?
                ORDER BY s.sale_id
				""";
		try(Connection conn = ConnectDB.getConnectionDB();
			PreparedStatement preparedStatement = conn.prepareStatement(getSaleSql)) {
            preparedStatement.setString(1, date);

            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                SalesDTO sale = new SalesDTO(
                rs.getInt("sale_id"),
                rs.getInt("emp_id"),
                rs.getDate("sale_date"),
                rs.getInt("total_price"),
                rs.getString("user_name"),
                rs.getString("payment_type").charAt(0)
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            System.out.println("매출 조회 SQL 오류 발생" + e.getMessage());
        }
		
		return sales;
	}
}
