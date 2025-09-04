<%@page import="dto.PaymentDTO"%>
<%@ page import="service.PaymentService" %>
<%@ page import="dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 결과</title>
</head>
<body>
<h2>결제 결과</h2>
<%
    // 파라미터 가져오기
    String prodIdStr = request.getParameter("prodId");
    String quantityStr = request.getParameter("quantity");
    String payMethod = request.getParameter("payMethod");
    String cardNumber = request.getParameter("cardNumber");
    String cashPaidStr = request.getParameter("cashPaid");

    int prodId = Integer.parseInt(prodIdStr);
    int quantity = Integer.parseInt(quantityStr);
    Integer cashPaid = (cashPaidStr != null && !cashPaidStr.isEmpty()) ? Integer.parseInt(cashPaidStr) : null;

    // PaymentService 객체
    PaymentService service = new PaymentService();

    // 선택한 제품과 수량 추가
    service.addItem(prodId, quantity);

    // 결제 처리
    String result = service.processPayment(payMethod, cardNumber, cashPaid);

    // 결제 항목 조회
    List<PaymentDTO> items = service.getPaymentItems();
%>

<% for(PaymentDTO item : items) { %>
<p>제품명: <%= item.getProdName() %></p>
<p>수량: <%= item.getQuantity() %> 개</p>
<p>단가: <%= item.getPrice() %> 원</p>
<% } %>

<p>총 결제금액: <%= service.getTotalPrice() %> 원</p>
<p>결제 방식: <%= payMethod.equals("card") ? "카드" : "현금" %></p>
<p>결제 상태: <%= result %></p>
<p>잔액: <%= service.getCurrentBalance() %> 원</p>

<button type="button" onclick="location.href='main.jsp';">메인으로</button>
</body>
</html>
