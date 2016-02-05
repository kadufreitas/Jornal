package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import DAO.FabricaConexao;
import DAO.UsuarioDAO;

import modelo.Categoria;
import modelo.Usuario;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class addCategoria
 */
@WebServlet("/addCategoria")
public class AddCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoria() {
        super();
        // TODO Auto-generated constructor stub
        Connection conexao = new FabricaConexao().getConnection();
        this.categoriaDAO = new CategoriaDAO(conexao);
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
		String nome = request.getParameter("nome");
		
		if(nome != null && nome.trim().length() > 0 ){
			try {
				Categoria categoria = new Categoria();
				categoria.setNome(nome);
				this.categoriaDAO.cadastrar(categoria);
			} catch (FalhaNoBanco e) {
				request.setAttribute("cadastro_erro", "Ocorreu alguma falha no banco!");
			}	
		}else{
			request.setAttribute("cadastro_erro", "Campos inválidos!");
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("cadastro.jsp");
		rq.forward(request, response);
	}

}
