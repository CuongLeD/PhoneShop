package control.dao;

import java.util.List;

public interface DBManipulateInterface <E>{
	public boolean insertElement(E e);
	public boolean updateElement(E e);
	public boolean deleteElement(E e);
	public E getElementById(int id);
	public List<E> getElements(int beginRow, int amount);
	public int amountRows();
	

}
