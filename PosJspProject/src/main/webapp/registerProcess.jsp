<%@page import="service.RegisterService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String product = request.getParameter("prodName");
String company = request.getParameter("company");
String expiration = request.getParameter("expiration");
String isAdultStr = request.getParameter("isAdult");

System.out.println("isAdultStr : " + isAdultStr);
isAdultStr = (isAdultStr != null && isAdultStr.equals("on")) ? "0" : "1";
System.out.println("isAdult : " + isAdultStr);

String priceStr = request.getParameter("price");
int price = 0;
if(priceStr != null && !priceStr.trim().isEmpty()) {
    try {
        price = Integer.parseInt(priceStr);
    } catch(NumberFormatException e) {
        System.out.println("가격 불러오기 오류");
    }
}

int stock = Integer.parseInt(request.getParameter("stock"));

RegisterService service = new RegisterService();
boolean result = service.registerProduct(product, company, expiration, isAdultStr, price, stock);

if(result) {
    %>
    <script>
    alert("제품이 정상적으로 등록되었습니다.");
    location.href = "register.jsp";
    </script>
    <%
} else {
    %>
    <script>
    alert("제품 등록에 실패했습니다. 다시 시도해 주세요.");
    history.back();
    </script>
    <%
}
    %>