package dao.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DaoAuthentificationTests.class, DaoEffectuerVirementTests.class, DaoListerClientTests.class,
		DaoListerCompteClientTests.class, DaoModifierClientTests.class })
public class AllTests {

}
