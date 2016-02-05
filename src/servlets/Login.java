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
import modelo.Usuario;
import DAO.FabricaConexao;
import DAO.UsuarioDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        Connection conexao = new FabricaConexao().getConnection();
        this.usuarioDAO = new UsuarioDAO(conexao);
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("senha");
		HttpSession session = request.getSession();
		
		if(email != null && password != null){
			Usuario usuario;
			try {
				usuario = this.usuarioDAO.ObterPorEmail(email);
				if(usuario != null && usuario.getSenha().equals(password)){
					
					session.setAttribute("usuario", usuario);
					response.sendRedirect("index.jsp");
					return;
				}else{
					request.setAttribute("erro_login", "Email ou Senha inválidos!");
				}
			} catch (FalhaNoBanco e) {
				request.setAttribute("erro_login", "Ocorreu alguma falha no banco!");
			}	
		}else{
			request.setAttribute("erro_login", "Email ou Senha inválidos!");
		}
		RequestDispatcher rq = request.getRequestDispatcher("login.jsp");
		rq.forward(request, response);
	}

}
