package pl.kurs.deanoffice.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entities.Teacher;

@Stateless
public class TeacherEJB {

	@PersistenceContext(name="deanOffice")
	EntityManager entityManager;
	
	public void add(Teacher teacher) {
		System.out.println("Creating teacher");
		System.out.println(entityManager.toString());
		entityManager.persist(teacher);
	}

	public List<Teacher> get() {
		Query q = entityManager.createQuery("select t from teachers t");
		@SuppressWarnings("unchecked")
		List<Teacher> resultTeachers = q.getResultList();
		return resultTeachers;
	}

	public Teacher getById(int id) {
		System.out.println("Retrieving teacher by id");
		return this.entityManager.find(Teacher.class, id);
	}

	public void remove(int id) {
		System.out.println("Removing teacher");
		Teacher teacher = entityManager.find(Teacher.class, id);
		entityManager.remove(teacher);
		
	}

	public void update(Teacher teacher) {
		System.out.println("Updating teacher");
		teacher = entityManager.merge(teacher);
		
	}
}
