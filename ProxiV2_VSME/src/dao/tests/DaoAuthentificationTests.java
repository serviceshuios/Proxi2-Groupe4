package dao.tests;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;

public class DaoAuthentificationTests {

	
	/**
	 * Test l'authentification d'un conseiller dans le cas où le conseiller rentre le bon login et le bon password
	 */
	@Test
	public void testauthentificationConseiller() {
		
		IDao idao = new Dao();
		String login = "bourne";
		String pwd = "bourne";
		
		int id = idao.authentificationConseiller(login, pwd);
		
		Assert.assertEquals(2, id);
	}

	
	/**
	 * Test l'authentification d'un conseiller dans le cas où le conseiller rentre le bon login et un mauvais password
	 */
	@Test
	public void testauthentificationConseiller2() {
		
		IDao idao = new Dao();
		String login = "bourne";
		String pwd = "bourn";
		
		int id = idao.authentificationConseiller(login, pwd);
		
		Assert.assertEquals(0, id);
	}
	
	/**
	 * Test l'authentification d'un conseiller dans le cas où le conseiller rentre un mauvais login 
	 */
	@Test
	public void testauthentificationConseiller3() {
		
		IDao idao = new Dao();
		String login = "bourn";
		String pwd = "bourne";
		
		int id = idao.authentificationConseiller(login, pwd);
		
		Assert.assertEquals(0, id);
	}
	
	
	
	
}
