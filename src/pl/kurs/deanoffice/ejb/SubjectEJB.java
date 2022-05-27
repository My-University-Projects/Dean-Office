package pl.kurs.deanoffice.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entity.Grade;
import pl.kurs.deanoffice.entity.Subject;
import pl.kurs.deanoffice.entity.Teacher;
import pl.kurs.deanoffice.exception.SubjectNotFoundException;
import pl.kurs.deanoffice.exception.TeacherNotFoundException;

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

	public Subject getById(int id) throws SubjectNotFoundException {
		System.out.println("Retrieving subject by id");
		Subject subject = this.entityManager.find(Subject.class, id);

		if (subject == null) {
			throw new SubjectNotFoundException();
		}

		subject.setGrades(new ArrayList<Grade>());
		subject.setTeachers(new ArrayList<Teacher>());
		return subject;
	}

	public void remove(int id) throws SubjectNotFoundException {
		System.out.println("Removing subject");
		Subject subject = entityManager.find(Subject.class, id);

		if (subject == null) {
			throw new SubjectNotFoundException();
		}

		entityManager.remove(subject);
	}

	public void update(Subject subject) {
		System.out.println("Updating subject");
		subject = entityManager.merge(subject);
	}

	public List<Subject> getSubjectsTaughtByTeacherWithProvidedId(Integer teacherId) throws TeacherNotFoundException {
		if (entityManager.find(Teacher.class, teacherId) == null) {
			throw new TeacherNotFoundException();
		}

		Query q = entityManager.createQuery("select t.subjects from teachers t where" + " t.id = :teacher_id");
		q.setParameter("teacher_id", teacherId);
		@SuppressWarnings("unchecked")
		List<Subject> resultSubjects = q.getResultList();

		for (Subject s : resultSubjects) {
			s.setGrades(new ArrayList<Grade>());
			s.setTeachers(new ArrayList<Teacher>());
		}
		return resultSubjects;
	}

}
