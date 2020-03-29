package model.service;

import java.util.List;
import model.Shop;
import model.dao.ShopDAOImpl;
import model.dao.DBManipulateInterface;

public class ShopServiceImpl implements DBManipulicateService<Shop> {
	private DBManipulateInterface<Shop> dbManipulicate = new ShopDAOImpl();

	@Override
	public boolean insertElement(Shop e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Shop e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Shop e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Shop getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Shop> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
