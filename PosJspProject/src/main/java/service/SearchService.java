package service;

import java.sql.Connection;
import java.util.List;

import DB.ConnectDB;
import dao.SearchDAO;
import dto.ProductDTO;

public class SearchService {
    public List<ProductDTO> search(String category, String keyword) {
        try(Connection conn = ConnectDB.getConnectionDB()) {
            SearchDAO dao = new SearchDAO();
            
            if("company".equalsIgnoreCase(category)) {
                return dao.searchByName(keyword);
            } else {
                return dao.searchByName(keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
