package service.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Agence;
import metier.Client;
import metier.Compte;
import metier.Conseiller;
import metier.Gerant;
import service.IGerant;
import service.Services;
import service.exception.AuditNegatifException;

/**
 * @author Marine
 *
 */
public class ServiceGerantAgenceTest {

	@Ignore
	@Test//Cas solde compte particulier <-5000
	public void testAuditerAgence() throws AuditNegatifException {
		
		
		IGerant sg = new Services(); //Création d'un service
		Gerant gerant1 = new Gerant();	//Création d'un gerant
		Conseiller conseiller1= new Conseiller();	//Création d'un conseiller
		
		Collection<Conseiller> col1 = gerant1.getConseillers();	//Association du conseiller au gérant
		col1.add(conseiller1);
		gerant1.setConseillers(col1);
		conseiller1.setGerant(gerant1);
		
		
		Client client1 = new Client();//Création d'un client
		client1.setTypeClient("particulier");
		Compte compte1 = new Compte();	//Création d'un compte
		compte1.setSolde(200);	
		client1.setComptes((Collection<Compte>) compte1); //Association du compte au client
		Collection<Client> colclient1= conseiller1.getClients();	//Association du client au conseiller1
		colclient1.add(client1);
		conseiller1.setClients(colclient1);
		client1.setConseiller(conseiller1);
		
		
		
		Client client2 = new Client();
		client2.setTypeClient("particulier");
		Compte compte2 = new Compte();
		compte2.setSolde(-60000); // Particularité du compte2 avec un solde indérieur à -5000
		client2.setComptes((Collection<Compte>) compte2);
		colclient1.add(client2);
		conseiller1.setClients(colclient1); //Association client2 au conseiller1
		client2.setConseiller(conseiller1);
		
		
		Conseiller conseiller2= new Conseiller(); //Création d'un conseiller2
		col1.add(conseiller2);
		gerant1.setConseillers(col1);
		conseiller2.setGerant(gerant1); //Association du conseiller2 au gérant
		
		
		Client client3 = new Client();	//Création d'un client3
		client3.setTypeClient("particulier");
		Compte compte3 = new Compte();
		compte3.setSolde(2300); 
		client3.setComptes((Collection<Compte>) compte3);
		Collection<Client> colclient2= conseiller2.getClients(); //Association du client2 au conseiller2
		colclient2.add(client3);
		conseiller2.setClients(colclient2);
		client3.setConseiller(conseiller2);
		
		
		Client client4 = new Client(); //Création d'un client4
		client1.setTypeClient("particulier");
		Compte compte4 = new Compte(); //Création d'un compte4
		compte4.setSolde(60000);
		client4.setComptes((Collection<Compte>) compte4);
		colclient2.add(client4);	//Association du client 4 au conseiller 2
		conseiller2.setClients(colclient2);
		client4.setConseiller(conseiller2);
		
		List<Client> tousLesClientsConseillers = new ArrayList();
		tousLesClientsConseillers.add(client1);
		tousLesClientsConseillers.add(client2);
		tousLesClientsConseillers.add(client3);
		tousLesClientsConseillers.add(client4);
		
		Agence a = new Agence();
		
		String reponse = sg.effectuerAudit(a); //Appel de la méthode AuditerAgence du gérant1
		
		Assert.assertEquals(true, reponse); //Renvoi si l'audit est positif ou négatif. réponse atendu négative à cause du compte2 du client2
	}
	
	// test identique au testAuditerAgence1 avec les comptes entreprise

	
}