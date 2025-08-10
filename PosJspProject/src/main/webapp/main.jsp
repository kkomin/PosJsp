<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>메인 페이지</title>
    <style>
        body {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            flex-direction: column;
        }
        
        .container {
            width: 100%;
            max-width: 1000px;
            padding: 20px;
        }
        
        .header-container {
            display: flex;
            flex-wrap: wrap;
            width:100%;
            padding: 20px;
            justify-content: space-between;
            align-items: center;
        }
        
        .login-time {
            margin-right: 80px;
        }
        
        .main-container {
            display: flex;
            flex-wrap: wrap;
            margin:0;
        }
        
        .main-container > div{
            border: 1px solid black;
            padding:50px;
            text-align: center;
            margin: 20px;
            cursor: pointer;
        }
        .image {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
   <%
   // name 가져오기
   String name = (String)session.getAttribute("userName");
   // 로그인한 시간 가져오기
   
   // 세션 없을 경우 ?
   if(name == null) {
   %>
       <script>
       alert("로그인이 필요합니다.");
       </script>
   <%
       response.sendRedirect("login.jsp");
       return;
   }
   %>
   <div class="container">
    <div class="header-container">
	    <h2 class="user-info"><%= name %> 님 환영합니다.</h2>
	    <!-- name을 추후 로그인한 시간으로 변경 필요 -->
	    <h4 class="login-time">로그인한 시간 : <%= name %></h4>
    </div>
    <div class="main-container">
        <div class="register-container" onclick="location.href='register.jsp'">
	        <img src="images/search.png" class="image">
	        <p>제품 등록</p>
        </div>
        <div class="search-container" onclick="location.href='search.jsp'">
	        <img src="images/search.png" class="image">
	        <p>재품 조회</p>
        </div>
        <div class="store-container" onclick="location.href='store.jsp'">
	        <img src="images/search.png" class="image">
	        <p>입고 처리</p>
        </div>
        <div class="checkstore-container" onclick="location.href='checkstore.jsp'">
	        <img src="images/search.png" class="image">
	        <p>재고 조회</p>
        </div>
        <div class="pay-container" onclick="location.href='pay.jsp'">
	        <img src="images/search.png" class="image">
	        <p>결 제</p>
        </div>
        <div class="sale-container" onclick="location.href='sale.jsp'">
	        <img src="images/search.png" class="image">
	        <p>매출 조회</p>
        </div>
        <div class="dailywages-container" onclick="location.href='dailywages.jsp'">
	        <img src="images/search.png" class="image">
	        <p>일급 정산</p>
        </div>
        <div class="end-container" onclick="location.href='end.jsp'">
	        <img src="images/search.png" class="image">
	        <p>종 료</p>
        </div>
    </div>
    </div>
</body>
</html>