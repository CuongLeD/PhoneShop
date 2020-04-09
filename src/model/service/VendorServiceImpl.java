package model.service;

import java.util.List;
import model.Vendor;
import model.dao.VendorDAOImpl;

public class VendorServiceImpl implements DBManipulicateService<Vendor>,
										SearchService<Vendor>
{
	private VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
	

	@Override
	public boolean insertElement(Vendor e) {
		return vendorDAOImpl.insertElement(e);
	}

	@Override
	public boolean updateElement(Vendor e) {
		return vendorDAOImpl.updateElement(e);
	}

	@Override
	public boolean deleteElement(Vendor e) {
		return vendorDAOImpl.deleteElement(e);
	}

	@Override
	public Vendor getElementById(int id) {
		return vendorDAOImpl.getElementById(id);
	}

	@Override
	public List<Vendor> getElements(int beginRow, int amount) {
		return vendorDAOImpl.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return vendorDAOImpl.amountRows();
	}

	@Override
	public Vendor searchElementByName(String name) {
		return vendorDAOImpl.searchElementByName(name);
	}

	@Override
	public Vendor searchById(int id) {
		return vendorDAOImpl.searchById(id);
	}

	@Override
	public List<Vendor> searchElementsByName(String name) {
		return vendorDAOImpl.searchElementsByName(name);
	}
	

}
