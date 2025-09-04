package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import DB.ConnectDB;
import dao.InventoryDAO;
import dto.ProductDTO;

public class InventoryService {
	public void randomInventory() throws SQLException {
        try(Connection connection = ConnectDB.getConnectionDB()) {
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            List<ProductDTO> products = inventoryDAO.inventory();

            Random random = new Random();
            int index = random.nextInt(products.size());
            ProductDTO selected = products.get(index);

            int quantity = random.nextInt(10) + 1;

            inventoryDAO.addInventoryLog(selected.getProdId(), quantity);
            inventoryDAO.addProducts(selected.getProdId(), quantity);

            System.out.printf("\n[%s] [%d개] 랜덤 입고 완료 (현재 재고: %d개)\n",
                    selected.getProdName(), quantity, selected.getStock() + quantity);
        }
    }

    // 사용자 선택 입고
    public void addInventory(int prodId, int quantity) throws SQLException {
        try(Connection connection = ConnectDB.getConnectionDB()) {
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            inventoryDAO.addInventoryLog(prodId, quantity);
            inventoryDAO.addProducts(prodId, quantity);
        }
    }
}
