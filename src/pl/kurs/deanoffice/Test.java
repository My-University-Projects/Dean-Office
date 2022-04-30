package pl.kurs.deanoffice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.kurs.deanoffice.entities.Teacher;

/*
 * Run this as application to check what schema database will be created
 */
public class Test {
	public static void main(String[] args) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("deanOffice");
		EntityManager manager = managerFactory.createEntityManager();
		
		Teacher teacher = new Teacher();
		teacher.setName("Jan");
		teacher.setSurname("Kowalski");
		manager.getTransaction().begin();
		manager.persist(teacher);
		manager.getTransaction().commit();
		manager.close();

	}
}
