package service.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Client;
import metier.Compte;
import metier.Conseiller;
import metier.Gerant;
import service.IGerant;
import service.ServiceGerant;
import service.exception.AuditNegatifException;

/**
 * @author Marine
 *
 */
public class ServiceGerantAgenceTest {

	@Ignore
	@Test//Cas solde compte particulier <-5000
	public void testAuditerAgence() throws AuditNegatifException {
		
		
		IGerant sg = new ServiceGerant(); //Création d'un service
		Gerant gerant1 = new Gerant();	//Création d'un gerant
		Conseiller conseiller1= new Conseiller();	//Création d'un conseiller
		
		Collection<Conseiller> col1 = gerant1.getMesConseillers();	//Association du conseiller au gérant
		col1.add(conseiller1);
		gerant1.setMesConseillers(col1);
		conseiller1.setMonGerant(gerant1);
		
		
		Client client1 = new Client();//Création d'un client
		client1.setTypeClient(1);
		Compte compte1 = new Compte();	//Création d'un compte
		compte1.setSolde(200);	
		client1.setMonCompteCourant(compte1); //Association du compte au client
		Collection<Client> colclient1= conseiller1.getMesClientsParticuliers();	//Association du client au conseiller1
		colclient1.add(client1);
		conseiller1.setMesClientsParticuliers(colclient1);
		client1.setMonConseillerClient(conseiller1);
		
		
		
		Client client2 = new Client();
		client2.setTypeClient(1);
		Compte compte2 = new Compte();
		compte2.setSolde(-60000); // Particularité du compte2 avec un solde indérieur à -5000
		client2.setMonCompteCourant(compte2);
		colclient1.add(client2);
		conseiller1.setMesClientsParticuliers(colclient1); //Association client2 au conseiller1
		client2.setMonConseillerClient(conseiller1);
		
		
		Conseiller conseiller2= new Conseiller(); //Création d'un conseiller2
		col1.add(conseiller2);
		gerant1.setMesConseillers(col1);
		conseiller2.setMonGerant(gerant1); //Association du conseiller2 au gérant
		
		
		Client client3 = new Client();	//Création d'un client3
		client3.setTypeClient(1);
		Compte compte3 = new Compte();
		compte3.setSolde(2300); 
		client3.setMonCompteCourant(compte3);
		Collection<Client> colclient2= conseiller2.getMesClientsParticuliers(); //Association du client2 au conseiller2
		colclient2.add(client3);
		conseiller2.setMesClientsParticuliers(colclient2);
		client3.setMonConseillerClient(conseiller2);
		
		
		Client client4 = new Client(); //Création d'un client4
		client1.setTypeClient(1);
		Compte compte4 = new Compte(); //Création d'un compte4
		compte4.setSolde(60000);
		client4.setMonCompteCourant(compte4);
		colclient2.add(client4);	//Association du client 4 au conseiller 2
		conseiller2.setMesClientsParticuliers(colclient2);
		client4.setMonConseillerClient(conseiller2);
		
		List<Client> tousLesClientsConseillers = new ArrayList();
		tousLesClientsConseillers.add(client1);
		tousLesClientsConseillers.add(client2);
		tousLesClientsConseillers.add(client3);
		tousLesClientsConseillers.add(client4);
		
		
		boolean reponse = sg.AuditerAgence(tousLesClientsConseillers); //Appel de la méthode AuditerAgence du gérant1
		
		Assert.assertEquals(true, (false==reponse)); //Renvoi si l'audit est positif ou négatif. réponse atendu négative à cause du compte2 du client2
	}
	
	// test identique au testAuditerAgence1 avec les comptes entreprise

	
	@Test//Cas solde compte entreprise <-50000

