package pl.kurs.deanoffice.repository;

import javax.ws.rs.core.Response;

public interface Repository<T> {

	abstract Response add(T item);

	abstract Response get();

	abstract Response getById(int id);

	abstract Response remove(int id);

	abstract Response update(T item);

}
