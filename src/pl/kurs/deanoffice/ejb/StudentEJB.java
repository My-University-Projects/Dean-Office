package pl.kurs.deanoffice.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entity.Grade;
import pl.kurs.deanoffice.entity.Student;
import pl.kurs.deanoffice.entity.Subject;
import pl.kurs.deanoffice.exception.StudentNotFoundException;
import pl.kurs.deanoffice.exception.SubjectNotFoundException;

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

		for (Student s : resultStudents) {
			s.setGrades(new ArrayList<Grade>()); // Will be changed
		}

		return resultStudents;
	}

	public Student getById(int id) throws StudentNotFoundException {
		System.out.println("Retrieving student by id");
		Student student = this.entityManager.find(Student.class, id);

		if (student == null) {
			throw new StudentNotFoundException();
		}

		student.setGrades(new ArrayList<Grade>());
		return student;
	}

	public void remove(int id) throws StudentNotFoundException {
		System.out.println("Removing student");
		Student student = entityManager.find(Student.class, id);

		if (student == null) {
			throw new StudentNotFoundException();
		}

		entityManager.remove(student);
	}

	public void update(Student student) {
		System.out.println("Updating student");
		student = entityManager.merge(student);

	}

	public List<Integer> getGradesFromSubject(int subjectId, int studentId)
			throws SubjectNotFoundException, StudentNotFoundException {
		if (entityManager.find(Subject.class, subjectId) == null) {
			throw new SubjectNotFoundException();
		}
		if (entityManager.find(Student.class, studentId) == null) {
			throw new StudentNotFoundException();
		}

		Query q = entityManager.createQuery(
				"select g.grade from grades g where g.subject.id = :subjectId and g.student.id = :studentId"); // check
																												// for
																												// names
																												// in
																												// grade
																												// class,
																												// not
																												// in
																												// db
		q.setParameter("studentId", studentId);
		q.setParameter("subjectId", subjectId);
		@SuppressWarnings("unchecked")
		List<Integer> result = q.getResultList();

		return result;
	}

}
