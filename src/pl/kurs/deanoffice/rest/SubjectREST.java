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

import pl.kurs.deanoffice.ejb.SubjectEJB;
import pl.kurs.deanoffice.entities.Subject;
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
		return bean.get();
	}

	@Override
	@GET
	@Path("/{id}")
	public Subject getById(@PathParam("id") int id) {
		return bean.getById(id);
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
