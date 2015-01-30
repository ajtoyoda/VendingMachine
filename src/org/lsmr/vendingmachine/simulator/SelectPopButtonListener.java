package org.lsmr.vendingmachine.simulator;

/**
 * Listens for events emanating when a purchase is made
 */

public interface SelectPopButtonListener extends
		SelectionButtonSimulatorListener {
	public void purchaseMade(SelectPopButtonSimulator button);
}
