package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.service.AccountServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994713135647760315L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account account = new Account();
		account.setPassword(password);
		account.setUsername(username);
		HttpSession httpSession = req.getSession();
		
		if(AccountServiceImpl.isAccountExist(account))
		{
			account = AccountServiceImpl.getAccountByUsername(username, password);
			if(account.getPermission().equals("ROLE_ADMIN"))
			{
				httpSession.setAttribute("user", account);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/list-phone");
				dispatcher.forward(req, resp);
			}else
			{
				//e=1 <=> not permission.
				resp.sendRedirect("/PhoneShop/login.jsp?e=1");
			}
		}else
		{
			// e=2 <=> the account is not exist
			resp.sendRedirect("/PhoneShop/login.jsp?e=2");
		}
		
	}

}
