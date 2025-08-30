<%@page import="java.util.List"%>
<%@page import="dao.getSalesDAO"%>
<%@page import="dto.SalesDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 조회 결과</title>
</head>
<body>
	<h2>매출 조회 결과</h2>
	
	<form action="sale.jsp" method="get">
    <input type="text" placeholder="날짜 입력(YYYYMMDD)" name="date" />
    <input type="submit" value="조회" />
	</form>
	
	<br/>
	
    <%
        String date = request.getParameter("date");
        if (date != null && !date.isEmpty()) {
            getSalesDAO dao = new getSalesDAO();
            List<SalesDTO> sales = dao.getSalesByDate(date);

            if (sales.isEmpty()) {
    %>
                <p>해당 날짜의 매출 기록이 없습니다.</p>
    <%
            } else {
                int totalSum = 0;
    %>
                <table border="1" cellpadding="5" cellspacing="0">
                    <tr>
                        <th>판매번호</th>
                        <th>직원ID</th>
                        <th>직원명</th>
                        <th>판매일자</th>
                        <th>결제수단</th>
                        <th>총액</th>
                    </tr>
                    <%
                        for (SalesDTO sale : sales) {
                            totalSum += sale.getTotalPrice();
                    %>
                        <tr>
                            <td><%= sale.getSaleId() %></td>
                            <td><%= sale.getEmpId() %></td>
                            <td><%= sale.getUserName() %></td>
                            <td><%= sale.getSaleDate() %></td>
                            <td><%= sale.getPaymentType() %></td>
                            <td><%= sale.getTotalPrice() %> 원</td>
                        </tr>
                    <% } %>
                </table>

                <p><b>총 매출: <%= totalSum %> 원</b></p>
    <%
            }
        }
    %>

</body>
</html>