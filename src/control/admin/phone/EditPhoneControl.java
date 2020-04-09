package control.admin.phone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Phone;
import model.Shop;
import model.Vendor;
import model.service.CategoryServiceImpl;
import model.service.PhoneServiceImpl;
import model.service.ShopServiceImpl;
import model.service.VendorServiceImpl;

@WebServlet(urlPatterns = {"/phone/edit-phone"})
public class EditPhoneControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Phone phone;
	private List<Shop> shops;
	private List<Category> categories;
	private List<Vendor> vendors;
	private PhoneServiceImpl phoneService;
	
	@Override
	public void init() throws ServletException {
		phone = new Phone();
		
		phoneService = new PhoneServiceImpl();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int phoneId = Integer.valueOf(req.getParameter("id").trim());
		phone = phoneService.getElementById(phoneId);
		categories = new CategoryServiceImpl().getElements(0, new CategoryServiceImpl().amountRows());
		shops = new ShopServiceImpl().getElements(0, new ShopServiceImpl().amountRows());
		vendors = new VendorServiceImpl().getElements(0, new VendorServiceImpl().amountRows());
		
		req.setAttribute("phone", phone);
		req.setAttribute("categories", categories);
		req.setAttribute("shops", shops);
		req.setAttribute("vendors", vendors);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/phone/edit-phone.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Phone phoneUpdate = new Phone();
		phoneUpdate.setPhoneId(Integer.valueOf(req.getParameter("id").trim()));
		phoneUpdate.setPhoneName(req.getParameter("txtPhoneName"));
		phoneUpdate.setPrice(Integer.valueOf(req.getParameter("txtPrice").trim()));
		phoneUpdate.setQuantity(Integer.valueOf(req.getParameter("txtQuantity")));
		phoneUpdate.setCategoryId(new CategoryServiceImpl().searchElementByName(req.getParameter("selectCategory")).getCategoryId());
		phoneUpdate.setVendorId(new VendorServiceImpl().searchElementByName(req.getParameter("selectVendor")).getVendorId());
		phoneUpdate.setShopId(new ShopServiceImpl().searchElementByName(req.getParameter("selectShop")).getShopId());
		phoneUpdate.setDescription(req.getParameter("txtDescription"));
		
		boolean updateResult = phoneService.updateElement(phoneUpdate);
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
		if(updateResult)
		{
			printWriter.println("<script type=\"text/javascript\">");
			printWriter.println("alert('Update success')");
			printWriter.println("</script>");
			resp.sendRedirect("/PhoneShop/phone/list-phone");
		}else
		{
			printWriter.println("<script type=\"text/javascript\">");
			printWriter.println("alert('Update failed')");
			printWriter.println("</script>");
			resp.sendRedirect("/PhoneShop/phone/edit-phone?id=" + phoneUpdate.getPhoneId());
		}
		
	}
	

}
