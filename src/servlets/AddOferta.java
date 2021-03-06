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

import DAO.FabricaConexao;
import DAO.OfertaDeCompraDAO;

import modelo.Comentario;
import modelo.OfertaDeCompra;
import modelo.Usuario;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class addOferta
 */
@WebServlet("/addOferta")
public class AddOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OfertaDeCompraDAO ofertaDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOferta() {
        super();
		Connection conexao = new FabricaConexao().getConnection();
        ofertaDAO =  new OfertaDeCompraDAO(conexao);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classificado = request.getParameter("idClassificado");
		try {
			String conteudo = request.getParameter("conteudo");
			long idClassificado = Long.parseLong(classificado);
			
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			OfertaDeCompra oferta = new OfertaDeCompra();
			oferta.setIdClassificado(idClassificado);
			oferta.setConteudo(conteudo);
			oferta.setUsuario(usuario);
			this.ofertaDAO.cadastrar(oferta);
			response.sendRedirect("listarClassificados");
			return;
		} catch (RuntimeException e) {
			request.setAttribute("erro_add_oferta", "Informações Inválida!");
		} catch (FalhaNoBanco e) {
			request.setAttribute("erro_add_oferta",
					"Ocorreu alguma falha no banco!");
		}
		RequestDispatcher rq = request
				.getRequestDispatcher("listarNoticias");
		rq.forward(request, response);
	}

}
