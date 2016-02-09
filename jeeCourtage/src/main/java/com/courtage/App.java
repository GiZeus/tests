package com.courtage;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.courtage.jpa.model.*;

/**
 * Hello world!
 *
 */
public class App 
{
	private static EntityManager em;
	
	public static void main( String[] args )
    {
		initEntityManager();
    	testInsert2();
    	displayInsert();
		displayCriteres();
		displayInsertFromCritere();
    	
    }
	
	public static void initEntityManager(){
		if(em == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
	    	em = emf.createEntityManager();
		}
	}
    
	public static void displayCriteres(){
		System.out.println("");
		System.out.println("Criteres : ");
		Query q = em.createQuery("Select c from Critere c");
		@SuppressWarnings("unchecked")
		Collection<Critere> criteres = (Collection<Critere>)q.getResultList();
		for(Critere c : criteres){
			System.out.println("-" + c.getLibelle());
			if(c.getParent() != null){
				System.out.println("\t has for parent : +"+c.getParent().getLibelle());
			}
		}
	}

    public static void displayInsert(){
    	
    	System.out.println("Hello World");
    	Query q = em.createQuery("Select s From Societe s");
    	@SuppressWarnings("unchecked")
		Collection<Societe> societes = (Collection<Societe>) q.getResultList();
    	for(Societe s : societes){
    		System.out.println("- "+s.getNom());
    		Collection<Contrat> contrats = s.getContrats();
    		for(Contrat contrat : contrats){
    			System.out.println("\t- "+contrat.getLibelle());
    			Collection<Categorie> categories = contrat.getCategories();
    			for(Categorie cat : categories){
    				System.out.println("\t\t- "+cat.getLibelle());
    				Collection<Critere> criteres = cat.getCriteres();
    				for(Critere c : criteres){
						displayCritere(c, 3);
    				}
    			}
    		}
    	}
    }

	public static void displayCritere(Critere c, int level){
		String message = "";
		for(int i = 0; i < level; i++){
			message += "\t";
		}
		message += "- "+c.getLibelle() + " - "+c.getChildren().size();
		System.out.println(message);
		for(Critere fils : c.getChildren()){
			displayCritere(fils, level + 1);
		}
	}

	@SuppressWarnings("unchecked")
	public static void displayInsertFromCritere(){
		System.out.println("");		
		System.out.println("Display from end");
		Query q = em.createQuery("Select c From Critere c");
		Collection<Critere> criteres = q.getResultList();
		for(Critere c : criteres){
			if(c.getLibelle().equals("Critere Parent")){
				Categorie cat = c.getCategorie();
				if(cat != null){
					System.out.println("get a categorie");
					System.out.println("Categorie -> "+cat.getLibelle());
					Collection<Contrat> contrats = cat.getContrats();
					for(Contrat contrat : contrats){
						System.out.println("Contrat -> "+contrat.getLibelle());
						Societe s = contrat.getSociete();
						System.out.println("Societe -> "+s.getNom());
					}
				}else{
					System.out.println("No Category found for critere "+c.getId());
				}
			}
		}
		
	}	
	
	
	public static void testInsert2(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
    	EntityManager em = emf.createEntityManager();
    	System.out.println("");
    	System.out.println( "Hello World!" );
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        
        /*
        EntityTest ent1 = new EntityTest();
        ent1.setLibelle("Entite 1");
        em.persist(ent1);
        */
        
		Critere child2 = new Critere("Critere Fils 2");
		//child2.setParent(c1);
		System.out.println("Critere fils 2 OK");

		Critere child3 = new Critere("Critere Petit Fils du 1");
		//child3.setParent(child);
		//em.persist(child3);
		System.out.println("Critere petit fils du 1 OK");

		Critere child1 = new Critere("Critere Fils 1");
		child1.getChildren().add(child3);
		//child.setParent(c1);
		System.out.println("Critere fils 1 OK");

		Critere c1 = new Critere("Critere Parent");
		child3.setParent(child2);
		child1.setParent(c1);
		child2.setParent(c1);
		c1.getChildren().add(child1);
		c1.getChildren().add(child2);
        em.persist(c1);
        System.out.println("Critere Parent OK");
        
        Categorie categorie = new Categorie("Assurance vie");
        categorie.getCriteres().add(c1);
		c1.setCategorie(categorie);
        em.persist(categorie);
        System.out.println("Categorie OK");

		Contrat contrat = new Contrat();
        contrat.setLibelle("Premier contrat");
        contrat.setDescription("Premiere description");
        contrat.getCategories().add(categorie);
        categorie.getContrats().add(contrat);
        //contrat.setSociete(s);
        em.persist(contrat);
        System.out.println("Contrat OK");
		
        Societe s = new Societe("Cofinoga");
        s.getContrats().add(contrat);
        contrat.setSociete(s);
        em.persist(s);
        System.out.println("Societe OK");
		
		//em.persist(child1);
		//em.persist(child2);
		
		
		
		
        
        
        
        System.out.println("");
        
        
        transac.commit();
        
        em.close();
        emf.close();
        System.out.println("End with success");
    }
    
    public static void testInsert(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");
    	EntityManager em = emf.createEntityManager();
    	System.out.println("");
    	System.out.println( "Hello World!" );
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        
        /*
        EntityTest ent1 = new EntityTest();
        ent1.setLibelle("Entite 1");
        em.persist(ent1);
        */
        
		Critere child2 = new Critere("Critere Fils 2");
		//child2.setParent(c1);
		System.out.println("Critere fils 2 OK");

		Critere child3 = new Critere("Critere Petit Fils du 1");
		//child3.setParent(child);
		em.persist(child3);
		System.out.println("Critere petit fils du 1 OK");

		Critere child1 = new Critere("Critere Fils 1");
		child1.getChildren().add(child3);
		//child.setParent(c1);
		System.out.println("Critere fils 1 OK");

		em.persist(child1);
		em.persist(child2);
		
		Critere c1 = new Critere("Critere Parent");
		c1.getChildren().add(child1);
		c1.getChildren().add(child2);
        em.persist(c1);
        System.out.println("Critere Parent OK");

        Categorie categorie = new Categorie("Assurance vie");
        categorie.getCriteres().add(c1);
        em.persist(categorie);
        System.out.println("Categorie OK");

        Contrat contrat = new Contrat();
        contrat.setLibelle("Premier contrat");
        contrat.setDescription("Premiere description");
        contrat.getCategories().add(categorie);
        em.persist(contrat);
        System.out.println("Contrat OK");
        
        
        
        System.out.println("");
        
        Societe s = new Societe("Cofinoga");
        s.getContrats().add(contrat);
        em.persist(s);
        System.out.println("Contrat OK");
        
        transac.commit();
        
        em.close();
        emf.close();
        System.out.println("End with success");
    }
}