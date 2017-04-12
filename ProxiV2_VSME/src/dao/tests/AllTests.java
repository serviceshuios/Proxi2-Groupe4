package dao.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DaoAjouterClientTest.class, DaoAuthentificationTests.class, DaoCompterNombreClientTest.class,
		DaoEffectuerVirementTests.class, DaoListerClientTests.class, DaoListerCompteClientTests.class,
		DaoModifierClientTests.class })
public class AllTests {

}
