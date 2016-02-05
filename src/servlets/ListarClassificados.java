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

import DAO.ClassificadoDAO;
import DAO.FabricaConexao;
import modelo.Categoria;
import modelo.Classificado;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class ListarClassificados
 */
@WebServlet("/listarClassificados")
public class ListarClassificados extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClassificadoDAO classificadoDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarClassificados() {
        super();
        Connection conexao = new FabricaConexao().getConnection();
        this.classificadoDAO = new ClassificadoDAO(conexao);
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<Classificado> classificados = this.classificadoDAO.pegarTodos();
			request.setAttribute("classificados", classificados);
		}catch (FalhaNoBanco e) {
			request.setAttribute("erro_listar_classificados", "Erro no banco!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("classificados.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
