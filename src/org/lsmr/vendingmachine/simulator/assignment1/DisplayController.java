package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.CoinReceptacleListener;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DispenserControllerListener;
import org.lsmr.vendingmachine.simulator.DisplaySimulator;
import org.lsmr.vendingmachine.simulator.DisplaySimulatorListener;
import org.lsmr.vendingmachine.simulator.PopCan;
import org.lsmr.vendingmachine.simulator.PopCanRackListener;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;

public class DisplayController extends AbstractStub implements
		PopCanRackListener, CoinReceptacleListener, DisplaySimulatorListener,
		DispenserControllerListener {
	private DisplaySimulator display;

	public DisplayController(DisplaySimulator display) {
		this.display = display;
	}

	@Override
	public void popAdded(PopCanRackSimulator popRack, PopCan pop) {
		display.display("Notice: Pop Added Successfully");
	}

	@Override
	public void popRemoved(PopCanRackSimulator popRack, PopCan pop) {
		display.display("Notice: Dispensing");
	}

	@Override
	public void popFull(PopCanRackSimulator popRack) {
		display.display("Notice: Pop Rack Full");
	}

	@Override
	public void popEmpty(PopCanRackSimulator popRack) {
		display.display("Notice: Pop Rack Empty");
	}

	@Override
	public void coinAdded(CoinReceptacleSimulator receptacle, Coin coin) {
		String finalString = new String("$");
		int dollars = receptacle.valueOfCoins() / 100;
		int cents = receptacle.valueOfCoins() % 100;
		if(dollars < 1){finalString += "0.";}else{finalString += (dollars + ".");}
		if(cents < 10){finalString += ("0" + cents);}else{finalString += cents;}
		display.display(finalString);
	}

	@Override
	public void messageChange(DisplaySimulator display, String oldMsg,
			String newMsg) {
		if (newMsg.contains("Notice: Not Enough Money")) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				//
			}
			display.display(oldMsg);
		}
		else if (newMsg.contains("Notice:")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				//
			}
			display.display(oldMsg);
		}
	}

	@Override
	public void coinsRemoved(CoinReceptacleSimulator receptacle) {
		display.display("$0.00");

	}

	@Override
	public void coinsFull(CoinReceptacleSimulator receptacle) {
		display.display("Notice: Cannot accept anymore coins");

	}

	@Override
	public void enabled(CoinReceptacleSimulator receptacle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(CoinReceptacleSimulator receptacle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notEnoughMoneyEvent(DispenserController control, Integer cost) {
		display.display("Notice: Not Enough Money. Selection Cost is: " + "$"
				+ cost / 100 + "." + cost % 100);
	}
	
	@Override
	public void notExactChange(DispenserController control) {
		display.display("Notice: Cannot Make Exact Change");
	}
}