	public void testAuditerAgence2() throws AuditNegatifException {
		IGerant sg = new ServiceGerant();
		Gerant gerant1 = new Gerant();
		Conseiller conseiller1= new Conseiller();
		Collection<Conseiller> col1 = gerant1.getMesConseillers();
		col1.add(conseiller1);
		gerant1.setMesConseillers(col1);
		conseiller1.setMonGerant(gerant1);
		
		
		Client client1 = new Client();
		client1.setTypeClient(2);
		Compte compte1 = new Compte();
		compte1.setSolde(200);
		client1.setMonCompteCourant(compte1);
		Collection<Client> colclient1= conseiller1.getMesClientsEntreprises();
		colclient1.add(client1);
		conseiller1.setMesClientsParticuliers(colclient1);
		client1.setMonConseillerClient(conseiller1);
		
		
		Client client2 = new Client();
		client2.setTypeClient(2);
		Compte compte2 = new Compte();
		compte2.setSolde(-60000);
		client2.setMonCompteCourant(compte2);
		colclient1.add(client2);
		conseiller1.setMesClientsEntreprises(colclient1);
		client2.setMonConseillerClient(conseiller1);
		
		
		Conseiller conseiller2= new Conseiller();
		col1.add(conseiller2);
		gerant1.setMesConseillers(col1);
		conseiller2.setMonGerant(gerant1);
		
		
		Client client3 = new Client();
		client3.setTypeClient(2);
		Compte compte3 = new Compte();
		compte3.setSolde(2300);
		client3.setMonCompteCourant(compte3);
		Collection<Client> colclient2= conseiller2.getMesClientsEntreprises();
		colclient2.add(client3);
		conseiller2.setMesClientsEntreprises(colclient2);
		client3.setMonConseillerClient(conseiller2);
		
		
		Client client4 = new Client();
		Compte compte4 = new Compte();
		compte4.setSolde(60000);
		client4.setMonCompteCourant(compte4);
		colclient2.add(client4);
		conseiller2.setMesClientsEntreprises(colclient2);
		client4.setMonConseillerClient(conseiller2);
		
		Collection <Client> tousLesClientsConseillers = conseiller1.getTousMesClients();
		tousLesClientsConseillers.add(client1);
		tousLesClientsConseillers.add(client2);
		tousLesClientsConseillers.add(client3);
		tousLesClientsConseillers.add(client4);
		
		boolean reponse = sg.AuditerAgence(tousLesClientsConseillers);
		
		Assert.assertEquals(true, (false==reponse));	
	}
	
	//test de l'Audit réussi
	@Ignore
	@Test//Cas solde compte entreprise >-50000 et compte solde particulier >-5000
	public void testAuditerAgence3() throws AuditNegatifException {
		
		IGerant  sg = new ServiceGerant();
		Gerant gerant1 = new Gerant();
		Conseiller conseiller1= new Conseiller();
		Collection<Conseiller> col1 = gerant1.getMesConseillers();
		col1.add(conseiller1);
		gerant1.setMesConseillers(col1);
		conseiller1.setMonGerant(gerant1);
		
		
		Client client1 = new Client();
		client1.setTypeClient(2);
		Compte compte1 = new Compte();
		compte1.setSolde(200);
		client1.setMonCompteCourant(compte1);
		Collection<Client> colclient1= conseiller1.getMesClientsEntreprises();
		colclient1.add(client1);
		conseiller1.setMesClientsEntreprises(colclient1);
		client1.setMonConseillerClient(conseiller1);
		
		
		Client client2 = new Client();
		client2.setTypeClient(2);
		Compte compte2 = new Compte();
		compte2.setSolde(60000);
		client2.setMonCompteCourant(compte2);
		colclient1.add(client2);
		conseiller1.setMesClientsEntreprises(colclient1);
		client2.setMonConseillerClient(conseiller1);
		
		
		Conseiller conseiller2= new Conseiller();
		col1.add(conseiller2);
		gerant1.setMesConseillers(col1);
		conseiller2.setMonGerant(gerant1);
		
		
		Client client3 = new Client();
		client3.setTypeClient(2);
		Compte compte3 = new Compte();
		compte3.setSolde(2300);
		client3.setMonCompteCourant(compte3);
		Collection<Client> colclient2= conseiller2.getMesClientsEntreprises();
		colclient2.add(client3);
		conseiller2.setMesClientsEntreprises(colclient2);
		client3.setMonConseillerClient(conseiller2);
		
		
		Client client4 = new Client();
		client4.setTypeClient(2);
		Compte compte4 = new Compte();
		compte4.setSolde(60000);
		client4.setMonCompteCourant(compte4);
		colclient2.add(client4);
		conseiller2.setMesClientsEntreprises(colclient2);
		client4.setMonConseillerClient(conseiller2);
		
		Collection <Client> tousLesClientsConseillers = conseiller1.getTousMesClients();
		tousLesClientsConseillers.add(client1);
		tousLesClientsConseillers.add(client2);
		tousLesClientsConseillers.add(client3);
		tousLesClientsConseillers.add(client4);
		
		boolean reponse = sg.AuditerAgence(tousLesClientsConseillers);
		
		Assert.assertEquals(true, (true==reponse)); //Vérification de tous les comptes de l'agence dans le cas où ils sont positif
		
		
		
		
		
	}}


