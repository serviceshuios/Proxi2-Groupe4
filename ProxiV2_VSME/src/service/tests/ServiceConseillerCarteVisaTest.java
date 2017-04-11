package service.tests;

import org.junit.Assert;
import org.junit.Test;

import metier.CarteBancaire;
import metier.CarteVisa;
import metier.Compte;
import service.IConseiller;
import service.ServiceConseiller;
import service.Services;

public class ServiceConseillerCarteVisaTest {

	//Test d'Activation d'une carte visa. Association d'une carte à un compte
	@Test
	public void testActivationCarteVisa() {
		IConseiller sc = new Services(); // Initiation d'un service
		
		Compte cc1 = new Compte();	//Création et instanciation d'un compte
		cc1.setNumeroCompte(123);		
		cc1.setDateOuverture("01 janvier 2011");
		cc1.setSolde(10000);
		
		CarteBancaire cv1 = new CarteBancaire(789, "typeDeCarte"); // Création d'une carte visa
		
		cc1.setCarteBancaire(cv1); //Association d'une carte à un compte
		
		Compte cc2 = new Compte();	//Création d'un deuxième compte identique au premier
		cc2.setNumeroCompte(123);
		cc2.setDateOuverture("01 janvier 2011");
		cc2.setSolde(10000);
	
		sc.ActivationCarteVisa(cc2,cv1);		//Appel de la méthode Activation carte
		
		Assert.assertEquals(true, (cc2.getCarteBancaire()==cc1.getCarteBancaire(); //Vérifie si la carte associée au compte2 est identique au compte1
	}

	
	//Test de désactivation d'une carte
	@Test
	public void testDesactivationCarteVisa() {
		
		IConseiller sc = new Services();
		Compte ce1 = new Compte();	//Création d'un compte 1
		ce1.setNumeroCompte(123);
		ce1.setDateOuverture("01 janvier 2011");
		ce1.setSolde(10000);
		
		
		Compte ce2 = new Compte();	//Création d'un compte 2
		
		CarteBancaire cv = new CarteBancaire(753,"type de carte"); //Création d'une carte
		
		
		ce2.setNumeroCompte(123);	
		ce2.setDateOuverture("01 janvier 2011");
		ce2.setSolde(10000);
		ce2.setCarteBancaire(cv);	//Association de la carte avec le compte2
		
		sc.DesactivationCarteVisa(ce2,cv); //Appel de la méthode Desactivation carte: desassociation de la carte au compte2
					
		Assert.assertEquals(true, (ce1.getCarteBancaire() == ce2.getCarteBancaire()); // Vérifie si l
	}

}
