package model.dao;

import java.util.List;

public interface SearchInteface <E>{
	public E searchElementByName(String name);
	public E searchById(int id);
	public List<E> searchElementsByName(String name);
}
