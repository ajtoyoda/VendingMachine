package org.lsmr.vendingmachine.simulator.assignment1;

import java.util.Timer;
import java.util.TimerTask;

import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.CoinReceptacleListener;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DeliveryChuteListener;
import org.lsmr.vendingmachine.simulator.DeliveryChuteSimulator;
import org.lsmr.vendingmachine.simulator.DispenserControllerListener;
import org.lsmr.vendingmachine.simulator.DisplaySimulator;
import org.lsmr.vendingmachine.simulator.DisplaySimulatorListener;
import org.lsmr.vendingmachine.simulator.PopCan;
import org.lsmr.vendingmachine.simulator.PopCanRackListener;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;

/**
 * This class controls the display and needs to listen to all object that display message
 * @author jamie
 *
 */
public class DisplayController extends AbstractStub implements
		PopCanRackListener, CoinReceptacleListener, DisplaySimulatorListener,
		DispenserControllerListener, DeliveryChuteListener {
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

	/**
	 * Displays the coin value of the total amount of money available to purchase on the display
	 */
	@Override
	public void coinAdded(CoinReceptacleSimulator receptacle, Coin coin) {
		String finalString = new String("$");
		int dollars = receptacle.valueOfCoins() / 100;
		int cents = receptacle.valueOfCoins() % 100;
		if (dollars < 1) {
			finalString += "0.";
		} else {
			finalString += (dollars + ".");
		}
		if (cents < 10) {
			finalString += ("0" + cents);
		} else {
			finalString += cents;
		}
		display.display(finalString);
	}

	/**
	 * Blanks the screen after a certain amount of time after giving a notice
	 */
	@Override
	public void messageChange(final DisplaySimulator display,
			final String oldMsg, String newMsg) {
		if (newMsg.contains("Notice: Not Enough Money")) {
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					display.blank();
					timer.cancel();
				}
			}, 4000);

		} else if (newMsg.contains("Notice:")) {
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					display.blank();
					timer.cancel();
				}
			}, 5000);
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
/**
 * Prints the amount of money needed to buy pop when the user does not currently have enough money to purchase it
 */
	@Override
	public void notEnoughMoneyEvent(DispenserController control, Integer cost) {
		String centCost;
		if(cost%100 < 10){
			centCost = "0"+cost%100;
		}else{
			centCost = ""+cost % 100;
		}
		display.display("Notice: Not Enough Money. Selection Cost is: " + "$"
				+ cost / 100 + "." + centCost);
	}

	@Override
	public void notExactChange(DispenserController control) {
		display.display("Notice: Cannot Make Exact Change");
	}

	@Override
	public void itemDelivered(DeliveryChuteSimulator chute) {
		display.display("Notice: Recieved " + chute.toString());

	}

	@Override
	public void doorOpened(DeliveryChuteSimulator chute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doorClosed(DeliveryChuteSimulator chute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chuteFull(DeliveryChuteSimulator chute) {
		display.display("Notice: Please remove all objects from chute and try again");

	}
}
