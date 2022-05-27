package pl.kurs.deanoffice.rest;

import java.util.ArrayList;
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

import pl.kurs.deanoffice.ejb.TeacherEJB;
import pl.kurs.deanoffice.entity.Subject;
import pl.kurs.deanoffice.entity.Teacher;
import pl.kurs.deanoffice.repository.TeacherRepository;

@Path("/deanoffice/teachers")
@Consumes("application/json")
@Produces("application/json")
public class TeacherREST implements TeacherRepository {

	@EJB
	TeacherEJB bean;

	@Override
	@POST
	public Response add(Teacher teacher) {
		try {
			teacher.setSubjects(new ArrayList<Subject>());
			bean.add(teacher);
			return Response.ok("Teacher added").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Teacher has not been added")
					.build();
		}
	}

	@Override
	@GET
	public Response get() {
		try {
			List<Teacher> teachers = bean.get();
			return Response.ok(teachers).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Teachers could not been retrieved")
					.build();
		}
	}

	@Override
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		try {

			Teacher teacher = bean.getById(id);
			return Response.ok(teacher).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Teacher with provided id has not been found").build();
		}
	}

	@Override
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") int id) {
		try {
			bean.remove(id);
			return Response.ok("teacher removed").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Teacher with provided id has not been found").build();
		}

	}

	@Override
	@PUT
	public Response update(Teacher teacher) {
		try {
			bean.update(teacher);
			return Response.ok("Teacher updated").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Teacher has not been updated")
					.build();
		}
	}

	@Override
	@POST
	@Path("/assignGradeToStudent")
	public Response assignGradeToStudent(@HeaderParam("studentId") int studentId,
			@HeaderParam("gradeValue") int gradeValue, @HeaderParam("subjectId") int subjectId) {
		try {
			bean.assignGradeToStudent(studentId, gradeValue, subjectId);
			return Response.ok("Grade added").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
					.entity("Something went wrong. Check provided student or subject id").build();
		}

	}

	@Override
	@PUT
	@Path("/assignTeacherToSubject")
	public Response assignTeacherToSubject(@HeaderParam("subjectId") int subjectId,
			@HeaderParam("teacherId") int teacherId) {
		try {
			bean.assignTeacherToSubject(subjectId, teacherId);
			return Response.ok("Teacher assigned to subject").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Check teacher od subject id")
					.build();
		}

	}
}
