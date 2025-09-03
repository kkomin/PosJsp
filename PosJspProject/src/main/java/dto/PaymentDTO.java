package dto;

public class PaymentDTO {
	private int prodId;
    private String prodName;
    private int price;
    private int quantity;

    public PaymentDTO(int prodId, String prodName, int price, int quantity) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProdId() { return prodId; }
    public String getProdName() { return prodName; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getTotalPrice() {
        return price * quantity;
    }
}
