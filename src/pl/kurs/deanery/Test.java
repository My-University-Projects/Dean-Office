package pl.kurs.deanery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Run this as application to check what schema database will be created
 */
public class Test {
	public static void main(String[] args) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("komis");
		EntityManager manager = managerFactory.createEntityManager();

	}
}
