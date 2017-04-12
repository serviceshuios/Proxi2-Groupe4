package service.tests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Adresse;
import metier.Client;
import metier.Conseiller;
import service.IConseiller;
import service.Services;
import service.exception.LeConseillerADeja10Clients;

public class ServiceConseillerClientTest {

	
	// Test d'ajout d'un client 
	@Test
	public void testAjouterClient() throws LeConseillerADeja10Clients {
		
		IConseiller sc = new Services();	//Créer un service
		
		int idCon = 2;
		int nb = sc.compterNombreClient(idCon);
		Adresse a1 = new Adresse("rue A",69000,"Lyon");		// Création et instanciation d'un client
		Client client1 = new Client();
		client1.setNom("Toto");
		client1.setPrenom("Titi");
		client1.setEmail("hhhh");
		client1.setTelephone("0606060606");
		client1.setSonAdresse(a1);
		client1.setTypeClient("particulier"); //Création d'un client particulier (type 1)
		
		
		
			sc.ajouterClient(idCon, client1);
		//Appel à la méthode AjouterClient:Association client 2 au conseiller 2
		
		int nb2=sc.compterNombreClient(idCon);
		nb++;
		
		Assert.assertEquals(true, nb==nb2); //Compare la taille de la collection du conseiller 2 avec le conseiller 1 (Client Particulier) 
		
	}
	
	
	
	//cas ou le conseiller a deja 10 clients et veut en ajouter un 11ème
	@Test
	public void testAjouterClient3() {
		
IConseiller sc = new Services();	//Créer un service
		
		int idCon = 1; //le conseiller 1 de la base de donnée de test a deja 10 clients
		int nb = sc.compterNombreClient(idCon);
		Adresse a1 = new Adresse("rue A",69000,"Lyon");		// Création et instanciation d'un client
		Client client1 = new Client();
		client1.setNom("Toto");
		client1.setPrenom("Titi");
		client1.setEmail("hhhh");
		client1.setTelephone("0606060606");
		client1.setSonAdresse(a1);
		client1.setTypeClient("particulier"); //Création d'un client particulier (type 1)
		
		
		try {
			sc.ajouterClient(idCon, client1);
		} catch (LeConseillerADeja10Clients e) {
			// TODO Auto-generated catch block
			System.out.println("vous avez deja 10clients");
		} //Appel à la méthode AjouterClient:Association client 2 au conseiller 2
		
		int nb2=sc.compterNombreClient(idCon);
		
		
		Assert.assertEquals(true, nb==nb2); //copare le nombre de client du conseiller
		
	}
	
	@Test
	public void testModifierClient() throws SQLException {
		IConseiller sc = new Services(); //Création d'un service
		
		Client c1 = new Client();	//Création d'un client et Instanciation
		c1.setNom("test");
		c1.setPrenom("test");
		c1.setTelephone("0000000000");
		c1.setEmail("test.test@gmail.com");
		Adresse a1 = new Adresse("rue de la victoire",69230,"saint genix laval");
		c1.setSonAdresse(a1);
		c1.setIdClient(13);
		
		Adresse a2 = new Adresse("rue B",69001,"Villeurbanne"); //Création d'une nouvelle adresse
		String nom = "C";
		String prenom = "B";
		String email = "toto.titi@gmail.com"; //Création d'un nouveau mail
		
		
		sc.modifierClient(c1, nom, prenom,a2,email);//Appel la fonction ModifierClient
		
		Collection<Client> cl = new ArrayList<Client>();
		cl.addAll(sc.recuperationClient(13));
		Client c2 = new Client();
		Adresse a3= new Adresse();
		for(Client c: cl)
		{
			c2.setNom(c.getNom());
			c2.setPrenom(c.getPrenom());
			c2.setEmail(c.getEmail());
			a3.setAdresse(c.getSonAdresse().getAdresse());
			a3.setCodePostale(c.getSonAdresse().getCodePostale());
			a3.setVille(c.getSonAdresse().getVille());
			c2.setSonAdresse(a3);
		}

		
		Assert.assertEquals(true, (c2.getNom().equals(nom)&& c2.getPrenom().equals(prenom) && c2.getEmail().equals(email) && c2.getSonAdresse().getAdresse().equals(a2.getAdresse()) && c2.getSonAdresse().getCodePostale()==(a2.getCodePostale()) && c2.getSonAdresse().getVille().equals(a2.getVille()) )); //Vérifier de la modification de l'adresse et le téléphone
	}
	//
	// Supprimer un client: Désassociation d'un client à son conseiller
	@Ignore
	@Test
	public void testSupprimerClientParticulier() {
		
		IConseiller ge = new Services();	//Création d'un service
		Conseiller conseiller1=new Conseiller();	// Création d'un conseiller
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"particulier");	//Création et instanciation d'un client Particulier
		Collection<Client> col1 = conseiller1.getClients();	//Association du client au conseiller
		col1.add(c1);
		conseiller1.setClients(col1);
		c1.setConseiller(conseiller1);
		col1.remove(c1); // désassociation du client à son conseiller
		conseiller1.setClients(col1);
		
		
		
		Conseiller conseiller2= new Conseiller();	//Création d'un conseiller 2 identique au conseiller 1
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"particulier"); //Création d'un client 2 identique au client 1
		Collection<Client> col2 = conseiller2.getClients(); //Association du client au conseiller
		col2.add(c2);
		conseiller2.setClients(col2);
		c2.setConseiller(conseiller2);
		ge.supprimerClient(c2,conseiller2.getIdConseiller());	//Utilisation de la méthode SupprimerClient
		
		//Test d'égalité de la tailler de la collection des client particuliers en conseiller 1 et 2
		Assert.assertEquals(true, (conseiller1.getClients().size()==conseiller2.getClients().size()));
		

	}
	
	// Identique au test testSupprimerClientParticulier avec un client entreprise
	@Ignore
	@Test
	public void testSupprimerClientEntreprise() {
		
		IConseiller ge = new Services();
		Conseiller conseiller1=new Conseiller();
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Client c1 = new Client("Toto","Titi","0606060606",001,a1,"entreprise");
		Collection<Client> col1 = conseiller1.getClients();
		col1.add(c1);
		conseiller1.setClients(col1);
		c1.setConseiller(conseiller1);
		col1.remove(c1);
		conseiller1.setClients(col1);
		
		
		
		Conseiller conseiller2= new Conseiller();
		Client c2 = new Client("Toto","Titi","0606060606",001,a1,"entreprise");
		Collection<Client> col2 = conseiller2.getClients();
		col2.add(c2);
		conseiller2.setClients(col2);
		c2.setConseiller(conseiller2);
		ge.supprimerClient(c2,conseiller2.getIdConseiller());
		
		
		Assert.assertEquals(true, (conseiller1.getClients().size()==conseiller2.getClients().size()));
		

	}
	


}
