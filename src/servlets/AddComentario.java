package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private ComentarioDAO comentarioDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComentario() {
		super();
		Connection conexao = new FabricaConexao().getConnection();
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
		String categoria = request.getParameter("idCategoria");
		try {
			String noticia = request.getParameter("idNoticia");
			long idNoticia = Long.parseLong(noticia);
			String conteudo = request.getParameter("conteudo");
			long idCategoria = Long.parseLong(categoria);
			
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			Comentario comentario = new Comentario();
			comentario.setIdNoticia(idNoticia);
			comentario.setConteudo(conteudo);
			comentario.setUsuario(usuario);
			this.comentarioDAO.cadastrar(comentario);
			response.sendRedirect("listarNoticias?idCategoria="+idCategoria);
			return;
		} catch (RuntimeException e) {
			request.setAttribute("erro_add_comentario", "Informações Inválida!");
		} catch (FalhaNoBanco e) {
			request.setAttribute("erro_add_comentario",
					"Ocorreu alguma falha no banco!");
		}
		RequestDispatcher rq = request
				.getRequestDispatcher("listarNoticias?idCategoria="+categoria);
		rq.forward(request, response);
	}
}
