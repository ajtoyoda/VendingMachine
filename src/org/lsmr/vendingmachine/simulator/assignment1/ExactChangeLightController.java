package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.AbstractHardware;
import org.lsmr.vendingmachine.simulator.AbstractHardwareListener;
import org.lsmr.vendingmachine.simulator.DispenserControllerListener;
import org.lsmr.vendingmachine.simulator.IndicatorLightSimulator;
import org.lsmr.vendingmachine.simulator.SelectPopButtonListener;
import org.lsmr.vendingmachine.simulator.SelectPopButtonSimulator;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulator;

public class ExactChangeLightController implements DispenserControllerListener, SelectPopButtonListener{
	IndicatorLightSimulator light;
	
	public ExactChangeLightController(IndicatorLightSimulator light){
		this.light = light;
	}
	
	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notEnoughMoneyEvent(DispenserController control, Integer cost) {
		// do nothing		
	}

	@Override
	public void notExactChange(DispenserController control) {
		light.activate();
	}

	@Override
	public void pressed(SelectionButtonSimulator button) {
		//light.deactivate();
	}

	@Override
	public void purchaseMade(SelectPopButtonSimulator button) {
		//
	}
}
