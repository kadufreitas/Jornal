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

import DAO.CategoriaDAO;
import DAO.FabricaConexao;
import modelo.Categoria;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class listarCategorias
 */
@WebServlet("/listarCategorias")
public class ListarCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCategorias() {
        super();
        Connection conexao = new FabricaConexao().getConnection();
        this.categoriaDAO = new CategoriaDAO(conexao);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			List<Categoria> categorias = this.categoriaDAO.pegarTodas();
			request.setAttribute("categorias", categorias);
		}catch (FalhaNoBanco e) {
			request.setAttribute("erro_listar_categorias", "Erro no banco!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("categorias.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Auto-generated method stub
	}

}
