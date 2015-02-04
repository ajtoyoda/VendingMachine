package org.lsmr.vendingmachine.simulator.assignment1;

import org.lsmr.vendingmachine.simulator.HardwareSimulator;

public class Setup {
	public static HardwareSimulator setup(int[] coinValues, int[]popCosts, String[] popNames) {


		// vm is vendingMachine
		HardwareSimulator vm = new HardwareSimulator(coinValues, popCosts,
				popNames);

		// Adding in controllers
		CurrentPurchaseReceptacleController currentPurchase = new CurrentPurchaseReceptacleController(
				vm.getCoinReceptacle());

		DisplayController displayControl = new DisplayController(
				vm.getDisplay());
		DispenserController[] dispenserControl = new DispenserController[vm
				.getNumberOfPopCanRacks()];
		ExactChangeLightController exactChangeControl = new ExactChangeLightController(vm.getExactChangeLight());
		
		//Adding dispenser controller for each poprack and making it listen to correct button
		//Registering each dispenser controller to the display
		for (int i = 0; i < vm.getNumberOfPopCanRacks(); i++) {
			dispenserControl[i] = new DispenserController(vm.getPopCanRack(i),
					vm.getCoinReceptacle(), vm.getCoinRacksMap(), vm.getCoinValues());
			vm.getSelectionButton(i).register(dispenserControl[i]);
			vm.getSelectionButton(i).register(exactChangeControl);
			dispenserControl[i].register(displayControl);
			dispenserControl[i].register(exactChangeControl);
		}

		OutOfOrderLightController lightControl = new OutOfOrderLightController(
				vm, vm.getOutOfOrderLight());

		vm.getReturnButton().register(currentPurchase);
		vm.getDisplay().register(displayControl);
		vm.getCoinReceptacle().register(displayControl);
		vm.getStorageBin().register(lightControl);
		for (int i = 0; i < vm.getNumberOfPopCanRacks(); i++) {
			vm.getPopCanRack(i).register(displayControl);
		}
		return vm;
	}
}
