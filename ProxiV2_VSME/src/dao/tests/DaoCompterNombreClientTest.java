package dao.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;

public class DaoCompterNombreClientTest {

	
	/**
	 * Test si la methoe pour compter le nombre de client d'un conseiller fonctionne avec la BDD implementee initiale
	 */
	@Test
	public void test() {
		
		IDao idao = new Dao();
		int x = idao.compterNombreClient(2);// le conseiller 2 a 5 clients 
		Assert.assertEquals(5, x);
		
	}

}
