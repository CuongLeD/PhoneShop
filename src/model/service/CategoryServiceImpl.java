package model.service;

import java.util.List;
import model.Category;
import model.dao.CategoryDAOImpl;

public class CategoryServiceImpl implements DBManipulicateService<Category>,
	SearchService<Category>
{
	private CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

	@Override
	public boolean insertElement(Category e) {
		return categoryDAOImpl.insertElement(e);
	}

	@Override
	public boolean updateElement(Category e) {
		return categoryDAOImpl.updateElement(e);
	}

	@Override
	public boolean deleteElement(Category e) {
		return categoryDAOImpl.deleteElement(e);
	}

	@Override
	public Category getElementById(int id) {
		return categoryDAOImpl.getElementById(id);
	}

	@Override
	public List<Category> getElements(int beginRow, int amount) {
		return categoryDAOImpl.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return categoryDAOImpl.amountRows();
	}

	@Override
	public Category searchElementByName(String name) {
		return categoryDAOImpl.searchElementByName(name);
	}

	@Override
	public Category searchById(int id) {
		return categoryDAOImpl.searchById(id);
	}

	@Override
	public List<Category> searchElementsByName(String name) {
		return categoryDAOImpl.searchElementsByName(name);
	}
	

}
