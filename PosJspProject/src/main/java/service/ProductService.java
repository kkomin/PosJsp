package service;

import java.util.List;
import dao.CheckStockDAO;
import dto.ProductDTO;

public class ProductService {
    private CheckStockDAO checkStockDAO = new CheckStockDAO();
    
    public List<ProductDTO> getAllProducts() {
        return checkStockDAO.getAllProducts();
    }

}
