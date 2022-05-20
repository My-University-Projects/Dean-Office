package pl.kurs.deanoffice.rest;

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

import pl.kurs.deanoffice.ejb.GradeEJB;
import pl.kurs.deanoffice.entity.Grade;
import pl.kurs.deanoffice.repository.GradeRepository;

@Path("/deanoffice/grades")
@Consumes("application/json")
@Produces("application/json")
public class GradeREST implements GradeRepository {

	@EJB
	GradeEJB bean;

	@Override
	@POST
	public Response add(Grade grade) {
		try {
			bean.add(grade);
			return Response.ok("Grade added").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Grade has not been added").build();
		}

	}

	@Override
	@GET
	public Response get() {
		try {
			return Response.ok(bean.get()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Grades could not been retrieved")
					.build();
		}
	}

	@Override
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		try {
			return Response.ok(bean.getById(id)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Grade with provided id has not been found").build();
		}
	}

	@Override
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") int id) {
		try {
			bean.remove(id);
			return Response.ok("Grade removed").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Grade with provided id has not been found").build();
		}
	}

	@Override
	@PUT
	public Response update(Grade grade) {
		try {
			bean.update(grade);
			return Response.ok("Grade updated").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. Grade has not been updated")
					.build();
		}
	}

	@Override
	@GET
	@Path("/getGradesAverageFromSubject/{subjectId}")
	public Response getGradesAverageFromSubjectWithProvidedId(@PathParam("subjectId") Integer subjectId) {
		try{
			return Response.ok(bean.getGradesAverageFromSubjectWithProvidedId(subjectId)).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong. We could not compute average").build();
		}
	}

}
