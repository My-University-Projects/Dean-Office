package pl.kurs.deanoffice.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kurs.deanoffice.entity.Grade;

@Stateless
public class GradeEJB {
	
	@PersistenceContext(name = "deanOffice")
	EntityManager entityManager;
	
	public void add(Grade grade) {
		System.out.println("Creating grade");
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
		Grade grade = this.entityManager.find(Grade.class, id);
		return grade;
	}

	public void remove(int id) {
		System.out.println("Removing grade");
		Grade grade = entityManager.find(Grade.class, id);
		entityManager.remove(grade);

	}

	public void update(Grade grade) {
		System.out.println("Updating grade");
		Grade oldGrade = entityManager.find(Grade.class, grade.getId());
		oldGrade.setGrade(grade.getGrade());
		entityManager.persist(oldGrade);
	}
	
	public Float getGradesAverageFromSubjectWithProvidedId(Integer subjectId){
		Query q = entityManager.createQuery("select g from grades g where g.subject.id = :subjectId");
		q.setParameter("subjectId", subjectId);
		List<Grade> grades = q.getResultList();
		int size = grades.size();
		Float gradesSum = 0.0f;
		for(Grade g : grades){
			gradesSum += g.getGrade();
		}
		
		return gradesSum/size;
	}

}
