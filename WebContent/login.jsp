<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Realizar Login</title>
<style type="text/css">
#login{
	margin-left:auto;
	margin-right:auto;
	width:300px;
	text-align:center;
}
.erro{
	color:red
}
</style>
</head>
<body>
	<form id="login" method="post" action="Login">
		<label for="email">Email:</label>
		<input type="text" name="email"/>
		<label for="senha">Senha:</label>
		<input type="password" name="senha"/>
		<c:forEach var="erro" items="${erro_login}">
			<a class="erro">${erro}</a>
		</c:forEach>
		<input type="submit" value="Entrar"/>
	</form>
</body>
</html>