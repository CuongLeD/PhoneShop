package model.service;

import java.util.List;
import model.Employee;
import model.dao.EmployeeDAOImpl;

public class EmployeeServiceImpl implements DBManipulicateService<Employee>,
SearchService<Employee>
{
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

	@Override
	public boolean insertElement(Employee e) {
		return employeeDAO.insertElement(e);
	}

	@Override
	public boolean updateElement(Employee e) {
		return employeeDAO.updateElement(e);
	}

	@Override
	public boolean deleteElement(Employee e) {
		return employeeDAO.deleteElement(e);
	}

	@Override
	public Employee getElementById(int id) {
		return employeeDAO.getElementById(id);
	}

	@Override
	public List<Employee> getElements(int beginRow, int amount) {
		return employeeDAO.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return employeeDAO.amountRows();
	}

	@Override
	public Employee searchElementByName(String name) {
		return employeeDAO.searchElementByName(name);
	}

	@Override
	public Employee searchById(int id) {
		return employeeDAO.searchById(id);
	}

	@Override
	public List<Employee> searchElementsByName(String name) {
		return employeeDAO.searchElementsByName(name);
	}
	

}
