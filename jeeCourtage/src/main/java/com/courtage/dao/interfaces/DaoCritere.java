package com.courtage.dao.interfaces;

import java.io.Serializable;
import java.util.Collection;

import com.courtage.dao.commun.GenericDao;
import com.courtage.jpa.model.Critere;

public interface DaoCritere extends GenericDao<Critere, Serializable>{

	Collection<Critere> getCategorieCriteres(int idCategorie);
}
