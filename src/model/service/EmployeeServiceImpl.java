package model.service;

import java.util.List;
import model.Employee;
import model.dao.EmployeeDAOImpl;
import model.dao.DBManipulateInterface;

public class EmployeeServiceImpl implements DBManipulicateService<Employee> {
	private DBManipulateInterface<Employee> dbManipulicate = new EmployeeDAOImpl();

	@Override
	public boolean insertElement(Employee e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Employee e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Employee e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Employee getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Employee> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
