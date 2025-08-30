<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 조회</title>
</head>
<body>
    <h3>카테고리 선택</h3>
    <form action="productSelect.jsp" method="get">
       <label>
         <input type="radio" name="category" value="name" checked> 제품명
       </label>
       <label>
         <input type="radio" name="category" value="company"> 제조사
       </label>        
        <h3>검색어 입력</h3>
        <input type="text" name="keyword" placeholder="검색어를 입력하세요">
        <button type="submit">검색</button>
    </form>
    <div>
    
    </div>
</body>
</html>