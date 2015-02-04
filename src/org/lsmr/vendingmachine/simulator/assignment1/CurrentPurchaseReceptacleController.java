package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulator;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulatorListener;

/**
 * This class controls the current purchase receptacle. It should be registered to return button only
 * @author jamie
 *
 */
public class CurrentPurchaseReceptacleController extends AbstractStub implements
		SelectionButtonSimulatorListener {
	private CoinReceptacleSimulator currentPurchaseReceptacle;

	public CurrentPurchaseReceptacleController(
			CoinReceptacleSimulator receptacle) {
		currentPurchaseReceptacle = receptacle;
	}
	/**
	 * If button it is listening to is pressed return coins
	 */
	public void pressed(SelectionButtonSimulator button) {
		if (button.getName() == "return") {
			try {
				currentPurchaseReceptacle.returnCoins();
			} catch (CapacityExceededException e) {
				
				// this happens when the removal location is full not sure what to do about it though. not really specified or realistic (capacity is like 10000)
			} catch (DisabledException e) {
				// TODO handle exception
				e.printStackTrace();
			}
		}
	}
}
