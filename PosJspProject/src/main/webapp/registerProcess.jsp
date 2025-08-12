<%@page import="service.RegisterService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
String product = request.getParameter("prod_name");
String company = request.getParameter("company");
String expiration = request.getParameter("expiration");
String isAdultStr = request.getParameter("isAdult");
char isAdult = (isAdultStr != null && isAdultStr.equals("Y")? '1' : '0');
int price = Integer.parseInt(request.getParameter("price"));
int stock = Integer.parseInt(request.getParameter("stock"));

RegisterService service = new RegisterService();
boolean result = service.registerProduct(product, company, expiration, isAdult, price, stock);

if(result) {
    response.sendRedirect("productList.jsp");
} else {
    System.out.println("제품 등록 실패!");
}

%>