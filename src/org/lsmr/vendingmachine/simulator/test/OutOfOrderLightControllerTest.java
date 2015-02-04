package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.CoinChannelSimulator;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.HardwareSimulator;
import org.lsmr.vendingmachine.simulator.IndicatorLightSimulator;
import org.lsmr.vendingmachine.simulator.assignment1.OutOfOrderLightController;

public class OutOfOrderLightControllerTest {

	//This will happen before each and every test case
	@Before
	public void setup(){
		indicatorLight = new IndicatorLightSimulator("");
	}
	//This will happen after each and every test case
	@After
	public void tearDown() {

	}
	
	//This function tests that the light is turned on and that it reaches the correct function
	@Test
	public void testTurnsLightOn() {
		indicatorLight.deactivate();
		didCall = false;
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(1);
		outOfOrderLightController = new OutOfOrderLightController(new HardwareSimulator(new int []{0}, new int[]{0}, new String []{""}){
			@Override
			public void enableSafety(){
				didCall = true;
			}
		}, indicatorLight);
		receptacle.register(outOfOrderLightController);
		//Should fill receptacle
		try {
			receptacle.acceptCoin(new Coin(10));
		} catch (CapacityExceededException e) {
			e.printStackTrace();
		} catch (DisabledException e) {
			e.printStackTrace();
		}
		assertTrue(indicatorLight.isActive());
		assertTrue(didCall);
	}
	
	@Test
	public void testTurnsLightOff(){
		//Start with light on
		indicatorLight.activate();
		didCall = false;
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(1);
		try {
			receptacle.acceptCoin(new Coin(10));
		} catch (CapacityExceededException e) {
			e.printStackTrace();
		} catch (DisabledException e) {
			e.printStackTrace();
		}
		outOfOrderLightController = new OutOfOrderLightController(new HardwareSimulator(new int []{0}, new int[]{0}, new String []{""}){
			@Override
			public void disableSafety(){
				didCall = true;
			}
		}, indicatorLight);
		receptacle.register(outOfOrderLightController);
		receptacle.connect(null,  new CoinChannelSimulator(new CoinReceptacleSimulator(10)), null);
		try {
			receptacle.returnCoins();
		} catch (CapacityExceededException e) {
			e.printStackTrace();
		} catch (DisabledException e) {
			e.printStackTrace();
		}
		//Should fill receptacle
		assertFalse(indicatorLight.isActive());
		assertTrue(didCall);
		
	}
	private OutOfOrderLightController outOfOrderLightController;
	private IndicatorLightSimulator indicatorLight;
	private boolean didCall;

}
