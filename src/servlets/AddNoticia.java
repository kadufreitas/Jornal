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

import excecao.FalhaNoBanco;
import DAO.FabricaConexao;
import DAO.NoticiaDAO;
import modelo.Noticia;
import modelo.Usuario;

/**
 * Servlet implementation class addNoticia
 */
@WebServlet("/addNoticia")
public class AddNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticiaDAO noticiaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNoticia() {
        super();
        Connection conexao = new FabricaConexao().getConnection();
        this.noticiaDAO = new NoticiaDAO(conexao);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq;
		try{
			String categoria = request.getParameter("idCategoria");
			long idCategoria = Long.parseLong(categoria);
			
			if(categoria != null){
				request.setAttribute("idCategoria", idCategoria);
				rq = request.getRequestDispatcher("cadastroNoticia.jsp");
			}else{
				request.setAttribute("erro_add_noticia", "Categoria Inválida!");
				rq = request.getRequestDispatcher("categorias.jsp");
			}
		}catch(RuntimeException e){
			request.setAttribute("erro_add_noticia", "Categoria Inválida!");
			rq = request.getRequestDispatcher("categorias.jsp");
		}
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String conteudo = request.getParameter("conteudo");
			String titulo = request.getParameter("titulo");
			String categoria = request.getParameter("idCategoria");
			long intCategoria = Long.parseLong(categoria);
			
			if(conteudo != null && categoria != null){
				Noticia noticia = new Noticia();
				noticia.setCategoria(intCategoria);
				noticia.setConteudo(conteudo);
				noticia.setTitulo(titulo);
				this.noticiaDAO.cadastrar(noticia);
				response.sendRedirect("listarNoticias?idCategoria="+intCategoria);
				return;
			}else{
				request.setAttribute("erro_add_noticia", "Informações Inválidas!");
			}
		}catch(RuntimeException e){
			request.setAttribute("erro_add_noticia", "Categoria Inválida!");
		} catch (FalhaNoBanco e) {
			request.setAttribute("erro_add_noticia", "Ocorreu alguma falha no banco!");
		}
		RequestDispatcher rq = request.getRequestDispatcher("cadastroNoticia.jsp");
		rq.forward(request, response);
	}

}
