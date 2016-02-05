<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Classificado</title>
<style type="text/css">
form{
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
	<form method="post" action="addCategoria">
		<label for="text">Conteudo:</label>
		<input type="text" name="conteudo"/>
		<c:forEach var="erro" items="${cadastro_erro}">
			<a class="erro">${erro}</a>
		</c:forEach>
		<input type="submit" value="Cadastrar"/>
	</form>
</body>
</html>