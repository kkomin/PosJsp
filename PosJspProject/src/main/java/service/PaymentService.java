package service;

import java.util.ArrayList;
import java.util.List;

import dao.CheckStockDAO;
import dto.PaymentDTO;
import dto.ProductDTO;

public class PaymentService {
    private int currentBalance = 1234000; // 초기 잔고
    private final CheckStockDAO stockDAO = new CheckStockDAO();
    
    // 현재 결제할 항목 리스트
    private final List<PaymentDTO> paymentItems = new ArrayList<>();

    // 전체 제품 조회
    public List<ProductDTO> getAllProducts() {
        return stockDAO.getAllProducts();
    }

    // 제품 선택 후 수량 추가
    public void addItem(int prodId, int quantity) {
        ProductDTO product = stockDAO.getAllProducts().stream()
                .filter(p -> p.getProdId() == prodId)
                .findFirst()
                .orElse(null);

        if (product != null) {
            PaymentDTO item = new PaymentDTO(prodId, product.getProdName(), product.getPrice(), quantity);
            paymentItems.add(item);
        }
    }

    // 현재 결제 금액 계산
    public int getTotalPrice() {
        return paymentItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    // 결제 처리 (method: "card" or "cash")
    public String processPayment(String method, String cardNumber, Integer cashPaid) {
        int totalPrice = getTotalPrice();
        String result = "";

        switch (method) {
            case "card":
                if (cardNumber == null || cardNumber.length() != 16) {
                    return "카드 번호가 올바르지 않습니다.";
                }
                currentBalance += totalPrice;
                result = "카드 결제 완료 : " + totalPrice + "원";
                break;

            case "cash":
                if (cashPaid == null || cashPaid < totalPrice) {
                    return "현금이 부족합니다.";
                }
                int change = cashPaid - totalPrice;
                currentBalance -= change;
                result = "현금 결제 완료 : " + totalPrice + "원, 거스름돈 : " + change + "원";
                break;

            default:
                return "결제 방법 선택 오류";
        }

        // 결제 완료 후 재고 갱신
        updateStock(paymentItems);
        paymentItems.clear(); // 초기화

        return result;
    }

    // 재고 갱신
    private void updateStock(List<PaymentDTO> items) {
        List<ProductDTO> allProducts = stockDAO.getAllProducts();
        for (PaymentDTO item : items) {
            ProductDTO product = allProducts.stream()
                    .filter(p -> p.getProdId() == item.getProdId())
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                int newStock = product.getStock() - item.getQuantity();
                if (newStock < 0) newStock = 0;
                stockDAO.updateStock(item.getProdId(), newStock);
            }
        }
    }

    // 현재 잔고 조회
    public int getCurrentBalance() {
        return currentBalance;
    }

    // 결제 항목 조회
    public List<PaymentDTO> getPaymentItems() {
        return paymentItems;
    }
}
