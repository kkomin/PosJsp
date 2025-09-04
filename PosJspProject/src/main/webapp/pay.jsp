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
	let unitPrice = 0; // 선택 제품 단가
	let isAdultProduct = false; // 성인 제품 여부

	function selectProduct(prodId, prodName, price, adult) {
	    document.getElementById("prodId").value = prodId;
	    document.getElementById("prodName").value = prodName;
	    document.getElementById("price").value = price;
	    unitPrice = parseInt(price);
	    isAdultProduct = adult;
	    updateTotal();
	}

	
	function updateTotal() {
        const quantity = parseInt(document.getElementById("quantity").value) || 1;
        const total = unitPrice * quantity;
        document.getElementById("totalPrice").innerText = total + " 원";
    }
</script>
<script>
	function togglePaymentInput() {
		const method = document.getElementById("payMethod").value;
	    const cardDiv = document.getElementById("cardInput");
	    const cashDiv = document.getElementById("cashInput");
	    const cardInputField = document.querySelector('input[name="cardNumber"]');
	    const cashInputField = document.getElementById("cashPaid");

	    if (method === "card") {
	        cardDiv.style.display = "block";
	        cashDiv.style.display = "none";
	        cashInputField.value = ""; // 현금 입력 초기화
	    } else {
	        cardDiv.style.display = "none";
	        cashDiv.style.display = "block";
	        cardInputField.value = ""; // 카드 입력 초기화
	    }
	}
</script>
<script>
    function validatePayment() {
    	const method = document.getElementById("payMethod").value;
    	const quantity = parseInt(document.getElementById("quantity").value) || 1;
    	const unitPrice = parseInt(document.getElementById("price").value) || 0;
    	const total = unitPrice * quantity;
        
    	 // 성인 제품 여부 확인
        if (isAdultProduct) {
            const adultConfirm = confirm("이 제품은 성인용입니다. 성인입니까?");
            if (!adultConfirm) {
                alert("성인 인증이 필요하여 결제가 취소되었습니다.");
                return false;
            }
        }

        // 카드 결제 검증
        if (method === 'card') {
            const cardNumber = document.getElementById('cardNumber').value.trim();
            const cardRegex = /^[0-9]{16}$/;
            if (!cardRegex.test(cardNumber)) {
                alert("카드 번호가 잘못되었습니다. 16자리 숫자를 입력해주세요.");
                document.getElementById("cardNumber").focus();
                return false;
            }
        }

        // 현금 결제 검증
        if (method === 'cash') {
            const cash = parseInt(document.getElementById("cashPaid").value) || 0;
            if (cash < total) {
                alert("금액이 부족합니다. 총 결제 금액: " + total + " 원");
                document.getElementById("cashPaid").focus();
                return false;
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
            <tr onclick="selectProduct('<%=p.getProdId()%>', '<%=p.getProdName()%>', '<%=p.getPrice()%>', <%=p.getIsAdult()%>)">
                <td><%=p.getProdName()%></td>
                <td><%=p.getPrice()%> 원</td>
                <td><%=p.getStock()%> 개</td>
            </tr>
            <% } %>
        </table>
    </div>
    <div class="right">
        <h3>결제 정보</h3>
        <form action="payment.jsp" method="post" onsubmit="return validatePayment();">
            <label>제품명:</label>
            <input type="text" id="prodName" name="prodName" readonly><br><br>
            <label>수량:</label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" onchange="updateTotal()"><br><br>
            <label>가격:</label>
            <input type="text" id="price" name="price" readonly><br><br>
            <label>총 결제금액:</label>
            <span id="totalPrice">0 원</span><br><br>
            <label>결제 방식:</label>
			<select name="payMethod" id="payMethod" onchange="togglePaymentInput()">
			    <option value="card">카드</option>
			    <option value="cash">현금</option>
			</select><br><br>
			
			<!-- 카드 입력 -->
			<div id="cardInput" style="display:block;">
			    <label>카드 번호 (16자리):</label>
			    <input type="text" name="cardNumber" maxlength="16" pattern="\d{16}" placeholder="숫자 16자리 입력">
			</div>
			
			<!-- 현금 입력 -->
			<div id="cashInput" style="display:none;">
			    <label>현금 입력:</label>
			    <input type="number" id="cashPaid" name="cashPaid" min="0">
			</div>	
			<br>
            <input type="hidden" id="prodId" name="prodId" placeholder="현금 금액 입력">
            <input type="submit" value="결제">
        </form>
    </div>
</div>
</body>
</html>
