package pl.kurs.deanoffice.repository;


import javax.ws.rs.core.Response;

import pl.kurs.deanoffice.entity.Subject;

public interface SubjectRepository extends Repository<Subject> {
	
	public Response getSubjectsTaughtByTeacherWithProvidedId(Integer teacherId);

}
