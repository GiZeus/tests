package com.courtage.web.servlet;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.courtage.dao.GenericDaoJpaImpl;
import com.courtage.jpa.model.Societe;


@Path("/TestJax")
public class TestJax extends Application {

	public TestJax(){}
	
	/*
	@GET
	@Produces("text/html")
	public List<Societe> getSocietes(){
		 GenericDaoJpaImpl<Societe, Serializable> socMgr = new GenericDaoJpaImpl<Societe, Serializable>();
		 List<Societe> societes = (List<Societe>) socMgr.readAll();
		 return societes;
	}
	*/
	
	@GET
	//@Path("/hello")
	@Produces("text/plain")
	public String getXMLString(){
		return "<bonjour>Hello!</bonjour>";
	}
	
	@POST
	@Produces("text/plain")
	public String insertSociete(){
		return "OK";
	}
	
	/*
	@OPTIONS
	public void updateSociete(Societe societe){
		
	}
	
	@DELETE
	public void deleteSociete(Societe societe){
		
	}
	*/
}
