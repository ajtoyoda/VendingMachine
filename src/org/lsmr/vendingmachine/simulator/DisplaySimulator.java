package org.lsmr.vendingmachine.simulator;

import org.lsmr.vendingmachine.simulator.assignment1.HardwareSimulatorExecutable;

/**
 * A simple device that displays a string. How it does this is not part of the
 * simulation. A very long string might scroll continuously, for example.
 */
public class DisplaySimulator extends
		AbstractHardware<DisplaySimulatorListener> {
	private String message = "$0.00";

	/**
	 * Tells the display to start displaying the indicated message. Announces a
	 * "messageChange" event to its listeners.
	 */
	public void display(String msg) {
		String oldMsg = message;
		message = msg;
		HardwareSimulatorExecutable.out.print(msg);
		System.out.println(msg);
		notifyMessageChange(oldMsg, msg);
	}
	/**
	 * Blanks the display
	 */
	public void blank(){
		display("|");
	}

	/**
	 * Permits the display message to be set without causing events to be
	 * announced.
	 */
	public void loadWithoutEvents(String message) {
		this.message = message;
	}

	
	private void notifyMessageChange(String oldMsg, String newMsg) {
		Class<?>[] parameterTypes = new Class<?>[] { DisplaySimulator.class,
				String.class, String.class };
		Object[] args = new Object[] { this, oldMsg, newMsg };
		notifyListeners(DisplaySimulatorListener.class, "messageChange",
				parameterTypes, args);
	}
}
