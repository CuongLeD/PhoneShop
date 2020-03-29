package model.service;

import java.util.List;
import model.Vendor;
import model.dao.VendorDAOImpl;
import model.dao.DBManipulateInterface;

public class VendorServiceImpl implements DBManipulicateService<Vendor> {
	private DBManipulateInterface<Vendor> dbManipulicate = new VendorDAOImpl();

	@Override
	public boolean insertElement(Vendor e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Vendor e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Vendor e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Vendor getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Vendor> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
