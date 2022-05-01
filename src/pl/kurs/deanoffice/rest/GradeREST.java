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

import pl.kurs.deanoffice.ejb.GradeEJB;
import pl.kurs.deanoffice.entities.Grade;
import pl.kurs.deanoffice.repositories.GradeRepository;

@Path("/deanoffice/grades")
@Consumes("application/json")
@Produces("application/json")
public class GradeREST implements GradeRepository {

	@EJB
	GradeEJB bean;

	@Override
	@POST
	public String add(Grade grade) {
		bean.add(grade);
		return "Grade added";
	}

	@Override
	@GET
	public List<Grade> get() {
		return bean.get();
	}

	@Override
	@GET
	@Path("/{id}")
	public Grade getById(@PathParam("id") int id) {
		return bean.getById(id);
	}

	@Override
	@DELETE
	@Path("/{id}")
	public String remove(@PathParam("id") int id) {
		bean.remove(id);
		return "Grade removed";
	}

	@Override
	@PUT
	public String update(Grade grade) {
		try {
			bean.update(grade);
			return "Grade updated";
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong. Grade has not been updated";
		}
	}

}
