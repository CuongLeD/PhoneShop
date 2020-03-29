package model.service;

import java.util.List;
import model.Phone;
import model.dao.PhoneDAOImpl;
import model.dao.DBManipulateInterface;

public class PhoneServiceImpl implements DBManipulicateService<Phone> {
	private DBManipulateInterface<Phone> dbManipulicate = new PhoneDAOImpl();

	@Override
	public boolean insertElement(Phone e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Phone e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Phone e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Phone getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Phone> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
