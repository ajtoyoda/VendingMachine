package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.assignment1.*;
import org.lsmr.vendingmachine.simulator.*;

//To run right click and select run as->JUnit test. Select eclipse plug in and then it should show a green bar

//Sample (albeit functional) file to demonstrate simple testing. Note that I am only testing given the correct inputs the function does what
//it is intended while using as few other pieces as possible
public class ExactChangeLightControllerTest {

	//This will happen before each and every test case
	@Before
	public void setup(){
		indicatorLight = new IndicatorLightSimulator("");
		exactLight = new ExactChangeLightController(indicatorLight);
	}
	//This will happen after each and every test case
	@After
	public void tearDown() {

	}
	//@Tests function names must start with test
	@Test
	public void testTurnsLightOn() {
		exactLight.notExactChange(new DispenserController(null,null,null,null));
		assertTrue(indicatorLight.isActive());
	}
	/*@Test
	public void testNextPurchaseTurnsLightOff(){
		indicatorLight.activate();
		exactLight.purchaseMade(null);
		assertFalse(indicatorLight.isActive());
	}
	@Test
	public void testSelectingPopTurnsLightOff(){
		indicatorLight.activate();
		exactLight.pressed(null);
		assertFalse(indicatorLight.isActive());
	}*/

	private ExactChangeLightController exactLight;
	private IndicatorLightSimulator indicatorLight;
}
