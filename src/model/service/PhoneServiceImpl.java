package model.service;

import java.util.List;
import model.Phone;
import model.dao.PhoneDAOImpl;

public class PhoneServiceImpl implements DBManipulicateService<Phone>,
	SearchService<Phone>
{
	private PhoneDAOImpl phoneDAO = new PhoneDAOImpl();

	@Override
	public boolean insertElement(Phone e) {
		return phoneDAO.insertElement(e);
	}

	@Override
	public boolean updateElement(Phone e) {
		return phoneDAO.updateElement(e);
	}

	@Override
	public boolean deleteElement(Phone e) {
		return phoneDAO.deleteElement(e);
	}

	@Override
	public Phone getElementById(int id) {
		return phoneDAO.getElementById(id);
	}

	@Override
	public List<Phone> getElements(int beginRow, int amount) {
		return phoneDAO.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return phoneDAO.amountRows();
	}

	@Override
	public Phone searchElementByName(String name) {
		return phoneDAO.searchElementByName(name);
	}

	@Override
	public Phone searchById(int id) {
		return phoneDAO.searchById(id);
	}

	@Override
	public List<Phone> searchElementsByName(String name) {
		return phoneDAO.searchElementsByName(name);
	}
	

}
