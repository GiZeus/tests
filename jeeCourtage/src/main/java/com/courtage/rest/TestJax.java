package com.courtage.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;



@Path("/TestJax")
public class TestJax {

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
	@Produces("text/plain")
	public String getXMLString(){
		return "<bonjour>Hello!</bonjour>";
	}
	/*
	@POST
	@Produces("text/plain")
	public String insertSociete(){
		return "OK";
	}
	*/
	
	/*
	@OPTIONS
	public void updateSociete(Societe societe){
		
	}
	
	@DELETE
	public void deleteSociete(Societe societe){
		
	}
	*/
}
