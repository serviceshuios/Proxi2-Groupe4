package dao.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.Adresse;
import metier.Client;
import service.exception.LeConseillerADeja10Clients;

public class DaoAjouterClientTest {

	@Test
	public void test() throws LeConseillerADeja10Clients {
		IDao idao = new Dao();
		Client c= new Client();
		String nom = "a";
		String prenom = "b";
		Adresse a = new Adresse("rue a", 69100, "villeurbanne");
		String email ="a.b@gmail.com";
		String telephone = "0202020202";
		String typeClient = "particulier";
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setSonAdresse(a);
		c.setTelephone(telephone);
		c.setEmail(email);
		c.setTypeClient(typeClient);

		idao.ajouterClient(2,c);
	}

}
