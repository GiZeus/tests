package com.courtage.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.courtage.dao.interfaces.DaoContrat;
import com.courtage.dao.jpa.JpaContrat;
import com.courtage.jpa.model.Contrat;

@Path("/contrat")
public class ContratResource {

	private DaoContrat jpaContrat;
	
	private DaoContrat getJpaContrat(){
		if(jpaContrat == null){
			jpaContrat = new JpaContrat();
		}
		return jpaContrat;
	}
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Contrat> getAllContrats(){
		return (Collection<Contrat>) getJpaContrat().readAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Contrat getContratById(@PathParam("id") int idContrat)
	{
		return getJpaContrat().read(idContrat);
	}
	
	@GET
	@Path("/Societe/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Contrat> getContratsForSociete(@PathParam("id") int idSociete)
	{
		return (Collection<Contrat>) getJpaContrat().getContratsForSociete(idSociete);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertContrat(Contrat contrat)
	{
		Response response = null;
		try{
			Contrat c1 = getJpaContrat().create(contrat);
			response = Response.status(200).entity(c1).build();
		}catch(Exception e){
			response = Response.status(400).build();
		}
		return response;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateContrat(Contrat contrat)
	{
		Response response = null;
		try{
			Contrat c = getJpaContrat().update(contrat);
			response = Response.status(200).entity(c).build();
		}catch(Exception e){
			e.printStackTrace();
			response = Response.status(400).build();
		}
		return response;
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteContrat(@PathParam("id") int id)
	{
		Response response = null;
		try{
			Contrat c = getJpaContrat().read(id);
			getJpaContrat().delete(c);
			response = Response.status(200).build();
		}catch(Exception e){
			response = Response.status(400).build();
		}
		return response;
	}
}
