package dao.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.Dao;
import dao.IDao;

public class DaoCompterNombreClientTest {

	@Test
	public void test() {
		
		IDao idao = new Dao();
		idao.compterNombreClient(2);
	}

}
