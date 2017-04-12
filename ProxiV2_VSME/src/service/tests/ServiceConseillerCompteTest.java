package service.tests;

import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Adresse;
import metier.Client;
import metier.Compte;
import service.IConseiller;
import service.Services;
import service.exception.AbsenceDeCompteCourantException;
import service.exception.AbsenceDeCompteEpargneException;
import service.exception.CompteCourantExistantException;
import service.exception.CompteEpargneExistantException;

public class ServiceConseillerCompteTest {

		//Test d'ajout d'un compte à un client
	
	@Test
	public void testAjouterCompteEpargneClient() {
		IConseiller sc = new Services(); //Création d'un service
		
		//Création d'un compte et instanciation
		Compte ce1 = new Compte();
		ce1.setNumeroCompte(123);
		ce1.setSolde(10000);
		ce1.setTypeCompte("epargne");
		
		
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création d'un client avec instanciation
		
		c1.setComptes((Collection<Compte>) ce1);	//Association d'un compte1 au client1
					
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); // Création d'un client 2 identique au client 1
		try {
			sc.ajouterCompteClient(c2,ce1);
		} catch (CompteEpargneExistantException | CompteCourantExistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//Appel de la méthode ajouterCompteClient pour associer le compte1 au client2
				
		//Vérifier que le client1 a le même compte que client 2
		Assert.assertEquals(true, (c1.getComptes().size()==c2.getComptes().size()));
	}
	
	//Test Ajouter Compte Epargne dans le cas où le client à déjà un compte Epargne
	
	@Test
	public void testAjouterCompteEpargneClient2() {
		IConseiller sc = new Services(); //Création d'un service
		
		//Création d'un compte et instanciation
				Compte ce1 = new Compte();
				ce1.setNumeroCompte(123);
				ce1.setSolde(10000);
				ce1.setTypeCompte("epargne");
		
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier");//Création et instanciation d'un client
		c1.setComptes((Collection<Compte>) ce1);	//Association du compte au client 
		
		//Création d'un compte 2 différent du compte1
		Compte ce2 = new Compte();
		ce2.setNumeroCompte(456);
		ce2.setSolde(1000);
		ce2.setTypeCompte("epargne");
		
		
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); // Création d'un client2 identique au client 1
		c2.setComptes((Collection<Compte>) ce1); //Association du Compte1 au client2
		
		try {
			sc.ajouterCompteClient(c2, ce2);
		} catch (CompteEpargneExistantException | CompteCourantExistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Appel de la méthode AjouterCompteClient pour essayer d'ajouter le compte 2 au client2
				
			
		//Vérifie que le compte2 du client2 n'a pas été ajouté
		Assert.assertEquals(true, (c1.getComptes().size()==c2.getComptes().size()));
	}
	
	
	
	// Test identique au testAjouterCompteEpargneClient avec un Compte Courant
	
	@Test
	public void testAjouterCompteCourantClient() {
		IConseiller sc = new Services();
		
		//Création d'un compte1
		Compte ce1 = new Compte();
		ce1.setNumeroCompte(123);
		ce1.setSolde(10000);
		ce1.setTypeCompte("epargne"); 
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création d'un client
		
		c1.setComptes((Collection<Compte>) ce1); //Assigner le compte au client
					
		
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création d'un client2
		try {
			sc.ajouterCompteClient(c2,ce1);
		} catch (CompteEpargneExistantException | CompteCourantExistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Assignation du compte1 au client2 avec la fonction AjouterClient
		
		Assert.assertEquals(true, (c1.getComptes().size()==c2.getComptes().size()));
	}
	
	//Test Ajouter Compte Courant dans le cas où le client à déjà un compte Courant
	@Test
	public void testAjouterCompteCourantClient2() {
		IConseiller sc = new Services();
		
		 // Création d'un Compte 1
		Compte ce1 = new Compte();
		ce1.setNumeroCompte(123);
		ce1.setSolde(10000);
		ce1.setTypeCompte("courant"); 
		
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création d'un client 1
		
		c1.setComptes((Collection<Compte>) ce1); //Assignation du compte1 au client1
		
		//Création d'un compte 2
		Compte ce2 = new Compte();
		ce2.setNumeroCompte(456);
		ce2.setSolde(1000);
		ce2.setTypeCompte("courant");
		
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); // Création d'un client2 identique au client1
		c2.setComptes((Collection<Compte>) ce1); //Assignation du compte1 au client 2
		
		try {
			sc.ajouterCompteClient(c2, ce2);
		} catch (CompteEpargneExistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CompteCourantExistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Appel de AjouterCompteClient pour assigner le compte2 au client2
		
		Assert.assertEquals(true, (c1.getComptes().size()==c2.getComptes().size())); //Test pour verifier que la modification n'a pas été possible
	
	}
	
	//Test de la suppression de l'association d'un compte Epargne à un client	
	@Test
	public void testSupprimerCompteEpargneClient() {
		
		IConseiller sc = new Services(); //Création d'un service
		
		
			//Création compte
		Compte ce1 = new Compte();
		ce1.setNumeroCompte(123);
		ce1.setSolde(10000);
		ce1.setTypeCompte("epargne"); 
		
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création client
		
		
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création Client2
		c2.setComptes((Collection<Compte>) ce1);
		try {
			sc.supprimerCompteClient(ce1,c2);
		} catch (AbsenceDeCompteEpargneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AbsenceDeCompteCourantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, ((c1.getComptes().size()==c2.getComptes().size())));
	}
	
	//Test de la suppression de l'association d'un compte courant à un client	
	@Test
	public void testSupprimerCompteCourantClient() {
		
		IConseiller sc = new Services(); //Création d'un service
		
		
		//Création compte
		Compte ce1 = new Compte();
		ce1.setNumeroCompte(123);
		ce1.setSolde(10000);
		ce1.setTypeCompte("epargne"); 
		
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création client
		
		
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création Client2
		c2.setComptes((Collection<Compte>) ce1); //Assignation du compte courant au client
		try {
			sc.supprimerCompteClient(ce1,c2);
		} catch (AbsenceDeCompteEpargneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AbsenceDeCompteCourantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Appel de la fonction pour supprimer le compte
		
		Assert.assertEquals(true, (c1.getComptes().size()==c2.getComptes().size()));
	}
	
	
	
	
	@Ignore
	@Test
	public void testlisterCompteClient() {
		fail("Not yet implemented");
	}

}
