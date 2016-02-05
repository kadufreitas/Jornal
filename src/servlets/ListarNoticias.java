package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecao.FalhaNoBanco;
import modelo.Noticia;
import DAO.FabricaConexao;
import DAO.NoticiaDAO;

/**
 * Servlet implementation class listarNoticias
 */
@WebServlet("/listarNoticias")
public class ListarNoticias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticiaDAO noticiaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarNoticias() {
        super();
        
        Connection conexao = new FabricaConexao().getConnection();
        this.noticiaDAO = new NoticiaDAO(conexao);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String idCategoria = request.getParameter("idCategoria");
			long categoria = Long.parseLong(idCategoria);
		
			List<Noticia> noticias = this.noticiaDAO.pegarTodas(categoria);
			request.setAttribute("noticias", noticias);
 		}catch(RuntimeException e){
			request.setAttribute("erro_listar_noticias", "Id Inválido!");
		}catch (FalhaNoBanco e) {
			request.setAttribute("erro_listar_noticias", "Erro no banco!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("noticias.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
