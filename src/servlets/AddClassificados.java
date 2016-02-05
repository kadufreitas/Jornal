package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ClassificadoDAO;
import DAO.FabricaConexao;

import sun.swing.plaf.windows.ClassicSortArrowIcon;

import modelo.Categoria;
import modelo.Classificado;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class addClassificados
 */
@WebServlet("/addClassificados")
public class AddClassificados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassificadoDAO classificadoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClassificados() {
        super();
        Connection conexao = new FabricaConexao().getConnection();
        classificadoDAO = new ClassificadoDAO(conexao);
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
	String conteudo = request.getParameter("conteudo");
		
		if(conteudo != null && conteudo.trim().length() > 0 ){
			try {
				Classificado classificado = new Classificado();
				classificado.setConteudo(conteudo);
				this.classificadoDAO.cadastrar(classificado);
				response.sendRedirect("listarClassificados");
				return;
			} catch (FalhaNoBanco e) {
				request.setAttribute("cadastro_erro", "Ocorreu alguma falha no banco!");
			}	
		}else{
			request.setAttribute("cadastro_erro", "Campos inválidos!");
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("cadastroClassificado.jsp");
		rq.forward(request, response);
	}
}


