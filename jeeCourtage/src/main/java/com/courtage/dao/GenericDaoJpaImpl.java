package com.courtage.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.*;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;
	
	//@PersistenceContext(unitName="MaBaseDeTestPU")
	protected EntityManagerFactory emf;
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
		entityManager = EntityManagerFactorySingleton.getEMFactory().createEntityManager();
	    ParameterizedType genericSuperclass = (ParameterizedType) getClass()
	         .getGenericSuperclass();
	    this.entityClass = (Class<T>) genericSuperclass
	         .getActualTypeArguments()[0];
	}
	
	public T create(T t) {
		this.entityManager.getTransaction().begin();
	    this.entityManager.persist(t);
	    this.entityManager.getTransaction().commit();
	    return t;
	}
	
	public T read(PK id) {
	    return this.entityManager.find(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public <Collection>T readAll() {
		Query q = entityManager.createQuery("SELECT o FROM "+entityClass.getName() +" o");
	    return (T) q.getResultList();
	}
	
	public T update(T t) {
		this.entityManager.getTransaction().begin();
	    T updatedEntity = this.entityManager.merge(t);
	    this.entityManager.getTransaction().commit();
	    return updatedEntity;
	}
	
	
	public void delete(T t) {
		this.entityManager.getTransaction().begin();
	    t = this.entityManager.merge(t);
	    this.entityManager.remove(t);
	    this.entityManager.getTransaction().commit();
	}
}