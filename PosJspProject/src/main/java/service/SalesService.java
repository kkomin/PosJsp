package service;

import java.sql.Connection;
import java.util.List;

import dao.getSalesDAO;
import dto.SalesDTO;

public class SalesService {
	private final getSalesDAO salesDAO;
	
	public SalesService(Connection conn) {
		this.salesDAO = new getSalesDAO();
	}
	
	public List<SalesDTO> getSalesByDate(String date) {
        return salesDAO.getSalesByDate(date);
    }
}