package pl.kurs.deanoffice.repositories;

import java.util.List;

public interface Repository<T> {
	
	abstract String add(T item);
	abstract List<T> get();
	abstract T getById(int id);
	abstract String remove(int id);
	abstract String update(T item);
	
}
