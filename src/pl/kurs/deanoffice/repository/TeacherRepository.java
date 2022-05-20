package pl.kurs.deanoffice.repository;

import javax.ws.rs.core.Response;

import pl.kurs.deanoffice.entity.Teacher;

public interface TeacherRepository extends Repository<Teacher> {
	
	abstract Response assignGradeToStudent(int studentId, int gradeValue, int subjectId);
	
	abstract Response assignTeacherToSubject(int subjectId, int teacherId);

}
