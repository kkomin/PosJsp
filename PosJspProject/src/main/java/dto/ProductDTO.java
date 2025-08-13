package dto;

import java.sql.Date;

// 제품 정보 객체
public class ProductDTO {
    private int prodId;
    private final String prodName;
    private final String company;
    private final Date expiration;
    private final Character isAdult;
    private final int price;
    private final int stock;


    // 제품 정보 등록 생성자
    public ProductDTO(String prodName, String company, Date expiration, Character isAdult, int price, int stock) {
        this.prodName = prodName;
        this.company = company;
        this.expiration = expiration;
        this.isAdult = isAdult;
        this.price = price;
        this.stock = stock;
    }

    // 등록된 제품 정보 조회용 생성자
    public ProductDTO(int prodId, String prodName, String company, Date expiration, Character isAdult, int price, int stock) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.company = company;
        this.expiration = expiration;
        this.isAdult = isAdult;
        this.price = price;
        this.stock = stock;
    }

    public String getProdName() {
        return prodName;
    }

    public String getCompany() {
        return company;
    }

    public Date getExpiration() {
        return expiration;
    }

    public Character getIsAdult() {
        return isAdult;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getProdId() {
        return prodId;
    }
}