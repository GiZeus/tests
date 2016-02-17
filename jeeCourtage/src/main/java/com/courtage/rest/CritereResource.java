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

import com.courtage.dao.interfaces.DaoCritere;
import com.courtage.dao.jpa.JpaCritere;
import com.courtage.jpa.model.Critere;

@Path("/critere")
public class CritereResource {

	private DaoCritere cDao;
	
	private DaoCritere getCritereDao()
	{
		if(cDao == null){
			cDao = new JpaCritere();
		}
		return cDao;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Critere> getAllCriteres(){
		return (Collection<Critere>)getCritereDao().readAll();
	}
	
	@GET
	@Path("categorie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Critere> getCategorieCritere(@PathParam("id") int idCategorie){
		return (Collection<Critere>)getCritereDao(). getCategorieCriteres(idCategorie);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Critere insertCritere(Critere critere)
	{
		Critere critereDB = getCritereDao().create(critere);
		return critereDB;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Critere updateCritere(Critere critere){
		return (Critere)getCritereDao().update(critere);
	}
	
	@DELETE
	@PathParam("{id}")
	public void deleteCritere(@PathParam("id")int idCritere)
	{
		Critere c = getCritereDao().read(idCritere);
		if(c != null){
			getCritereDao().delete(c);
		}
	}
	
}
