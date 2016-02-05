package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet Filter implementation class FiltroJornalista
 */
@WebFilter(urlPatterns={"/addNoticia", "/removerNoticia", "/cadastroNoticia.jsp"})
public class FiltroJornalista implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroJornalista() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (session == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("login.jsp");
			return;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("login.jsp");
			return;
		}

		
		if(usuario.getTipo() == 3){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("index.jsp");
			return;
		}
		
		chain.doFilter(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
