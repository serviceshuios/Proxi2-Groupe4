package dao.tests;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.Adresse;
import metier.Client;
import metier.Conseiller;
import service.IConseiller;
import service.Services;
import service.exception.LeConseillerADeja10Clients;

public class DaoListerClientTests {

	
	// Test la méthode lister client dans le cas d'un conseiller qui a 10 clients
	@Test
	public void listerClienttest() {
		
		IDao idao = new Dao();
		
		Conseiller co = new Conseiller();
		int idcon = 1;//Conseiller dont l'idConseiller est de 1 : il a 10 clients en BDD
		
		Collection<Client> col1 = idao.listerClient(idcon); //Collection de clients particulier pour un conseiller
		
		Assert.assertEquals(10, col1.size()); //Regarde si la taille de la collection du conseiller 1 est bien égale à 10
		
	}
	
	// Test la méthode lister client par mot clé 
		@Test
		public void listerClienttestParMotCle() {
			
			IDao idao = new Dao();
			
			String motcle ="ma"; //mot clé ma : 3 personnes possèdent ce mot cle dans la bdd
			
			Collection<Client> col1 = idao.listerClient(motcle); //Collection de clients particulier pour un conseiller
			
			Assert.assertEquals(3, col1.size()); //Regarde si la taille de la collection du conseiller 1 est bien égale à 10
			
		}
	
	
}
