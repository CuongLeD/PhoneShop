package model.service;

import java.util.List;
import model.Category;
import model.dao.CategoryDAOImpl;
import model.dao.DBManipulateInterface;

public class CategoryServiceImpl implements DBManipulicateService<Category> {
	private DBManipulateInterface<Category> dbManipulicate = new CategoryDAOImpl();

	@Override
	public boolean insertElement(Category e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Category e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Category e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Category getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Category> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
