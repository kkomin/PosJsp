<%@page import="dao.WorkLogDAO"%>
<%@page import="service.WorkWageService"%>
<%@page import="dto.LoginLogDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일급 정산 및 종료</title>
</head>
<body>
	<%
	    String empIdStr = request.getParameter("empId");
	    boolean isProcessed = false;
	    int workMinutes = 0;
	    int dailyWage = 0;
	
	    if(empIdStr != null && !empIdStr.isEmpty()) {
	        int empId = Integer.parseInt(empIdStr);
	
	        // DTO 생성
	        LoginLogDTO dto = new LoginLogDTO();
	        dto.setEmpId(empId);
	
	        // 서비스 호출 → 일급 계산 + 로그아웃 시간 세팅
	        WorkWageService service = new WorkWageService();
	        WorkWageService.LoginResult result = service.logout(dto);
	
	        // JSP에서 보여줄 값 세팅
	        workMinutes = (int)result.getWorkMinutes();
	        dailyWage = result.getDailyWage();
	
	        // 로그아웃 기록 DB 업데이트
	        WorkLogDAO dao = new WorkLogDAO();
	        dao.updateLogout(result.getLogId(), result.getLogoutTime(),
	                         result.getWorkMinutes(), result.getDailyWage());
	
	        isProcessed = true;
	    }
	%>
	
	<h2>퇴근 및 일급 정산</h2>
	
	<% if(isProcessed) { %>
	    <p>총 근무 시간: <%= workMinutes %>분</p>
	    <p>오늘 일급: <%= dailyWage %>원</p>
	
	    <script>
	        // 10초 뒤 자동 로그아웃 + login.jsp 이동
	        setTimeout(function() {
	            alert("로그아웃되었습니다.");
	            window.location.href = "login.jsp";
	        }, 5000);
	    </script>
	<% } else { %>
	    <form method="get">
	        <input type="hidden" name="empId" value="1" /> <!-- 테스트용 empId -->
	        <input type="submit" value="퇴근" />
	    </form>
	<% } %>
</body>
</html>