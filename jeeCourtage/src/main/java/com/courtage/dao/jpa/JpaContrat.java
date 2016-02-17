package com.courtage.dao.jpa;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Query;

import com.courtage.dao.commun.GenericDaoJpaImpl;
import com.courtage.dao.interfaces.DaoContrat;
import com.courtage.jpa.model.Contrat;

public class JpaContrat extends GenericDaoJpaImpl<Contrat, Serializable> implements DaoContrat {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Contrat> getContratsForSociete(int idSociete)
	{
		StringBuilder query = new StringBuilder("Select c From Contrat c where c.societe.id = :idSociete");
		Query q = entityManager.createQuery(query.toString());
		q.setParameter("idSociete", idSociete);
		return (Collection<Contrat>) q.getResultList();
	}
}
