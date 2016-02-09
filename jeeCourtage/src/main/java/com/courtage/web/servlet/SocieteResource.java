package com.courtage.web.servlet;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.courtage.dao.JpaSociete;
import com.courtage.jpa.model.Societe;

@Path("/Societe")
public class SocieteResource {

	JpaSociete jpaSociete;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
	EntityManager em = emf.createEntityManager();
	
	private JpaSociete getJpaSociete(){
		if(jpaSociete == null){
			jpaSociete = new JpaSociete();
		}
		return jpaSociete;
	}
	
	@GET
	//@Produces("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Societe> getAllSocietes(){
		Collection<Societe> societes = getJpaSociete().getAllSocietes();
		return societes;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Societe getSociete(@PathParam("id") int id){
		return getJpaSociete().read(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertSociete(Societe societe){
		System.out.println("Nom : "+societe.getNom());
		Societe s2 = getJpaSociete().create(societe);
		System.out.println(s2.toString());
		return Response.status(200).entity(s2).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSociete(Societe societe){
		Societe updateSociete = getJpaSociete().update(societe);
		return Response.status(200).entity(updateSociete).build();
	}
	
	@DELETE
	@Path("{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSociete(@PathParam("id") int id){
		Response response = null;
		try{
			Societe s = getJpaSociete().read(id);
			if(s == null){
				throw new Exception("");
			}
			getJpaSociete().delete(s);
			response = Response.status(200).build();
		}catch(Exception e){
			response = Response.status(404).build();
		}
		return response;
	}
}
