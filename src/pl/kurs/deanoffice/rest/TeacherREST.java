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

import pl.kurs.deanoffice.ejb.TeacherEJB;
import pl.kurs.deanoffice.entities.Subject;
import pl.kurs.deanoffice.entities.Teacher;
import pl.kurs.deanoffice.repositories.TeacherRepository;

@Path("/deanoffice/teachers")
@Consumes("application/json")
@Produces("application/json")
public class TeacherREST implements TeacherRepository {

	@EJB
	TeacherEJB bean;

	@Override
	@POST
	public String add(Teacher teacher) {
		teacher.setSubjects(new ArrayList<Subject>());
		bean.add(teacher);
		return "Teacher added";

	}

	@Override
	@GET
	public List<Teacher> get() {
		return bean.get();
	}

	@Override
	@GET
	@Path("/{id}")
	public Teacher getById(@PathParam("id") int id) {
		return bean.getById(id);
	}

	@Override
	@DELETE
	@Path("/{id}")
	public String remove(@PathParam("id") int id) {
		bean.remove(id);
		return "teacher removed";

	}

	@Override
	@PUT
	public String update(Teacher teacher) {
		try {
			bean.update(teacher);
			return "Teacher updated";
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong. Teacher has not been updated";
		}
	}
}
