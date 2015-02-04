package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulator;
import org.lsmr.vendingmachine.simulator.assignment1.CurrentPurchaseReceptacleController;

public class CurrentPurchaseReceptacleControllerTest {

	@Before
	public void setup(){	
	}
	
	//This will happen after each and every test case
	@After
	public void tearDown() {
	}
	
	@Test
	public void testPressedReturn()
	{
		returnReceptacle = new CoinReceptacleSimulator(10){
			@Override
			public void returnCoins(){
				assertEquals(true,true);
			}
		};
		returnReceptacleController = new CurrentPurchaseReceptacleController(returnReceptacle);
		returnButton = new SelectionButtonSimulator("return");
		returnReceptacleController.pressed(returnButton);
	}
	
	@Test
	public void testPressedTrash()
	{
		trashReceptacle = new CoinReceptacleSimulator(10){
			@Override
			public void returnCoins(){
				assertEquals(false,true);
			}
		};
		trashReceptacleController = new CurrentPurchaseReceptacleController(trashReceptacle);
		trashButton = new SelectionButtonSimulator("trash");
		trashReceptacleController.pressed(trashButton);
	}
	
	private CoinReceptacleSimulator returnReceptacle;
	private CoinReceptacleSimulator trashReceptacle;
	private CurrentPurchaseReceptacleController returnReceptacleController;
	private CurrentPurchaseReceptacleController trashReceptacleController;
	private SelectionButtonSimulator returnButton;
	private SelectionButtonSimulator trashButton;

}
