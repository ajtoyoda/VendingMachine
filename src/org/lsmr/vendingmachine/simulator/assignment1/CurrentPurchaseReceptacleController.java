package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulator;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulatorListener;

public class CurrentPurchaseReceptacleController extends AbstractStub implements
		SelectionButtonSimulatorListener {
	private CoinReceptacleSimulator currentPurchaseReceptacle;

	public CurrentPurchaseReceptacleController(
			CoinReceptacleSimulator receptacle) {
		currentPurchaseReceptacle = receptacle;
	}

	public void pressed(SelectionButtonSimulator button) {
		if (button.getName() == "return") {
			try {
				currentPurchaseReceptacle.returnCoins();
			} catch (CapacityExceededException e) {
				// this happens when the removal location is full not sure what to do about it though. not really specified or realistic (capacity is like 10000)
				e.printStackTrace();
			} catch (DisabledException e) {
				// TODO handle exception
				e.printStackTrace();
			}
		}
	}
}
