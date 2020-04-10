package control.admin.employee;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.service.EmployeeServiceImpl;

@WebServlet(urlPatterns= {"/employee/list-employees"})
public class ListEmployeeCtrl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private List<Employee> employees;
	private EmployeeServiceImpl employeeService;
	
	@Override
	public void init() throws ServletException {
		employeeService = new EmployeeServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		employees = employeeService.getElements(0, 20);
		req.setAttribute("employees", employees);
		System.out.println(new SimpleDateFormat("yy:MM:dd HH:mm:ss").format(employees.get(0).getDateCreate()));
		String url = "/admin/employee/list-employees.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String employeeName = req.getParameter("search").trim();
		employees = employeeService.searchElementsByName(employeeName);
		req.setAttribute("employees", employees);
		String url = "/admin/employee/list-employees.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
