package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.FabricaConexao;
import DAO.UsuarioDAO;
import modelo.Usuario;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class cadastrarJornalista
 */
@WebServlet("/cadastrarJornalista")
public class CadastrarJornalista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarJornalista() {
        super();
        

        Connection conexao = new FabricaConexao().getConnection();
        this.usuarioDAO = new UsuarioDAO(conexao);
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
		String email = request.getParameter("email");
		String password = request.getParameter("senha");
		String nome = request.getParameter("nome");
		
		if(email != null && password != null && nome != null && nome.trim().length() > 0 && email.trim().length() > 0){
			try {
				Usuario usuario = new Usuario();
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(password);
				usuario.setTipo(2);
				this.usuarioDAO.cadastrar(usuario);
				response.sendRedirect("cadastroJornalista.jsp");
				return;
			} catch (FalhaNoBanco e) {
				request.setAttribute("add_jornalista_erro", "Ocorreu alguma falha no banco!");
			}	
		}else{
			request.setAttribute("add_jornalista_erro", "Campos inválidos!");
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("cadastroJornalista.jsp");
		rq.forward(request, response);
	}

}
