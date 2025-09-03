<%@page import="service.WorkWageService"%>
<%@page import="dto.LoginLogDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<h2>로그아웃 처리</h2>
    <form method="post">
        <input type="hidden" name="empId" value="1" /> <!-- 테스트용 empId -->
        <input type="submit" value="프로그램 종료" />
    </form>

    <%
        String empIdStr = request.getParameter("empId");
        if (empIdStr != null) {
            int empId = Integer.parseInt(empIdStr);

            LoginLogDTO dto = new LoginLogDTO();
            dto.setEmpId(empId);

            WorkWageService service = new WorkWageService();
            WorkWageService.LoginResult result = service.logout(dto);

            out.println("<p>총 근무 시간: " + result.getWorkMinutes() + "분</p>");
            out.println("<p>오늘 일급: " + result.getDailyWage() + " 원</p>");
        }
    %>
</body>
</html>