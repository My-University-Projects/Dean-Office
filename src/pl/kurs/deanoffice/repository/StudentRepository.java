package pl.kurs.deanoffice.repository;


import javax.ws.rs.core.Response;

import pl.kurs.deanoffice.entity.Student;

public interface StudentRepository extends Repository<Student> {
	
	abstract Response getGradesFromSubject(int subjectId, int studentId);

}
