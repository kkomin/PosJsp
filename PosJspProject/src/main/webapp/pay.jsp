<%@ page import="java.util.List" %>
<%@ page import="dao.CheckStockDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 결제</title>
<style>
    .container { display: flex; }
    .left { width: 50%; border-right: 1px solid #ccc; padding: 10px; }
    .right { width: 50%; padding: 10px; }
    table { width: 100%; border-collapse: collapse; }
    th, td { border: 1px solid #ccc; padding: 5px; text-align: left; }
    tr:hover { background-color: #f0f0f0; cursor: pointer; }
</style>
<script>
	function selectProduct(prodId, prodName, price) {
	    document.getElementById("prodId").value = prodId;
	    document.getElementById("prodName").value = prodName;
	    document.getElementById("price").value = price;
	}
</script>
<script>
	function togglePaymentInput() {
	    const method = document.getElementById("payMethod").value;
	    const cardDiv = document.getElementById("cardInput");
	    const cashDiv = document.getElementById("cashInput");
	
	    if (method === "card") {
	        cardDiv.style.display = "block";
	        cashDiv.style.display = "none";
	    } else if (method === "cash") {
	        cardDiv.style.display = "none";
	        cashDiv.style.display = "block";
	    }
	}
</script>
<script>
    function validatePayment() {
        const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
        
        if (paymentMethod === 'card') {
            const cardNumber = document.getElementById('cardNumber').value.trim();

            // 숫자만 입력되었는지와 16자리인지 체크
            const cardRegex = /^[0-9]{16}$/;

            if (!cardRegex.test(cardNumber)) {
                alert("카드 번호가 잘못되었습니다. 16자리 숫자를 입력해주세요.");
                return false; // form 제출 막기
            }
        }
        return true; // 정상일 경우 제출 허용
    }
</script>

</head>
<body>
<h2>제품 선택 및 결제</h2>
<div class="container">
    <div class="left">
        <h3>제품 목록</h3>
        <%
            CheckStockDAO dao = new CheckStockDAO();
            List<ProductDTO> products = dao.getAllProducts();
        %>
        <table>
            <tr><th>제품명</th><th>가격</th><th>재고</th></tr>
            <%
                for(ProductDTO p : products) {
            %>
            <tr onclick="selectProduct('<%=p.getProdId()%>', '<%=p.getProdName()%>', '<%=p.getPrice()%>')">
                <td><%=p.getProdName()%></td>
                <td><%=p.getPrice()%> 원</td>
                <td><%=p.getStock()%> 개</td>
            </tr>
            <% } %>
        </table>
    </div>
    <div class="right">
        <h3>결제 정보</h3>
        <form action="payment.jsp" method="post">
            <label>제품명:</label>
            <input type="text" id="prodName" name="prodName" readonly><br><br>
            <label>수량:</label>
            <input type="number" name="quantity" value="1" min="1"><br><br>
            <label>가격:</label>
            <input type="text" id="price" name="price" readonly><br><br>
            <label>결제 방식:</label>
			<select name="payMethod" id="payMethod" onchange="togglePaymentInput()">
			    <option value="card">카드</option>
			    <option value="cash">현금</option>
			</select><br><br>
			
			<!-- 카드 입력 -->
			<div id="cardInput" style="display:block;">
			    <label>카드 번호 (16자리):</label>
			    <input type="text" name="cardNumber" maxlength="16" pattern="\d{16}" placeholder="숫자 16자리 입력" required>
			</div>
			
			<!-- 현금 입력 -->
			<div id="cashInput" style="display:none;">
			    <label>현금 입력:</label>
			    <input type="number" name="cashPaid" min="0">
			</div>	
			<br>
            <input type="hidden" id="prodId" name="prodId">
            <input type="submit" value="결제">
        </form>
    </div>
</div>
</body>
</html>
