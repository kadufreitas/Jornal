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

import modelo.Noticia;
import excecao.FalhaNoBanco;
import DAO.FabricaConexao;
import DAO.NoticiaDAO;

/**
 * Servlet implementation class removerNoticia
 */
@WebServlet("/removerNoticia")
public class RemoverNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticiaDAO noticiaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverNoticia() {
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
			String noticia = request.getParameter("idNoticia");
			long idNoticia = Long.parseLong(noticia);
			String idCategoria = request.getParameter("idCategoria");
			
			this.noticiaDAO.remover(idNoticia);
			response.sendRedirect("listarNoticias?idCategoria="+idCategoria);
			return;
		}catch(RuntimeException e){
			request.setAttribute("erro_remove_categoria", "Id Inválido!");
		} catch (FalhaNoBanco e) {
			request.setAttribute("erro_remove_categoria", "Ocorreu alguma falha no banco!");
		}
		RequestDispatcher rq = request.getRequestDispatcher("erros.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
