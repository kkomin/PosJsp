<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>메인 페이지</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .main-container {
            display: flex;
            flex-wrap: wrap;
        }
        
        .main-container > div{
            border: 1px solid black;
            padding:20px;
            text-align: center;
            margin:10px;
        }
        .image {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <div class="register-container">
        <img src="images/search.png" class="image">
        <p>제품 등록</p>
        </div>
        <div class="search-container">
        <img src="images/search.png" class="image">
        <p>재품 조회</p>
        </div>
        <div class="store-container">
        <img src="images/search.png" class="image">
        <p>입고 처리</p>
        </div>
        <div class="checkstore-container">
        <img src="images/search.png" class="image">
        <p>재고 조회</p>
        </div>
        <div class="pay-container">
        <img src="images/search.png" class="image">
        <p>결 제</p>
        </div>
        <div class="sale-container">
        <img src="images/search.png" class="image">
        <p>매출 조회</p>
        </div>
        <div class="deadline-container">
        <img src="images/search.png" class="image">
        <p>일급 정산</p>
        </div>
        <div class="end-container">
        <img src="images/search.png" class="image">
        <p>종 료</p>
        </div>
    </div>
</body>
</html>