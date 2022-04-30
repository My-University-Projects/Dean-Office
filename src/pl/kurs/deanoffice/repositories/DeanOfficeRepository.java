package pl.kurs.deanoffice.repositories;

import pl.kurs.deanoffice.entities.*;

public interface DeanOfficeRepository<T> extends Repository<T> {
	
	void createStudent(Student student);
	void createTeacher(Teacher teacher);
	void createGrade(Grade grade);
	void createSubject(Subject subject);

}
