package service.tests;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Adresse;
import metier.Conseiller;
import metier.Gerant;
import service.IGerant;
import service.Services;

public class ServiceGerantConseillerTest {

	// Test pour ajouter un conseiller à gérant
	@Ignore
	@Test
	public void testAjouterConseiller() {

		IGerant ge = new Services();
		
		Gerant g1 = new Gerant();
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Conseiller c1 = new Conseiller();
		c1.setNom("toto");
		c1.setPrenom("titi");
		c1.setTelephone("0606060606");
		c1.setSonAdresse(a1);
		
		
		Collection<Conseiller> col1 = g1.getConseillers();
		col1.add(c1);
		g1.setConseillers(col1);
		c1.setGerant(g1);
		
		Gerant g2 = new Gerant();
		
		Conseiller c2 = new Conseiller();
		c2.setNom("toto");
		c2.setPrenom("titi");
		c2.setTelephone("0606060606");
		c2.setSonAdresse(a1);
		
		ge.ajouterConseiller(g2.getIdGerant(),c2);
		System.out.println();
		System.out.println(g2.getConseillers());
		
		Assert.assertEquals(true, (g1.getConseillers().size()==g2.getConseillers().size()));
		
	}
	
	// Test de la modification de l'adresse et du téléphone du conseiller
	@Ignore
	@Test
	public void testModifierConseiller() {
		IGerant ge = new Services();
		Conseiller c1 = new Conseiller();
		c1.setNom("Toto");
		c1.setPrenom("Titi");
		c1.setTelephone("0606060606");
		c1.setId(001);
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		c1.setSonAdresse(a1);
		Adresse a2 = new Adresse("rue B",69001,"Villeurbanne");
		String telephone2 = "0707070707";
		
		Conseiller c2 = new Conseiller();
		c2.setNom("Toto");
		c2.setPrenom("Titi");
		c2.setTelephone("0707070707");
		c2.setId(001);
		c2.setSonAdresse(a2);
		ge.modifierConseiller(c1,a2,telephone2);
		Assert.assertEquals(true, (c1.getSonAdresse()==c2.getSonAdresse() && c1.getTelephone() == c2.getTelephone()));
	}
	
	
	// Test de desassociation d'un conseiller à un gérant
	@Test
	public void testSupprimerConseiller() {
		IGerant ge = new Services();
		Gerant gerant1=new Gerant();
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Conseiller c1 = new Conseiller();
		c1.setNom("toto");
		c1.setPrenom("titi");
		c1.setTelephone("0606060606");
		c1.setSonAdresse(a1);
		Collection<Conseiller> col1 = gerant1.getConseillers();
		col1.add(c1);
		gerant1.setConseillers(col1);
		c1.setGerant(gerant1);
		col1.remove(c1);
		gerant1.setConseillers(col1);
		
		Gerant gerant2= new Gerant();
		Conseiller c2 = new Conseiller();
		c2.setNom("toto");
		c2.setPrenom("titi");
		c2.setTelephone("0606060606");
		c2.setSonAdresse(a1);
		
		Collection<Conseiller> col2 = gerant2.getConseillers();
		col2.add(c2);
		gerant2.setConseillers(col2);
		c2.setGerant(gerant2);
		ge.supprimerConseiller(c2,gerant2.getIdGerant());
		
		
		Assert.assertEquals(true, (col1.size()==gerant2.getConseillers().size()));
		
	}
	
	
	@Ignore
	@Test
	public void testAfficherConseiller() {
		
	}

}
