package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.CoinReceptacleListener;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.HardwareSimulator;
import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.IndicatorLightSimulator;

public class OutOfOrderLightController extends AbstractStub implements
		CoinReceptacleListener {
	private HardwareSimulator sim;
	private IndicatorLightSimulator outOfOrderLight;

	public OutOfOrderLightController(HardwareSimulator simulator, IndicatorLightSimulator outOfOrderLight) {
		sim = simulator;
		this.outOfOrderLight = outOfOrderLight;
		
	}

	@Override
	public void coinAdded(CoinReceptacleSimulator receptacle, Coin coin) {
		// No event
	}

	@Override
	public void coinsFull(CoinReceptacleSimulator receptacle) {
		sim.enableSafety();
		outOfOrderLight.activate();
	}

	@Override
	public void coinsRemoved(CoinReceptacleSimulator receptacle) {
		sim.disableSafety();
		outOfOrderLight.deactivate();

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
