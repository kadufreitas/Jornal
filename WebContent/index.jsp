<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jornal</title>
</head>
<body>
	<a href="listarCategorias">Categorias de Notícias</a>
	<a href="listarClassificados">Classificados</a>
	<c:if test="${not empty usuario && (usuario.tipo == 2 || usuario.tipo == 1)}">
		<a href="addNoticia?idCategoria=${categoria.id}">Adicionar Notícia</a>
	</c:if>
</body>
</html>