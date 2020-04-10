package control.admin.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.service.EmployeeServiceImpl;

@WebServlet(urlPatterns = {"/employee/delete-employee"})
public class DeleteEmployeeCtrl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeServiceImpl employeeService;
	
	@Override
	public void init() throws ServletException {
		employeeService = new EmployeeServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int employeeId = Integer.valueOf(req.getParameter("id"));
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setStatus(false);
		employeeService.deleteElement(employee);
		
		String url = "/PhoneShop/employee/list-employees";
		resp.sendRedirect(url);
	}
	

}
