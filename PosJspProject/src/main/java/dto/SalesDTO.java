package dto;

import java.sql.Date;

public class SalesDTO {
	// 판매 정보에 대한 객체 생성
	// saleid, empid, saledate, totalprice, paymenttype, isadult
    private int saleId;
    private int empId;
    private Date saleDate;
    private int totalPrice;
    private String paymentType;
    private Character isAdult;

    public SalesDTO() {}

    public SalesDTO(int saleId, int empId, Date saleDate, int totalPrice, String paymentType, Character isAdult) {
        this.saleId = saleId;
        this.empId = empId;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.isAdult = isAdult;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getEmpId() {
        return empId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Character getIsAdult() {
        return isAdult;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
