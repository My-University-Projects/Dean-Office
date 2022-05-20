package pl.kurs.deanoffice.repository;

import javax.ws.rs.core.Response;

import pl.kurs.deanoffice.entity.Grade;

public interface GradeRepository extends Repository<Grade> {
	
	public Response getGradesAverageFromSubjectWithProvidedId(Integer subjectId);
	
}
