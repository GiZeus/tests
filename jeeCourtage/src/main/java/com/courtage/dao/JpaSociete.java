package com.courtage.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.courtage.jpa.model.Societe;

public class JpaSociete extends GenericDaoJpaImpl<Societe, Serializable> {

	public JpaSociete(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Societe> getAllSocietes(){
		return (Collection<Societe>) readAll();
		/*
		Societe s = read(1);
		Collection<Societe> list = new ArrayList<Societe>();
		list.add(s);
		return list;
		*/
	}
}
