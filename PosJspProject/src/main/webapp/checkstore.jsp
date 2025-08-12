<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ProductService service = new ProductService();
	List<ProductDTO> productList = service.getAllProducts();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 현황</title>
<style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #eee; }
    </style>
</head>
<body>
    <h2 style="text-align: center">제품 재고 현황</h2>
    <table>
        <thead>
            <tr>
                <th>제품명</th>
                <th>회사명</th>
                <th>가격</th>
                <th>수량</th>
                <th>유통기한</th>
                <th>19금 여부</th>
            </tr>
        </thead>
        <tbody>
        <%
	        for(ProductDTO product : productList) {
        %>
        <tr>
           <td><%= product.getProdName() %></td>
           <td><%= product.getCompany() %></td>
           <td><%= product.getPrice() %> 원</td>
           <td><%= product.getStock() %></td>
           <td><%= new SimpleDateFormat("yyyy-MM-dd").format(product.getExpiration()) %></td>
           <td><%= (product.getIsAdult() == '1') ? "Y" : "N" %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>