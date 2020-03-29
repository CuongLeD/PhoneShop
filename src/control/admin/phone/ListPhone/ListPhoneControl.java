package control.admin.phone.ListPhone;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Phone;
import model.service.*;

@WebServlet(urlPatterns = {"/list-phone"})
public class ListPhoneControl extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -134263185606524169L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PhoneServiceImpl phoneService = new PhoneServiceImpl();
		List<Phone> phones = phoneService.getElements(0, 10);
		System.out.println(phones.size());
		req.setAttribute("phones", phones);
		RequestDispatcher dispatecher = req.getRequestDispatcher("/admin/phone/list-phone.jsp");
		dispatecher.forward(req, resp);
	}

}
