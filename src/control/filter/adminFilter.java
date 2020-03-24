package control.filter;

import control.dao.AccountDAOImpl;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;

@WebFilter(urlPatterns = {"/admin/*"})
public class adminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();
		
		Object object = httpSession.getAttribute("user");
		if(object != null)
		{
			chain.doFilter(request, response);
		}else
		{
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			if(username != null && password != null)
			{
				Account account = AccountDAOImpl.getAccountByUsername(username);
				if(account != null && account.getPassword().equals(password) && account.getPermission().equals("ROLE_ADMIN"))
				{
					httpSession.setAttribute("user", account);
					chain.doFilter(request, response);
				}else
				{
					resp.sendRedirect("/PhoneShop/login.jsp?e=2");
				}
			}else
			{
				resp.sendRedirect("/PhoneShop/login.jsp?e=1");
			}
		}
		
		
		
		
		
	}
	
}
