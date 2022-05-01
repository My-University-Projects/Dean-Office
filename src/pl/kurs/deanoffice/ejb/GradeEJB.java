package pl.kurs.deanoffice.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entities.Grade;

@Stateless
public class GradeEJB {
	
	@PersistenceContext(name = "deanOffice")
	EntityManager entityManager;
	
	public void add(Grade grade) {
		System.out.println("Creating grade");
		System.out.println(entityManager.toString());
		entityManager.persist(grade);
	}

	public List<Grade> get() {
		Query q = entityManager.createQuery("select g from grades g");
		@SuppressWarnings("unchecked")
		List<Grade> resultGrades = q.getResultList();
		return resultGrades;
	}

	public Grade getById(int id) {
		System.out.println("Retrieving grade by id");
		return this.entityManager.find(Grade.class, id);
	}

	public void remove(int id) {
		System.out.println("Removing grade");
		Grade grade = entityManager.find(Grade.class, id);
		entityManager.remove(grade);

	}

	public void update(Grade grade) {
		System.out.println("Updating grade");
		grade = entityManager.merge(grade);

	}

}
