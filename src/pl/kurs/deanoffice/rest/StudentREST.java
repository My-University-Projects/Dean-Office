package pl.kurs.deanoffice.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.kurs.deanoffice.ejb.StudentEJB;
import pl.kurs.deanoffice.entity.Student;
import pl.kurs.deanoffice.repository.StudentRepository;

@Path("/deanoffice/students")
@Consumes("application/json")
@Produces("application/json")
public class StudentREST implements StudentRepository {

	@EJB
	StudentEJB bean;

	@Override
	@POST
	public Response add(Student student) {
		try {
			bean.add(student);
			return Response.ok("Student added").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Studen has not been added")
					.build();
		}

	}

	@Override
	@GET
	public Response get() {
		try {
			List<Student> students = bean.get();
			return Response.ok(students).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Students could not been retrieved")
					.build();
		}
	}

	@Override
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		try {
			Student student = bean.getById(id);
			return Response.ok(student).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Student with provided id has not been found").build();
		}
	}

	@Override
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") int id) {
		try {
			bean.remove(id);
			return Response.ok("Student removed").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Student with provided id has not been found").build();
		}
	}

	@Override
	@PUT
	public Response update(Student student) {
		try {
			bean.update(student);
			return Response.ok("Student updated").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Student has not been updated")
					.build();
		}
	}

	@Override
	@GET
	@Path("/grades/{subjectId}")
	public Response getGradesFromSubject(@PathParam("subjectId") int subjectId,
			@HeaderParam("studentId") int studentId) {
		try {
			return Response.ok(bean.getGradesFromSubject(subjectId, studentId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Check provided student id")
					.build();
		}
	}
}
