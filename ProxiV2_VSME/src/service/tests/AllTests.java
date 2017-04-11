package service.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ServiceConseillerCarteVisaTest.class, ServiceConseillerClientTest.class,
		ServiceConseillerCompteTest.class, ServiceConseillerGestionPatrimoineTest.class,
		ServiceConseillerSimulationTest.class, ServiceGerantAgenceTest.class, ServiceGerantConseillerTest.class,
		ServieConseillerVirementTest.class })
public class AllTests {

}
