<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categorias</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty categorias}">
			<c:forEach var="categoria" items="${categorias}">
				<div class="categoria">
					<p>Nome: ${categoria.nome}</p>
					<c:if test="${not empty usuario && (usuario.tipo == 2 || usuario.tipo == 1)}">
						<a href="addNoticia?idCategoria=${categoria.id}">Adicionar Notícia</a>
					</c:if>
					<a href="listarNoticias?idCategoria=${categoria.id}">Ver Notícias</a>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>Não há categorias cadastradas</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty usuario && usuario.tipo == 1}">
		<a href="cadastroCategoria.jsp">Adicionar Categoria</a>
	</c:if>
</body>
</html>