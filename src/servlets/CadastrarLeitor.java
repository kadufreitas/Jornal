package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FabricaConexao;
import DAO.UsuarioDAO;
import modelo.Usuario;
import excecao.FalhaNoBanco;

/**
 * Servlet implementation class cadastrarLeitor
 */
@WebServlet("/cadastrarLeitor")
public class CadastrarLeitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarLeitor() {
        super();
        // TODO Auto-generated constructor stub
        Connection conexao = new FabricaConexao().getConnection();
        this.usuarioDAO = new UsuarioDAO(conexao);
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
		HttpSession session = request.getSession();
		
		if(email != null && password != null && nome != null && nome.trim().length() > 0 && email.trim().length() > 0){
			try {
				Usuario usuario = new Usuario();
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(password);
				usuario.setTipo(3);
				this.usuarioDAO.cadastrar(usuario);
				session.setAttribute("usuario", usuario);
				response.sendRedirect("cadastro.jsp");
			} catch (FalhaNoBanco e) {
				session.setAttribute("cadastro_erro", "Ocorreu alguma falha no banco!");
				response.sendRedirect("cadastro.jsp");
			}	
		}else{
			session.setAttribute("cadastro_erro", "Campos inválidos!");
			response.sendRedirect("cadastro.jsp");
		}
	}

}
