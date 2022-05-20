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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.kurs.deanoffice.ejb.SubjectEJB;
import pl.kurs.deanoffice.entity.Subject;
import pl.kurs.deanoffice.repository.SubjectRepository;

@Path("/deanoffice/subjects")
@Consumes("application/json")
@Produces("application/json")
public class SubjectREST implements SubjectRepository {

	@EJB
	SubjectEJB bean;

	@Override
	@POST
	public Response add(Subject subject) {
		try {
			bean.add(subject);
			return Response.ok("Subject added").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Subject has not been added")
					.build();
		}
	}

	@Override
	@GET
	public Response get() {
		try {
			List<Subject> subjects = bean.get();
			return Response.ok(subjects).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Subjects could not been retrieved")
					.build();
		}
	}

	@Override
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		try {
			Subject subject = bean.getById(id);
			return Response.ok(subject).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Subject with provided id could not been found").build();
		}
	}

	@Override
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") int id) {
		try {
			bean.remove(id);
			return Response.ok("Subject removed").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Subject with provided id could not been found").build();
		}
	}

	@Override
	@PUT
	public Response update(Subject subject) {
		try {
			bean.update(subject);
			return Response.ok("Subject updated").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Subject has not been updated")
					.build();
		}
	}

	@Override
	@GET
	@Path("subjectsTeachedByTeacher/{teacherId}")
	public Response getSubjectsTeachedByTeacherWithProvidedId(@PathParam("teacherId") Integer teacherId) {
		try{
			return Response.ok(bean.getSubjectsTeachedByTeacherWithProvidedId(teacherId)).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. We could not find any subject").build();
		}
	}
	
	
}
