package pl.kurs.deanoffice.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entities.Student;

@Stateless
public class StudentEJB {

	@PersistenceContext(name = "deanOffice")
	EntityManager entityManager;

	public void add(Student student) {
		System.out.println("Creating student");
		System.out.println(entityManager.toString());
		entityManager.persist(student);
	}

	public List<Student> get() {
		Query q = entityManager.createQuery("select s from students s");
		@SuppressWarnings("unchecked")
		List<Student> resultStudents = q.getResultList();
		return resultStudents;
	}

	public Student getById(int id) {
		System.out.println("Retrieving student by id");
		return this.entityManager.find(Student.class, id);
	}

	public void remove(int id) {
		System.out.println("Removing student");
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);

	}

	public void update(Student student) {
		System.out.println("Updating student");
		student = entityManager.merge(student);

	}
}
