package com.courtage.dao.jpa;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Query;

import com.courtage.dao.commun.GenericDaoJpaImpl;
import com.courtage.dao.interfaces.DaoCritere;
import com.courtage.jpa.model.Critere;

public class JpaCritere extends GenericDaoJpaImpl<Critere, Serializable> implements DaoCritere {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Critere> getCategorieCriteres(int idCategorie) {
		StringBuilder sb = new StringBuilder("Select c From Critere where c.categorie.id = :idCategorie");
		Query q = entityManager.createQuery(sb.toString());
		return q.getResultList();
	}

	
	
}
