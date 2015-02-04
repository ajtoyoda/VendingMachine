/**
 * 
 */
package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.CoinRackSimulator;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;
import org.lsmr.vendingmachine.simulator.SelectPopButtonSimulator;
import org.lsmr.vendingmachine.simulator.assignment1.DispenserController;

/**
 * @author Evan Riegel JUnit tests for the DispenserController class
 *
 */
public class DispenserControllerTest {

	private DispenserController myDCTest;
	private CoinReceptacleSimulator receptacle;
	private CoinRackSimulator coinRackArray[];
	private SelectPopButtonSimulator myIspepTestButton;
	private int coinReleased[];
	private boolean hasDispensedPop, hasStoredCoins;
	private int[] coinValues;

	// The following is a special flag for indicating that the code has entered
	// the
	// popRack.dispensePop() line, which is directly tied in with the vending
	// machine
	// successfully making change.

	@Before
	public void setup() {
		int[] coins = { 5, 10, 25, 100, 200 };
		coinValues = coins;
		coinReleased = new int[coinValues.length];
		for (@SuppressWarnings("unused") int coin : coinReleased) {
			coin = 0;
		}
		hasDispensedPop = false;
		hasStoredCoins = false;

		myIspepTestButton = new SelectPopButtonSimulator("Ispep", 10);

		Map<Integer, CoinRackSimulator> coinRackMap = new HashMap<Integer, CoinRackSimulator>();

		coinRackArray = new CoinRackSimulator[coinValues.length];
		// I couldn't think of a way around this because it has to explicitly
		// define the inner function
		coinRackArray[0] = new CoinRackSimulator(10) {
			@Override
			public void releaseCoin() {
				coinReleased[0]++;
			}
		};
		coinRackArray[1] = new CoinRackSimulator(10) {
			@Override
			public void releaseCoin() {
				coinReleased[1]++;
			}
		};
		coinRackArray[2] = new CoinRackSimulator(10) {
			@Override
			public void releaseCoin() {
				coinReleased[2]++;
			}
		};
		coinRackArray[3] = new CoinRackSimulator(10) {
			@Override
			public void releaseCoin() {
				coinReleased[3]++;
			}
		};
		coinRackArray[4] = new CoinRackSimulator(10) {
			@Override
			public void releaseCoin() {
				coinReleased[4]++;
			}
		};

		receptacle = new CoinReceptacleSimulator(100) {
			@Override
			public void storeCoins() {
				hasStoredCoins = true;
			}
		};

		for (int i = 0; i < coinValues.length; i++) {
			coinRackMap.put(coinValues[i], coinRackArray[i]);
		}

		myDCTest = new DispenserController(new PopCanRackSimulator(10) {
			@Override
			public void dispensePop() {
				hasDispensedPop = true;
			}
		}, receptacle, coinRackMap, coinValues);
	}

	@After
	public void teardown() {
		myIspepTestButton = null;
		myDCTest = null;
	}

	@Test
	public void assertInstanceOfDispenserControllerCanBeCreated() {
		// Create a new DispenserController with myTestMachine's various private
		// variables
		assertTrue(myDCTest != null);
	}

	@Test
	public void assertPurchaseCannotBeMadeWithInsufficientCoins() {
		// Try our purchase, having entered no coins
		try {
			myDCTest.purchaseMade(myIspepTestButton);
			assertFalse(hasDispensedPop);
			assertFalse(hasStoredCoins);
			for (int i = 0; i < coinValues.length; i++) {
				assertEquals(0, coinReleased[i]);
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void assertPurchaseCanBeMadeWithExactChange() {
		// Put 10 cents into the "user coin slot"
		Coin c = new Coin(10);
		try {
			receptacle.acceptCoin(c);
		} catch (Exception exc) {
			// System.out.println(exc.getCause().toString());
		}
		// Try our purchase, having entered the exact cost
		try {
			myDCTest.purchaseMade(myIspepTestButton);
			assertTrue(hasDispensedPop);
			assertTrue(hasStoredCoins);
			for (int i = 0; i < coinValues.length; i++) {
				assertEquals(0, coinReleased[i]);
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void assertPurchaseCanBeMadeRequiringChangeMade() {
		// Put 15 cents into the "user coin slot"
		Coin c1 = new Coin(10);
		Coin c2 = new Coin(5);
		try {
			coinRackArray[0].acceptCoin(new Coin(5));
			receptacle.acceptCoin(c1);
			receptacle.acceptCoin(c2);
		} catch (Exception exc) {
			// System.out.println(exc.getCause().toString());
		}

		// Try our purchase, which should detect that change is needed
		try {
			myDCTest.purchaseMade(myIspepTestButton);
			assertTrue(hasDispensedPop);
			assertTrue(hasStoredCoins);
			for (int i = 0; i < coinValues.length; i++) {
				if (i != 0)
					assertEquals(0, coinReleased[i]);
				else {
					assertEquals(1, coinReleased[i]);
				}
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void assertCoinRackCannotMake5CentsChangeWithNoCoinsLoaded() {
		// Put 15 cents into the "user coin slot"
		Coin c1 = new Coin(10);
		Coin c2 = new Coin(5);
		try {
			//No loading of coins into change racks
			receptacle.acceptCoin(c1);
			receptacle.acceptCoin(c2);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			// System.out.println(exc.getCause().toString());
		}

		// Try our purchase, which will require 5 cents change
		try {
			myDCTest.purchaseMade(myIspepTestButton);
			assertFalse(hasDispensedPop);
			assertFalse(hasStoredCoins);
			for (int i = 0; i < coinValues.length; i++) {
					assertEquals(0, coinReleased[i]);
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void assertCoinRackCannotMake5CentsChangeWithOneDimeLoaded() {
		// Load one dime into machine
		try {
			coinRackArray[1].acceptCoin(new Coin(10));
		} catch (CapacityExceededException e1) {
			e1.printStackTrace();
		} catch (DisabledException e1) {
			e1.printStackTrace();
		}

		// Put 15 cents into the "user coin slot"
		Coin c1 = new Coin(10);
		Coin c2 = new Coin(5);
		try {
			receptacle.acceptCoin(c1);
			receptacle.acceptCoin(c2);
		} catch (Exception exc) {
			// System.out.println(exc.getCause().toString());
		}

		// Try our purchase, which will require 5 cents change
		try {
			myDCTest.purchaseMade(myIspepTestButton);
			assertFalse(hasDispensedPop);
			assertFalse(hasStoredCoins);
			for (int i = 0; i < coinValues.length; i++) {
					assertEquals(0, coinReleased[i]);
			}
		} catch (Exception e) {
		}
	}

}
