package service.tests;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Adresse;
import metier.Conseiller;
import metier.Gerant;
import service.IGerant;
import service.ServiceGerant;

public class ServiceGerantConseillerTest {

	// Test pour ajouter un conseiller à gérant
	@Test
	public void testAjouterConseiller() {

		IGerant ge = new ServiceGerant();
		
		Gerant g1 = new Gerant();
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Conseiller c1 = new Conseiller("Toto","Titi",0606060606,001,a1);
		
		Collection<Conseiller> col1 = g1.getMesConseillers();
		col1.add(c1);
		g1.setMesConseillers(col1);
		c1.setMonGerant(g1);
		
		Gerant g2 = new Gerant();
		Conseiller c2 = new Conseiller("Toto","Titi",0606060606,001,a1);
		ge.AjouterConseiller(g2,c2);
		System.out.println();
		System.out.println(g2.getMesConseillers());
		
		Assert.assertEquals(true, (g1.getMesConseillers().size()==g2.getMesConseillers().size()));
		
	}
	
	// Test de la modification de l'adresse et du téléphone du conseiller
	@Test
	public void testModifierConseiller() {
		IGerant ge = new ServiceGerant();
		Conseiller c1 = new Conseiller();
		c1.setNom("Toto");
		c1.setPrenom("Titi");
		c1.setTelephone(0606060606);
		c1.setId(001);
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		c1.setSonAdresse(a1);
		Adresse a2 = new Adresse("rue B",69001,"Villeurbanne");
		int telephone2 = 0707070707;
		Conseiller c2 = new Conseiller("Toto","Titi",telephone2,001,a2);
		ge.ModifierConseiller(c1,a2,telephone2);
		Assert.assertEquals(true, (c1.getSonAdresse()==c2.getSonAdresse() && c1.getTelephone() == c2.getTelephone()));
	}
	
	
	// Test de desassociation d'un conseiller à un gérant
	@Test
	public void testSupprimerConseiller() {
		IGerant ge = new ServiceGerant();
		Gerant gerant1=new Gerant();
		Adresse a1 = new Adresse("rue A",69000,"Lyon");
		Conseiller c1 = new Conseiller("Toto","Titi",0606060606,001,a1);
		Collection<Conseiller> col1 = gerant1.getMesConseillers();
		col1.add(c1);
		gerant1.setMesConseillers(col1);
		c1.setMonGerant(gerant1);
		col1.remove(c1);
		gerant1.setMesConseillers(col1);
		
		Gerant gerant2= new Gerant();
		Conseiller c2 = new Conseiller("Toto","Titi",0606060606,001,a1);
		Collection<Conseiller> col2 = gerant2.getMesConseillers();
		col2.add(c2);
		gerant2.setMesConseillers(col2);
		c2.setMonGerant(gerant2);
		ge.SupprimerConseiller(c2,gerant2);
		
		
		Assert.assertEquals(true, (col1.size()==gerant2.getMesConseillers().size()));
		
	}
	
	
	@Ignore
	@Test
	public void testAfficherConseiller() {
		
	}

}
