<%@page import="DB.ConnectDB"%>
<%@page import="dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="service.InventoryService"%>
<%@page import="dao.InventoryDAO"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 입고</title>
</head>
<body>
<h2>제품 입고</h2>

<%
    String message = "";
    InventoryService service = new InventoryService();

    // 사용자 선택 입고
    if(request.getParameter("action") != null && request.getParameter("action").equals("add")) {
        try {
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            service.addInventory(prodId, quantity);
            message = "제품 입고가 완료되었습니다.";
        } catch(Exception e) {
            message = "입고 처리 오류: " + e.getMessage();
        }
    }

    // 랜덤 입고
    if(request.getParameter("action") != null && request.getParameter("action").equals("random")) {
        try {
            service.randomInventory();
            message = "랜덤 입고가 완료되었습니다.";
        } catch(Exception e) {
            message = "랜덤 입고 오류: " + e.getMessage();
        }
    }

    // 재고 조회
    List<ProductDTO> products = null;
    try(Connection conn = ConnectDB.getConnectionDB()) {
        InventoryDAO dao = new InventoryDAO(conn);
        products = dao.inventory();
    } catch(Exception e) {
        message += " 재고 조회 오류: " + e.getMessage();
    }
%>

<p style="color:red;"><%= message %></p>

<h3>제품 선택 입고</h3>
<form method="post" action="inventory.jsp">
    <label>제품 선택:</label>
    <select name="prodId" required>
        <option value="">제품 선택</option>
        <% for(ProductDTO p : products) { %>
            <option value="<%=p.getProdId()%>"><%=p.getProdName()%> (재고: <%=p.getStock()%>개)</option>
        <% } %>
    </select>
    <br>
    <label>입고 수량:</label>
    <input type="number" name="quantity" value="1" min="1" required>
    <input type="hidden" name="action" value="add">
    <input type="submit" value="입고 처리">
</form>

<h3>랜덤 입고</h3>
<form method="post" action="inventory.jsp">
    <input type="hidden" name="action" value="random">
    <input type="submit" value="랜덤 입고">
</form>

<h3>현재 재고</h3>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>제품ID</th>
        <th>제품명</th>
        <th>재고</th>
    </tr>
    <% for(ProductDTO p : products) { %>
        <tr>
            <td><%= p.getProdId() %></td>
            <td><%= p.getProdName() %></td>
            <td><%= p.getStock() %></td>
        </tr>
    <% } %>
</table>

</body>
</html>
