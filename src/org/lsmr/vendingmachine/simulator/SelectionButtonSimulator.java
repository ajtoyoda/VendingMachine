package org.lsmr.vendingmachine.simulator;

/**
 * Represents a simple push button on the vending machine. It ignores the
 * enabled/disabled state.
 */
public class SelectionButtonSimulator extends
		AbstractHardware<SelectionButtonSimulatorListener> {
	private String buttonName;
	private int buttonCost;

	public SelectionButtonSimulator(String name) {
		buttonName = name;
		buttonCost = 0;
	}

	public SelectionButtonSimulator(String name, int cost) {
		buttonName = name;
		buttonCost = cost;
	}

	/**
	 * Simulates the pressing of the button. Notifies its listeners of a
	 * "pressed" event.
	 */
	public void press() {
		notifyPressed();
	}

	public String getName() {
		return buttonName;
	}

	public int getCost() {
		return buttonCost;
	}

	private void notifyPressed() {
		Class<?>[] parameterTypes = new Class<?>[] { SelectionButtonSimulator.class };
		Object[] args = new Object[] { this };
		notifyListeners(SelectionButtonSimulatorListener.class, "pressed",
				parameterTypes, args);
	}

}
