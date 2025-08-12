package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.ProductDAO;

public class RegisterService {
	private ProductDAO productDAO = new ProductDAO(); 
		public boolean registerProduct(String prodName, String company, String expirationStr, char isAdult, int price, int stock) {
			try {
				// 날짜 변환
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate localDate = LocalDate.parse(expirationStr, formatter);
				Date expiration = Date.valueOf(localDate);
				
				// 유효성 검사 추가 예정
				
				productDAO.insertProduct(prodName, company, expiration, isAdult, price, stock);
				return true;
				
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
}
