package com.courtage.jpa.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.courtage.data.adapter.IntegerAdapter;
import com.sun.xml.bind.CycleRecoverable;

@Entity
@Table(name="Categorie")
@XmlRootElement
public class Categorie implements CycleRecoverable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="categorie", cascade=CascadeType.ALL)
	private Collection<Critere> criteres;
	@ManyToMany(mappedBy="categories")
	@JoinColumn(nullable=true)
	private Collection<Contrat> contrats;
	
	private String libelle;
	
	
	public Categorie(){	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	//@XmlID
	//@XmlJavaTypeAdapter(value=IntegerAdapter.class, type=String.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	//@XmlIDREF
	public Collection<Critere> getCriteres() {
		if(criteres == null){
			criteres = new ArrayList<Critere>();
		}
		return criteres;
	}

	public void setCriteres(Collection<Critere> criteres) {
		this.criteres = criteres;
	}
	//@XmlIDREF
	public Collection<Contrat> getContrats() {
		if(contrats == null){
			return new ArrayList<Contrat>();
		}
		return contrats;
	}

	public void setContrat(Collection<Contrat> contrats) {
		this.contrats = contrats;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		// TODO Auto-generated method stub
		//return null;
		Categorie c = new Categorie();
		c.setId(this.getId());
		return c;
	}
	
	
}
