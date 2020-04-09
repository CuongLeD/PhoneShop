package control.admin.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Phone;
import model.service.PhoneServiceImpl;

@WebServlet(urlPatterns = {"/phone/delete-phone"})
public class DeletePhoneCtrl extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PhoneServiceImpl phoneService;
	
	@Override
	public void init() throws ServletException {
		phoneService = new PhoneServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int phoneId = Integer.valueOf(req.getParameter("id"));
		Phone phone = phoneService.searchById(phoneId);
		phoneService.deleteElement(phone);
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect("/PhoneShop/phone/list-phone");
	}
	
}
