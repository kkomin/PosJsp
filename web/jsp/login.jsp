<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <form method="post" action="loginProcess.jsp">
            <label for="userId">아이디 : </label>
            <input type="text" id="password" name="password" required>

            <button type="submit">로그인</button>
        </form>
    </div>
</body>
</html>