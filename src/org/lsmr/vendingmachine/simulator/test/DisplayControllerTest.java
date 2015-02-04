package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.CapacityExceededException;
import org.lsmr.vendingmachine.simulator.Coin;
import org.lsmr.vendingmachine.simulator.CoinReceptacleSimulator;
import org.lsmr.vendingmachine.simulator.DeliveryChuteSimulator;
import org.lsmr.vendingmachine.simulator.DisabledException;
import org.lsmr.vendingmachine.simulator.DisplaySimulator;
import org.lsmr.vendingmachine.simulator.PopCan;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;
import org.lsmr.vendingmachine.simulator.assignment1.DispenserController;
import org.lsmr.vendingmachine.simulator.assignment1.DisplayController;
import org.lsmr.vendingmachine.simulator.assignment1.HardwareSimulatorExecutable;

public class DisplayControllerTest{

	@Before
	public void setup(){
		display = new DisplaySimulator();
		displayController = new DisplayController(display);
		basicOutStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(basicOutStream));
		hardwareOutStream = new ByteArrayOutputStream();
		HardwareSimulatorExecutable.out = new PrintStream(hardwareOutStream);
	}
	
	//This will happen after each and every test case
	@After
	public void tearDown() {
	}
	
	@Test
	public void testPopAddedMsg()
	{
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		PopCan pop = new PopCan();
		displayController.popAdded(popRack, pop);
		//assertEquals("Notice: Pop Added Successfully\n", basicOutStream.toString());
		assertEquals("Notice: Pop Added Successfully", hardwareOutStream.toString());
	}
	
	@Test
	public void testPopRemovedMsg()
	{
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		PopCan pop = new PopCan();
		displayController.popRemoved(popRack, pop);
		//assertEquals("Notice: Dispensing", basicOutStream.toString());
		assertEquals("Notice: Dispensing", hardwareOutStream.toString());
	}
	
	@Test
	public void testPopFullMsg()
	{
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		displayController.popFull(popRack);
		//assertEquals("Notice: Pop Rack Full", basicOutStream.toString());
		assertEquals("Notice: Pop Rack Full", hardwareOutStream.toString());
	}
	
	@Test
	public void testPopEmptyMsg()
	{
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		displayController.popEmpty(popRack);
		//assertEquals("Notice: Pop Rack Empty", basicOutStream.toString());
		assertEquals("Notice: Pop Rack Empty", hardwareOutStream.toString());
	}
	
	@Test
	public void testCoinAddedMsgOneCent()
	{
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(10);
		Coin coin = new Coin(1);
		try {
			receptacle.acceptCoin(coin);
		} catch (CapacityExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayController.coinAdded(receptacle, coin);
		//assertEquals("$0.01", basicOutStream.toString());
		assertEquals("$0.01", hardwareOutStream.toString());
	}
	
	@Test
	public void testCoinAddedMsgTenCent()
	{
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(10);
		Coin coin = new Coin(10);
		try {
			receptacle.acceptCoin(coin);
		} catch (CapacityExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayController.coinAdded(receptacle, coin);
		//assertEquals("$0.10", basicOutStream.toString());
		assertEquals("$0.10", hardwareOutStream.toString());
	}
	
	@Test
	public void testCoinAddedMsgDollar()
	{
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(10);
		Coin coin = new Coin(100);
		try {
			receptacle.acceptCoin(coin);
		} catch (CapacityExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayController.coinAdded(receptacle, coin);
		//assertEquals("$1.00", basicOutStream.toString());
		assertEquals("$1.00", hardwareOutStream.toString());
	}

	@Test
	public void testMessageChangeBasic()
	{
		displayController.messageChange(display, "$0.00", "$1.00");
		//assertEquals("$1.00", basicOutStream.toString());
		assertEquals("", hardwareOutStream.toString());
	}
	
	@Test
	public void testMessageChangeNEM()
	{
		displayController.messageChange(display, "$0.00", "Notice: Not Enough Money");
		//assertEquals("Notice: Not Enough Money", basicOutStream.toString());
		assertEquals("", hardwareOutStream.toString());
	}
	
	@Test
	public void testCoinsRemovedMsg()
	{
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(10);
		displayController.coinsRemoved(receptacle);
		//assertEquals("$0.00", basicOutStream.toString());
		assertEquals("$0.00", hardwareOutStream.toString());
	}
	
	@Test
	public void testCoinsFullMsg()
	{
		CoinReceptacleSimulator receptacle = new CoinReceptacleSimulator(10);
		displayController.coinsFull(receptacle);
		//assertEquals("Notice: Cannot accept anymore coins", basicOutStream.toString());
		assertEquals("Notice: Cannot accept anymore coins", hardwareOutStream.toString());
	}
	
	@Test
	public void testEnabled()
	{
		//does nothing
	}
	
	@Test
	public void testDisabled()
	{
		//does nothing
	}
	
	@Test
	public void testNotEnoughMoneyEvent()
	{
		DispenserController control = new DispenserController(null, null, null, null);
		Integer cost = 120; 
		displayController.notEnoughMoneyEvent(control, cost);
		//assertEquals("Notice: Not Enough Money. Selection Cost is: $1.20", basicOutStream.toString());
		assertEquals("Notice: Not Enough Money. Selection Cost is: $1.20", hardwareOutStream.toString());
	}
	
	@Test
	public void testNotExactChange()
	{
		DispenserController control = new DispenserController(null, null, null, null);
		displayController.notExactChange(control);
		//assertEquals("Notice: Cannot Make Exact Change", basicOutStream.toString());
		assertEquals("Notice: Cannot Make Exact Change", hardwareOutStream.toString());
	}
	
	@Test
	public void testItemDelivered()
	{
		DeliveryChuteSimulator chute = new DeliveryChuteSimulator(10);
		displayController.itemDelivered(chute);
		//assertEquals("Notice: Recieved " + chute.toString(), basicOutStream.toString());
		assertEquals("Notice: Recieved " + chute.toString(), hardwareOutStream.toString());
	}
	
	@Test
	public void testDoorOpened()
	{
		//does nothing
	}
	
	@Test
	public void testDoorClosed()
	{
		//does nothing
	}
	
	@Test
	public void testChuteFull()
	{
		DeliveryChuteSimulator chute = new DeliveryChuteSimulator(10);
		displayController.chuteFull(chute);
		//assertEquals("Notice: Please remove all objects from chute and try again", basicOutStream.toString());
				assertEquals("Notice: Please remove all objects from chute and try again", hardwareOutStream.toString());
	}
	
	private DisplaySimulator display;
	private DisplayController displayController;
	private ByteArrayOutputStream hardwareOutStream;
	private ByteArrayOutputStream basicOutStream;

}
