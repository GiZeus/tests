package com.courtage.dao.interfaces;

import java.io.Serializable;
import java.util.Collection;

import com.courtage.dao.commun.GenericDao;
import com.courtage.jpa.model.Contrat;

public interface DaoContrat extends GenericDao<Contrat, Serializable>{

	Collection<Contrat> getContratsForSociete(int idSociete);
}
