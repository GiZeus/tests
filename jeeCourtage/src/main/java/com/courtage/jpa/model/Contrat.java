package com.courtage.jpa.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.courtage.data.adapter.IntegerAdapter;
import com.sun.xml.bind.CycleRecoverable;

@Entity
@Table(name="Contrat")
@XmlRootElement
public class Contrat implements CycleRecoverable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String libelle;
	private String description;
	@ManyToOne
	@JoinColumn(nullable=true)
	private Societe societe;
	@ManyToMany
	@JoinColumn(nullable=true)
	private Collection<Categorie> categories;
	
	public Contrat(){}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	//@XmlIDREF
	public Collection<Categorie> getCategories() {
		if(categories == null){
			categories = new ArrayList<Categorie>();
		}
		return categories;
	}

	public void setCategories(Collection<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		// TODO Auto-generated method stub
		//return null;
		Contrat c = new Contrat();
		c.setId(this.getId());
		return c;
	}
	
	
	
	
}
