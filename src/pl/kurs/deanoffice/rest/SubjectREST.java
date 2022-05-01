package pl.kurs.deanoffice.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.kurs.deanoffice.ejb.SubjectEJB;
import pl.kurs.deanoffice.entities.Grade;
import pl.kurs.deanoffice.entities.Subject;
import pl.kurs.deanoffice.entities.Teacher;
import pl.kurs.deanoffice.repositories.SubjectRepository;

@Path("/deanoffice/subjects")
@Consumes("application/json")
@Produces("application/json")
public class SubjectREST implements SubjectRepository {

	@EJB
	SubjectEJB bean;

	@Override
	@POST
	public String add(Subject subject) {
		bean.add(subject);
		return "Subject added";
	}

	@Override
	@GET
	public List<Subject> get() {
		List<Subject> subjects = bean.get();
		for (Subject s : subjects) {
			s.setGrades(new ArrayList<Grade>());
			s.setTeachers(new ArrayList<Teacher>());
		}
		return subjects;
	}

	@Override
	@GET
	@Path("/{id}")
	public Subject getById(@PathParam("id") int id) {
		Subject subject = bean.getById(id);
		subject.setGrades(new ArrayList<Grade>());
		subject.setTeachers(new ArrayList<Teacher>());
		return subject;
	}

	@Override
	@DELETE
	@Path("/{id}")
	public String remove(@PathParam("id") int id) {
		bean.remove(id);
		return "Subject removed";
	}

	@Override
	@PUT
	public String update(Subject subject) {
		try {
			bean.update(subject);
			return "Subject updated";
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong. Subject has not been updated";
		}
	}
}
