package com.courtage.jpa.model;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.courtage.data.adapter.IntegerAdapter;
import com.sun.xml.bind.CycleRecoverable;


@Entity
@Table(name="Societe")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Societe implements CycleRecoverable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(mappedBy="societe", cascade=CascadeType.PERSIST)
	private Collection<Contrat> contrats;

	
	public Societe(){}
	
	public Societe(String nom){
		this.nom = nom;
	}
	
	//@XmlID
	//@XmlJavaTypeAdapter(value=IntegerAdapter.class, type=String.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	//@XmlIDREF
	public Collection<Contrat> getContrats() {
		if(contrats == null){
			contrats = new ArrayList<Contrat>();
		}
		return contrats;
	}

	public void setContrats(Collection<Contrat> contrats) {
		this.contrats = contrats;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		// TODO Auto-generated method stub
		//return null;
		Societe s = new Societe();
		s.setId(this.getId());
		return s;
	}
	
	public String toString(){
		return ""+nom+ " "+id+ " " ;
	}
	
	
	
}
