package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import DB.ConnectDB;

public class ProductDAO {
	public void insertProduct(String prodName, String company, Date expiration, char isAdult, int price, int stock) {
		String insertSql = "insert into products (prod_id, prod_name, company, expiration, is_adult, price, stock) values (prod_id_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectDB.getConnectionDB();
				PreparedStatement pre = conn.prepareStatement(insertSql)) {
				
			pre.setString(1, prodName);
			pre.setString(2, company);
			pre.setDate(3, expiration);
			pre.setString(4, String.valueOf(isAdult));
			pre.setInt(5, price);
			pre.setInt(6, stock);
			
			pre.executeUpdate();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}