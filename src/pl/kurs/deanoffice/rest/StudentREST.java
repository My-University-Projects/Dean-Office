package pl.kurs.deanoffice.rest;

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

import pl.kurs.deanoffice.ejb.StudentEJB;
import pl.kurs.deanoffice.entities.Student;
import pl.kurs.deanoffice.repositories.StudentRepository;

@Path("/deanoffice/students")
@Consumes("application/json")
@Produces("application/json")
public class StudentREST implements StudentRepository {

	@EJB
	StudentEJB bean;

	@Override
	@POST
	public String add(Student student) {
		bean.add(student);
		return "Student added";
	}

	@Override
	@GET
	public List<Student> get() {
		return bean.get();
	}

	@Override
	@GET
	@Path("/{id}")
	public Student getById(@PathParam("id") int id) {
		return bean.getById(id);
	}

	@Override
	@DELETE
	@Path("/{id}")
	public String remove(@PathParam("id") int id) {
		bean.remove(id);
		return "Student removed";
	}

	@Override
	@PUT
	public String update(Student student) {
		try {
			bean.update(student);
			return "Student updated";
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong. Student has not been updated";
		}
	}

}
