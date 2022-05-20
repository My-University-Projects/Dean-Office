package pl.kurs.deanoffice.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entity.Grade;
import pl.kurs.deanoffice.entity.Subject;
import pl.kurs.deanoffice.entity.Teacher;

@Stateless
public class SubjectEJB {
	
	@PersistenceContext(name = "deanOffice")
	EntityManager entityManager;

	public void add(Subject subject) {
		System.out.println("Creating subject");
		entityManager.persist(subject);
	}
	public List<Subject> get() {
		Query q = entityManager.createQuery("select s from subjects s");
		@SuppressWarnings("unchecked")
		List<Subject> resultSubjects = q.getResultList();
		for (Subject s : resultSubjects) {
			s.setGrades(new ArrayList<Grade>());
			s.setTeachers(new ArrayList<Teacher>());
		}
		return resultSubjects;
	}

	public Subject getById(int id) {
		System.out.println("Retrieving subject by id");
		Subject subject = this.entityManager.find(Subject.class, id);
		subject.setGrades(new ArrayList<Grade>());
		subject.setTeachers(new ArrayList<Teacher>());
		return subject;
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
	

	public List<Subject> getSubjectsTeachedByTeacherWithProvidedId(Integer teacherId){
		Query q = entityManager.createQuery("select t.subjects from teachers t where"
				+ " t.id = :teacher_id");
		q.setParameter("teacher_id",teacherId);
		@SuppressWarnings("unchecked")
		List<Subject> resultSubjects = q.getResultList();
		for (Subject s : resultSubjects) {
			s.setGrades(new ArrayList<Grade>());
			s.setTeachers(new ArrayList<Teacher>());
		}
		return resultSubjects;
	}

}
