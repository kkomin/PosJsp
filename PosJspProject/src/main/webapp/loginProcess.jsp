<%@page import="dto.UserDTO"%>
<%@page import="dao.UserDAO"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    String id = request.getParameter("userId");
    String pw = request.getParameter("password");
    
    UserDAO dao = new UserDAO();    		
    UserDTO user = dao.login(id, pw);
    

    if(user != null) {
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getUserName());
        response.sendRedirect("main.jsp");
    } else {
%>

<script>
    alert("아이디 또는 비밀번호가 잘못되었습니다.");
    history.back();
</script>

<%  
    }
%>