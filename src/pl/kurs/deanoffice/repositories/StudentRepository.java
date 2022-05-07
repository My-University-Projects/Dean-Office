package pl.kurs.deanoffice.repositories;


import javax.ws.rs.core.Response;

import pl.kurs.deanoffice.entities.Student;

public interface StudentRepository extends Repository<Student> {
	
	abstract Response getGradesFromSubject(int subjectId, int studentId);

}
