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

import com.courtage.dao.interfaces.DaoCategorie;
import com.courtage.dao.jpa.JpaCategorie;
import com.courtage.jpa.model.Categorie;

@Path("/categorie")
public class CategorieResource {

	private DaoCategorie cDao;
	private DaoCategorie getCategorieDao(){
		if(cDao == null){
			cDao = new JpaCategorie();
		}
		return cDao;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Categorie> getAllCategories(){
		return (Collection<Categorie>) getCategorieDao().readAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie insertCategorie(Categorie categorie){
		return getCategorieDao().create(categorie);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie updateCategorie(Categorie categorie){
		return getCategorieDao().update(categorie);
	}
	
	@DELETE
	public void deleteCategorie(@PathParam("id") int idCategorie){
		Categorie c = getCategorieDao().read(idCategorie);
		if(c != null){
			getCategorieDao().delete(c);
		}
	}
}
