package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ComentarioDAO;
import DAO.FabricaConexao;
import DAO.UsuarioDAO;

import modelo.Comentario;
import modelo.Noticia;
import modelo.Usuario;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class addComentario
 */
@WebServlet("/addComentario")
public class AddComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private ComentarioDAO comentarioDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComentario() {
		super();
		Connection conexao = new FabricaConexao().getConnection();
		usuarioDAO = new UsuarioDAO(conexao);
		comentarioDAO = new ComentarioDAO(conexao);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String noticia = request.getParameter("idNoticia");
			long idNoticia = Long.parseLong(noticia);
			String conteudo = request.getParameter("conteudo");
			String usuario = request.getParameter("idUsuario");
			long idUsuario = Long.parseLong(usuario);
			String categoria = request.getParameter("idCategoria");
			long idCategoria = Long.parseLong(categoria);

			if (conteudo != null && usuario != null && noticia != null) {
				Comentario comentario = new Comentario();
				comentario.setIdNoticia(idNoticia);
				comentario.setConteudo(conteudo);
				Usuario usuarioAux = usuarioDAO.pegarPorId(idUsuario);
				comentario.setUsuario(usuarioAux);
				this.comentarioDAO.cadastrar(comentario);
				response.sendRedirect("listarNoticias?idCategoria="
						+ idCategoria);
				return;
			} else {
				request.setAttribute("erro_add_categoria",
						"Informações Inválidas!");
			}
		} catch (RuntimeException e) {
			request.setAttribute("erro_add_categoria", "Categoria Inválida!");
		} catch (FalhaNoBanco e) {
			request.setAttribute("erro_add_categoria",
					"Ocorreu alguma falha no banco!");
		}
		RequestDispatcher rq = request
				.getRequestDispatcher("listarNoticias.jsp");
		rq.forward(request, response);
	}
}
