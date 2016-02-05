<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Classificados</title>
</head>
<body>
	<h1>Classificados</h1>
	<c:choose>
		<c:when test="${not empty classificados}">
			<c:forEach var="classificado" items="${classificados}">
				<div class="classificado">
					<p>
						${classificado.conteudo}
					</p>
					<div>
						<p>Ofertas</p>
						<c:choose>
							<c:when test="${not empty classificado.ofertas}">
								<c:forEach var="oferta" items="${classificado.ofertas}">
									<h5>${oferta.usuario.nome}</h5>
									<p>${oferta.conteudo}</p>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div>Não há ofertas para essa notícia</div>
							</c:otherwise>
						</c:choose>
					</div>
					<c:if test="${not empty usuario}">					
						<form method="post" action="addOferta">
							<input type="text" name="conteudo"/>
							<input type="hidden" name="idClassificado" value="${classificado.id}"/>
							<input type="submit" value="Cadastrar"/>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>Não há classificados cadastradas</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty usuario && (usuario.tipo == 2 || usuario.tipo == 1)}">
		<a href="cadastroClassificado.jsp">Adicionar Classificado</a>
	</c:if>
</body>
</html>