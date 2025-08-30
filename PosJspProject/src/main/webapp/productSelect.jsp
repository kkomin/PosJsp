<%@page import="dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="service.SearchService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String category = request.getParameter("category");
	String keyword = request.getParameter("keyword");
	
	SearchService search = new SearchService();
	List<ProductDTO> list = search.search(category, keyword);
	
	request.setAttribute("productList", list);    
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 검색</title>
</head>
<body>
    <h3>검색 결과</h3>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>제품명</th>
            <th>제조사</th>
            <th>유통기한</th>
            <th>성인 여부</th>
            <th>가격</th>
            <th>수량</th>
        </tr>
        
        <% 
        List<ProductDTO> products = (List<ProductDTO>)request.getAttribute("productList");
        if(products != null && !products.isEmpty()) {
            for(ProductDTO p : products) {
        %>
        <tr>
            <td><%= p.getProdId() %></td>
            <td><%= p.getProdName() %></td>
            <td><%= p.getCompany() %></td>
            <td><%= p.getExpiration() %>
            <td><%= (p.getIsAdult() != null && p.getIsAdult() == '1') ? "성인" : "일반" %></td>
            <td><%= p.getPrice() %></td>
            <td><%= p.getStock() %></td>
        </tr>
        <%
	            }
	        } else {
        %>
        <tr>
            <td colspan="7">검색 결과가 없습니다.</td>
        </tr>
        <%
        }
        %>
    </table>
</body>
</html>