<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="src.DB.ConnectDB" %>

<%
    String id = request.getParameter("userId");
    String pw = request.getParameter("password");

    UserDAO dao = new UserDAO();
    UserDTO user = dao.login(id, pw);

    if(id != null && pw != null) {
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getName());
        response.sendRedirect("main.jsp");
    } else {
%>
<%
    <script>
        alert("아이디 또는 비밀번호가 잘못되었습니다.");
        history.back();
%>

<%  
    }
%>