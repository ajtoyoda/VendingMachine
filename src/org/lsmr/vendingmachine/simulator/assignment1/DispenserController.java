package org.lsmr.vendingmachine.simulator.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.lsmr.vendingmachine.simulator.AbstractHardware;
import org.lsmr.vendingmachine.simulator.AbstractHardwareListener;
import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.CoinRackSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.DispenserControllerListener;
import org.lsmr.vendingmachine.simulator.EmptyException;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.SelectPopButtonListener;
import org.lsmr.vendingmachine.simulator.SelectPopButtonSimulator;
import org.lsmr.vendingmachine.simulator.SelectionButtonSimulator;
/**
 * This class controls a popRack and currentStorage during purchases. It notifies two events, not exact change and not enough money
 * Its constructor arguments are a popRack, a mapping of the integer value of coins to the CoinRack which stores that value of coin and the
 * coinValues
 */
public class DispenserController extends
		AbstractHardware<DispenserControllerListener> implements
		SelectPopButtonListener {
	private PopCanRackSimulator popRack;
	private CoinReceptacleSimulator currentStorage;
	private Map<Integer, CoinRackSimulator> coinRackMap;
	private int []coinValues;

	public DispenserController(PopCanRackSimulator popCanRack,
			CoinReceptacleSimulator receptacle, Map<Integer, CoinRackSimulator> coinMap, int [] coinValue) {
		popRack = popCanRack;
		currentStorage = receptacle;
		coinRackMap = coinMap;
		coinValues = coinValue;
	}

/**
 * This function calculates difference between value needed and current purchasing value. If less it notifies notEnoughMoney
 * If equal it vends a pop and stores coins. If greater it attempts to make change. If making change is possible it dispenses change
 * then dispenses pop. If making change is not possible it notifies not exact change and keeps the money as current purchasing value
 */
	@Override
	public void purchaseMade(SelectPopButtonSimulator button) {
		if (currentStorage.valueOfCoins() == button.getCost()) {
			try {
				popRack.dispensePop();
				currentStorage.storeCoins();
			} catch (DisabledException e) {
				// No Requirement on how to handle this
			} catch (EmptyException e) {
				popRack.notifyPopEmpty();
			} catch (CapacityExceededException e) {
				try {
					currentStorage.returnCoins();
				} catch (CapacityExceededException e1) {
					// If it gets here we are screwed
					e1.printStackTrace();
				} catch (DisabledException e1) {
					e1.printStackTrace();
				}
			}
		} else if (currentStorage.valueOfCoins() > button.getCost()) {
			try {
				if(makeChange(currentStorage.valueOfCoins()-button.getCost())){
					popRack.dispensePop();
					currentStorage.storeCoins();
				}
			} catch (DisabledException e) {
				// No Requirement on disabled yet
			} catch (EmptyException e) {
				popRack.notifyPopEmpty();
			} catch (CapacityExceededException e) {
				try {
					currentStorage.returnCoins();
				} catch (CapacityExceededException e1) {
					// If it gets here we are screwed
					e1.printStackTrace();
				} catch (DisabledException e1) {
					e1.printStackTrace();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		} 
		else {
			Class<?>[] parameterTypes = new Class<?>[] {
					DispenserController.class, Integer.class };
			Integer arg = new Integer(button.getCost());
			Object[] args = new Object[] { this, arg };
			notifyListeners(DispenserControllerListener.class,
					"notEnoughMoneyEvent", parameterTypes, args);
		}
	}

	@Override
	public void pressed(SelectionButtonSimulator button) {
		// Probably should throw simulation exception

	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * This function calculates if it is possible to make change given the amount of change needed and current state of the CoinRacks
	 * If it is possible it vends change
	 * @param amountOfChange
	 * The amount of change needed to be returned
	 * @return
	 * If it successfully made change
	 */
	private boolean makeChange(int amountOfChange){
		//Sort coin values
		ArrayList <Integer> sortedSet = new ArrayList <Integer>();
		for(int i = 0; i < coinValues.length; i++){
			sortedSet.add(coinValues[i]);
		}
		Collections.sort(sortedSet);
		
		//Coin Racks are sorted by value so should line up with the sortedSet
		int numberNeeded[] = new int[sortedSet.size()];
		for(int i = sortedSet.size()-1; i >=0; i--){
			if(amountOfChange/sortedSet.get(i)< coinRackMap.get(sortedSet.get(i)).getAmount()){
				numberNeeded[i] = amountOfChange/sortedSet.get(i).intValue();
				amountOfChange= amountOfChange%sortedSet.get(i);
			}else{
				numberNeeded[i] = coinRackMap.get(sortedSet.get(i)).getAmount();
				amountOfChange = amountOfChange - coinRackMap.get(sortedSet.get(i)).getAmount()*sortedSet.get(i);
			}
		}
		
		//If amountOfChange is not equal to zero at this point change cannot be given
		if(amountOfChange != 0){
			notifyNotExactChange();
			return false;
		}
		
		//Release change
		for(int i = sortedSet.size()-1; i>=0; i--){
			for(int numberToRelease = 0; numberToRelease <numberNeeded[i]; numberToRelease++){
			try {
				coinRackMap.get(sortedSet.get(i)).releaseCoin();
				} catch (DisabledException e) {
					// No Requirement on how to handle this
				} catch (EmptyException e) {
					//Should never get here but if we do then notifyExactChange even though we dispensed some coins potentiall?
					notifyNotExactChange();
				} catch (CapacityExceededException e) {
					try {
						currentStorage.returnCoins();
						return false;
					} catch (CapacityExceededException e1) {
						// If it gets here we are screwed
						e1.printStackTrace();
					} catch (DisabledException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	private void notifyNotExactChange(){
		Class<?>[] parameterTypes = new Class<?>[] {
				DispenserController.class};
		Object[] args = new Object[] { this};
		notifyListeners(DispenserControllerListener.class,
				"notExactChange", parameterTypes, args);
	}
}
