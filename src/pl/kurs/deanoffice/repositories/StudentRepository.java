package pl.kurs.deanoffice.repositories;

import java.util.List;

import pl.kurs.deanoffice.entities.Student;

public interface StudentRepository extends Repository<Student> {
	
	abstract List<Integer> getGradesFromSubject(int subjectId, int studentId);

}
