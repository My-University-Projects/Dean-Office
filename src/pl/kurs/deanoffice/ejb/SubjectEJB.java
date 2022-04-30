package pl.kurs.deanoffice.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entities.Subject;

@Stateless
public class SubjectEJB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "deanOffice")
	EntityManager entityManager;

	public void add(Subject subject) {
		System.out.println("Creating subject");
		System.out.println(entityManager.toString());
		entityManager.persist(subject);
	}

	public List<Subject> get() {
		Query q = entityManager.createQuery("select s from subjects s");
		@SuppressWarnings("unchecked")
		List<Subject> resultSubjects = q.getResultList();
		return resultSubjects;
	}

	public Subject getById(int id) {
		System.out.println("Retrieving subject by id");
		return this.entityManager.find(Subject.class, id);
	}

	public void remove(int id) {
		System.out.println("Removing subject");
		Subject subject = entityManager.find(Subject.class, id);
		entityManager.remove(subject);

	}

	public void update(Subject subject) {
		System.out.println("Updating subject");
		subject = entityManager.merge(subject);

	}

}
