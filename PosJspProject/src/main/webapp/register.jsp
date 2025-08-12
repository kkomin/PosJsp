<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 등록</title>
<style>
    .body-container {
        padding: 20px;
    }
    .body-container > input {
        padding: 10px;
    }

    .check19 {
        margin-top: 20px;
    }
    .btn {
        margin-top: 30px;
    }
</style>
</head>
<body>
<div class="body-container">
    <h3>제품명</h3>
    <input type="text" name="product" placeholder="제품명을 입력하세요.">
    <h3>제조사</h3>
    <input type="text" name="company" placeholder="제조사를 입력하세요.">
    <h3>가격</h3>
    <input type="number" name="price" placeholder="가격을 입력하세요.">
    <h3>유통기한</h3>
    <input type="number" name="expiration" placeholder="유통기한을 입력하세요.">
    <h3>수량</h3>
    <input type="number" name="stock" placeholder="수량을 입력하세요." min="10" value="10">
    <div class="check19">
        <span>19금 여부</span>
        <input type="checkbox" name="is_adult">
    </div>
    <div class="btn">
        <button>등록</button>
    </div>
</div>
</body>
</html>