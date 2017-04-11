package dao.tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.Compte;
import service.exception.DecouvertNonAutorise;
import service.exception.MontantNegatifException;
import service.exception.MontantSuperieurAuSoldeException;

public class DaoEffectuerVirementTests {

	@Test
	public void EffectuerUnVirementTest() throws SQLException {
		
		IDao idao = new Dao();
		Dao dao= new Dao();
		
		Compte c1 = new Compte();
		c1.setIdCompte(2); //le solde du compte est de 5 000 000 euros
		Compte c2 = new Compte();
		c2.setIdCompte(5); //le solde du compte est de 0 euros
		
		int montant = 300;
		double solde1 = dao.recuperationSolde(c1)-montant;
		double solde2 = dao.recuperationSolde(c2)+montant;
		try {
			idao.effectuerVirement(montant, c1, c2);
		} catch (MontantNegatifException | MontantSuperieurAuSoldeException | DecouvertNonAutorise e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double solde1bis = dao.recuperationSolde(c1);
		double solde2bis = dao.recuperationSolde(c2);
		
		Assert.assertEquals(true, (solde1bis==solde1 && solde2bis==solde2));
		
	}

}
