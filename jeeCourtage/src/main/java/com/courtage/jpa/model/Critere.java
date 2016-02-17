package com.courtage.jpa.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.bind.CycleRecoverable;

@Entity
@Table(name="Critere")
@XmlRootElement
public class Critere implements CycleRecoverable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String libelle;
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	private Collection<Critere> children;
	@ManyToOne
	@JoinColumn(nullable=true)
	private Critere parent;
	@ManyToOne
	@JoinColumn(nullable=true)
	private Categorie categorie;
	
	public Critere(){}

	public Critere(String libelle) {
		this.libelle = libelle;
	}

	public Critere(String libelle, Collection<Critere> children) {
		this.libelle = libelle;
		this.children = children;
	}
	
	public Critere(String libelle, Collection<Critere> children, Critere parent) {
		this.libelle = libelle;
		this.children = children;
		this.parent = parent;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	//@XmlIDREF
	public Collection<Critere> getChildren() {
		if(children == null){
			children = new ArrayList<Critere>();
		}
		return children;
	}
	public void setChildren(Collection<Critere> children) {
		this.children = children;
	}
	public Critere getParent() {
		return parent;
	}
	public void setParent(Critere parent) {
		this.parent = parent;
	}

	//@XmlID
	//@XmlJavaTypeAdapter(value= IntegerAdapter.class, type=String.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/// Gestion des dependances circulaires pour JAX RS
	@Override
	public Object onCycleDetected(Context arg0) {
		Critere c = new Critere();
		c.setId(this.getId());
		return c;
	}
	
	
}
