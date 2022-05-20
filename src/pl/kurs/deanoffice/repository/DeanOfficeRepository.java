package pl.kurs.deanoffice.repository;

import pl.kurs.deanoffice.entity.*;

public interface DeanOfficeRepository<T> extends Repository<T> {
	
	void createStudent(Student student);
	void createTeacher(Teacher teacher);
	void createGrade(Grade grade);
	void createSubject(Subject subject);

}
