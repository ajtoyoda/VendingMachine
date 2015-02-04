package org.lsmr.vendingmachine.simulator;

/**
 * Represents a button used to select which pop you want. It extends selection button simulator and adds a field to keep
 * track of button cost
 * @author jamie
 *
 */
public class SelectPopButtonSimulator extends SelectionButtonSimulator {
	public SelectPopButtonSimulator(String name, int cost) {
		super(name, cost);
	}
	/**
	 * Represents the button being pressed. Notifies listeners of a purchae made event
	 */
	@Override
	public void press() {
		notifyPurchaseMade();
	}

	/**
	 * Private method to notify listeners following examples in other classes
	 */
	private void notifyPurchaseMade() {
		Class<?>[] parameterTypes = new Class<?>[] { SelectPopButtonSimulator.class };
		Object[] args = new Object[] { this };
		notifyListeners(SelectPopButtonListener.class, "purchaseMade",
				parameterTypes, args);
	}
}
