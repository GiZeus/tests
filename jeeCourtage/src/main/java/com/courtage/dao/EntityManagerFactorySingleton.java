package com.courtage.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerFactorySingleton {

	private static volatile EntityManagerFactory emf;
	
	/*
	private EntityManagerFactorySingleton(){
		emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
	}
	*/
	
	public static EntityManagerFactory getEMFactory(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
		}
		return emf;
	}
	
	
}
