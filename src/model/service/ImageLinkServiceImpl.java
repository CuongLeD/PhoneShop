package model.service;

import java.util.List;
import model.ImageLink;
import model.dao.ImageLinkDAOImpl;
import model.dao.DBManipulateInterface;

public class ImageLinkServiceImpl implements DBManipulicateService<ImageLink> {
	private DBManipulateInterface<ImageLink> dbManipulicate = new ImageLinkDAOImpl();

	@Override
	public boolean insertElement(ImageLink e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(ImageLink e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(ImageLink e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public ImageLink getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<ImageLink> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	
	public static List<ImageLink> getByPhoneId(int phoneId)
	{
		return ImageLinkDAOImpl.getByPhoneId(phoneId);
	}
	

}
