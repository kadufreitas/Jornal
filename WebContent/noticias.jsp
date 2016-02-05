<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notícias</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".excluir").click(function(){
			var retorno = confirm("Deseja realmente excluir essa notícia?");
			if(!retorno) return false;
		});
	});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty noticias}">
			<c:forEach var="noticia" items="${noticias}">
				<div class="noticia">
					<h1>${noticia.titulo}</h1>
					<p>
						${noticia.conteudo}
					</p>
					<c:if test="${not empty usuario && (usuario.tipo == 1)}">
						<a class="excluir" href="removerNoticia?idNoticia=${noticia.id}&idCategoria=${noticia.categoria}">Excluir Notícia</a>
					</c:if>					
					<div>
						<h4>Comentários</h4>
						<c:choose>
							<c:when test="${not empty noticia.comentarios}">
								<c:forEach var="comentario" items="${noticia.comentarios}">
									<h5>${comentario.usuario.nome}</h5>
									<p>${comentario.conteudo}</p>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div>Não há comentários para essa notícia</div>
							</c:otherwise>
						</c:choose>
					</div>
					<c:if test="${not empty usuario}">					
						<form method="post" action="addComentario">
							<input type="text" name="conteudo"/>
							<input type="hidden" name="idCategoria" value="${noticia.categoria}"/>
							<input type="hidden" name="idNoticia" value="${noticia.id}"/>
							<input type="submit" value="Comentar"/>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>Não há notícias cadastradas</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty usuario && (usuario.tipo == 2 || usuario.tipo == 1)}">
		<a href="addNoticia?idCategoria=${idCategoria}">Adicionar Noticia</a>
	</c:if>
</body>
</html>