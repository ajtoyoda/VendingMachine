package org.lsmr.vendingmachine.simulator;

public class SelectPopButtonSimulator extends SelectionButtonSimulator {
	public SelectPopButtonSimulator(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void press() {
		notifyPurchaseMade();
	}

	private void notifyPurchaseMade() {
		Class<?>[] parameterTypes = new Class<?>[] { SelectPopButtonSimulator.class };
		Object[] args = new Object[] { this };
		notifyListeners(SelectPopButtonListener.class, "purchaseMade",
				parameterTypes, args);
	}
}
