package service.tests;

import org.junit.Assert;
import org.junit.Test;

import service.IConseiller;
import service.ServiceConseiller;
import service.exception.MontantNegatifException;

public class ServiceConseillerSimulationTest {

	// Test d'une simulation d'un crédit
	@Test
	public void testEffectuerSimulationCredit() throws MontantNegatifException {
		IConseiller sc = new ServiceConseiller(); //Création d'un service
		double montant=200000;	//Instanciation du montant du crédit
		int taux=3; //Instanciation du taux du crédit
		int duree=24;//durée du crédit en mois
		
		double montantARembourser = 200000*(1+(3/100)); // calcul du montant à rembourser en totalité
		double montantARembourserParMois = montantARembourser/duree; //Calcul des mensualités à rembourser
		
		double m1=sc.EffectuerSimulationCredit(montant, taux, duree); //Appel à la méthode de simulation 
		Assert.assertEquals(true, (m1==montantARembourserParMois)); //Vérifier la méthode avec les calculs dans le test
		
	}
	
	@Test//cas montant negatif
	public void testEffectuerSimulationCredit2() throws MontantNegatifException {
		IConseiller  sc = new ServiceConseiller();
		double montant=-200;
		int taux=3;
		int duree=24;//mois
		
		double montantARembourser = 0;
		double montantARembourserParMois = 0;
		
		double m1=sc.EffectuerSimulationCredit(montant, taux, duree);
		
		Assert.assertEquals(true, (m1==montantARembourserParMois)); //Vérifier si la méthode renvoi une valeur null identique au valeur du test
		
	}

}
