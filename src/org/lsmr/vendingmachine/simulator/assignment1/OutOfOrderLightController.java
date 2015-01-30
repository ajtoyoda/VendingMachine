package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.CoinReceptacleListener;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.HardwareSimulator;
import org.lsmr.vendingmachine.simulator.Coin;

public class OutOfOrderLightController extends AbstractStub implements
		CoinReceptacleListener {
	private HardwareSimulator sim;

	public OutOfOrderLightController(HardwareSimulator simulator) {
		sim = simulator;
	}

	@Override
	public void coinAdded(CoinReceptacleSimulator receptacle, Coin coin) {
		// No event
	}

	@Override
	public void coinsFull(CoinReceptacleSimulator receptacle) {
		sim.enableSafety();
	}

	@Override
	public void coinsRemoved(CoinReceptacleSimulator receptacle) {
		sim.disableSafety();

	}

	@Override
	public void enabled(CoinReceptacleSimulator receptacle) {
		// do nothing
	}

	@Override
	public void disabled(CoinReceptacleSimulator receptacle) {
		// do nothing
	}

}
