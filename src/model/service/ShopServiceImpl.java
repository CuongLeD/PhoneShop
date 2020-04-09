package model.service;

import java.util.List;
import model.Shop;
import model.dao.ShopDAOImpl;

public class ShopServiceImpl implements DBManipulicateService<Shop>,
	SearchService<Shop>

{
	private ShopDAOImpl shopDAOImpl = new ShopDAOImpl();

	@Override
	public boolean insertElement(Shop e) {
		return shopDAOImpl.insertElement(e);
	}

	@Override
	public boolean updateElement(Shop e) {
		return shopDAOImpl.updateElement(e);
	}

	@Override
	public boolean deleteElement(Shop e) {
		return shopDAOImpl.deleteElement(e);
	}

	@Override
	public Shop getElementById(int id) {
		return shopDAOImpl.getElementById(id);
	}

	@Override
	public List<Shop> getElements(int beginRow, int amount) {
		return shopDAOImpl.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return shopDAOImpl.amountRows();
	}

	@Override
	public Shop searchElementByName(String name) {
		return shopDAOImpl.searchElementByName(name);
	}

	@Override
	public Shop searchById(int id) {
		return shopDAOImpl.searchById(id);
	}

	@Override
	public List<Shop> searchElementsByName(String name) {
		return shopDAOImpl.searchElementsByName(name);
	}
	

}
