package control.admin.employee;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Shop;
import model.service.EmployeeServiceImpl;
import model.service.ShopServiceImpl;

@WebServlet(urlPatterns = {"/employee/add-employee"})
public class AddEmployeeCtrl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Shop> shops = new ShopServiceImpl().getElements(0, new ShopServiceImpl().amountRows());
		req.setAttribute("shops", shops);
		String url = "/admin/employee/add-employee.jsp";
		RequestDispatcher dispatcher  = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
		 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		Employee employee = new Employee();
		employee.setEmployeeName(req.getParameter("txtName").trim());
		employee.setEmployeeAddress(req.getParameter("txtAddress").trim());
		employee.setEmployeeEmail(req.getParameter("email").trim());
		employee.setShopId(new ShopServiceImpl().searchElementByName(req.getParameter("selectShop").trim()).getShopId());
		employee.setEmployeePhoneNumber(req.getParameter("txtPhoneNumber").trim());
		employee.setEmployeePosition(req.getParameter("selectRole").trim());
		employee.setDateCreate(new Date());
		
		employeeService.insertElement(employee);
		String url = "/PhoneShop/employee/list-employees"; 
		resp.sendRedirect(url);
	}
	
}
