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
import pl.kurs.deanoffice.entity.Teacher;

@Stateless
public class TeacherEJB {

	@PersistenceContext(name="deanOffice")
	EntityManager entityManager;
	
	public void add(Teacher teacher) {
		System.out.println("Creating teacher");
		
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(this.entityManager.find(Subject.class, 1));
		teacher.setSubjects(subjects);
		entityManager.persist(teacher);
	}

	public List<Teacher> get() {
		Query q = entityManager.createQuery("select t from teachers t");
		@SuppressWarnings("unchecked")
		List<Teacher> resultTeachers = q.getResultList();
		for (Teacher t : resultTeachers) {
			t.setSubjects(new ArrayList<Subject>()); // Will be changed
		}
		return resultTeachers;
	}

	public Teacher getById(int id) {
		System.out.println("Retrieving teacher by id");
		Teacher teacher = this.entityManager.find(Teacher.class, id);
		teacher.setSubjects(new ArrayList<Subject>());
		return teacher;
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
	
	public void assignGradeToStudent(int studentId, int gradeValue, int subjectId){
		Grade grade = new Grade();
		grade.setGrade(gradeValue);
		grade.setStudent(this.entityManager.find(Student.class, studentId));
		grade.setSubject(this.entityManager.find(Subject.class, subjectId));
		this.entityManager.persist(grade);
		
	}
	
	public void assignTeacherToSubject(int subjectId, int teacherId){
		Teacher teacher = this.entityManager.find(Teacher.class, teacherId);
		Subject subject = this.entityManager.find(Subject.class, subjectId);
		
		teacher.getSubjects().add(subject);
		subject.getTeachers().add(teacher);
		
		this.entityManager.merge(teacher);
		this.entityManager.merge(subject);
		
	}
}
