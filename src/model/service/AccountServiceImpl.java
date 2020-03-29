package model.service;

import java.util.List;
import model.Account;
import model.dao.AccountDAOImpl;
import model.dao.DBManipulateInterface;

public class AccountServiceImpl extends AccountDAOImpl implements DBManipulicateService<Account> {
	private DBManipulateInterface<Account> dbManipulicate = new AccountDAOImpl();

	@Override
	public boolean insertElement(Account e) {
		return dbManipulicate.insertElement(e);
	}

	@Override
	public boolean updateElement(Account e) {
		return dbManipulicate.updateElement(e);
	}

	@Override
	public boolean deleteElement(Account e) {
		return dbManipulicate.deleteElement(e);
	}

	@Override
	public Account getElementById(int id) {
		return dbManipulicate.getElementById(id);
	}

	@Override
	public List<Account> getElements(int beginRow, int amount) {
		return dbManipulicate.getElements(beginRow, amount);
	}

	@Override
	public int amountRows() {
		return dbManipulicate.amountRows();
	}
	

}
