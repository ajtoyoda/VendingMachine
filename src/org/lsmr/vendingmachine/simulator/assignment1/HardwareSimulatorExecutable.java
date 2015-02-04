package org.lsmr.vendingmachine.simulator.assignment1;

import java.io.PrintStream;

import org.lsmr.vendingmachine.simulator.*;
import org.lsmr.vendingmachine.simulator.GUI.*;

public class HardwareSimulatorExecutable {
	public static PrintStream out;
	public static void main(String[] args) {
		int[] coinValues = { 5, 10, 25, 100, 200 };
		int[] popCosts = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
		String[] popNames = { "Ispep", "Cola-Coca", "7-Down", "Root Bear", "Alligatorade",
							  "Sunny E", "Six Alive", "Dr Salt", "Hour Maid", "Mountain Don't" };
		System.out.println("Hardware simulator initializing.");
		
		HardwareSimulator myMachine = Setup.setup(coinValues, popCosts, popNames);
		VendingMachineGUI vmGUI = new VendingMachineGUI(myMachine);
		myMachine.getExactChangeLight().register(vmGUI);
		myMachine.getOutOfOrderLight().register(vmGUI);
		//new VendingMachineGUI(myMachine);
		out = new PrintStream(vmGUI.getOutputStream(),true);
		for(int i = 0; i < 10; i ++){
			for(int j = 0; j < 5; j++)
				myMachine.getPopCanRack(i).loadWithoutEvents(new PopCan());	
		}	
		for(int i = 0; i <myMachine.getCoinValues().length; i++){
			for(int j = 0; j< 5; j++)
				myMachine.getCoinRack(i).loadWithoutEvents(new Coin(myMachine.getCoinValues()[i]));
		}

		/*Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please select from the following choices:");
			System.out.println("1 - Enter coin");
			System.out.println("2 - Select pop");
			System.out.println("3 - Coin return");
			System.out.print("Your entry: ");

			int myChoice = sc.nextInt();
			System.out.println("You picked option " + myChoice);

			if (myChoice == 1) {
				System.out.print("Coin Value : ");
				int coinVal = sc.nextInt();
				Coin c = new Coin(coinVal);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (myChoice == 2) {
				System.out.print("Pop selection: ");
				int popChoice = sc.nextInt();
				System.out.println("You've selected " + popChoice);

				myMachine.getSelectionButton(popChoice).press();
			} else if (myChoice == 3) {
				try {
					myMachine.getCoinReceptacle().returnCoins();
				} catch (CapacityExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DisabledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Coins returned through delivery chute.");
			} else {
				System.out.println("Exiting simulator.");
				break;
			}
		}
		sc.close();*/
	}
}