<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Notícia</title>
</head>
<body>
	<h3>Adicionar Noticia</h3>
	<form action="addNoticia" method="post">
		Titulo: <input type="text" name="titulo" /> <br />
		Conteudo: <input type="text" name="conteudo" /> <br />
		<input type="hidden" name="idCategoria" value="${idCategoria}"/>
		<input type="submit" value="OK">
	</form>
</body>
</html>