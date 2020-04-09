package control.admin.phone;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Phone;
import model.service.PhoneServiceImpl;

@WebServlet(urlPatterns = {"/phone/search-phone"})
public class SearchPhoneCtrl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private List<Phone> phoneListFound;
	private PhoneServiceImpl phoneService;
	
	@Override
	public void init() throws ServletException {
		phoneService = new PhoneServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String phoneName = req.getParameter("phoneName").trim();
		phoneListFound = phoneService.searchElementsByName(phoneName);
		req.setAttribute("phones", phoneListFound);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/phone/list-phone.jsp");
		dispatcher.forward(req, resp);
	}
}
