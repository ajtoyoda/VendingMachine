package org.lsmr.vendingmachine.simulator;

import org.lsmr.vendingmachine.simulator.assignment1.DispenserController;

public interface DispenserControllerListener extends AbstractHardwareListener {
	public void notEnoughMoneyEvent(DispenserController control, Integer cost);
	public void notExactChange(DispenserController control);
}
