package org.lsmr.vendingmachine.simulator.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vendingmachine.simulator.DisplaySimulator;
import org.lsmr.vendingmachine.simulator.PopCan;
import org.lsmr.vendingmachine.simulator.PopCanRackSimulator;
import org.lsmr.vendingmachine.simulator.assignment1.DisplayController;
import org.lsmr.vendingmachine.simulator.assignment1.HardwareSimulatorExecutable;

public class DisplayControllerTest{

	@Before
	public void setup(){
		display = new DisplaySimulator();
		displayController = new DisplayController(display);
		outStream = new ByteArrayOutputStream();
		HardwareSimulatorExecutable.out = new PrintStream(outStream);
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
		assertEquals("Notice: Pop Added Successfully", outStream.toString());
	}
	
	@Test
	public void testPopRemovedMsg()
	{
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		PopCan pop = new PopCan();
		displayController.popRemoved(popRack, pop);
		assertEquals("Notice: Dispensing", outStream.toString());
	}
	
	/*@Test
	public void testPopAddedMsg()
	{
		DisplayController displayController = new DisplayController(display);
		PopCanRackSimulator popRack = new PopCanRackSimulator(10);
		PopCan pop = new PopCan();
		DispenserController dispenserControl = new DispenserController(popRack,
				null, null, null);
		dispenserControl.register(displayController);
		System.setOut(new PrintStream(outStream));
		
		try {
			popRack.addPop(pop);
		} catch (CapacityExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(" Pop Added Successfully", outStream.toString());
		System.setOut(null);
		
	}
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	private DisplaySimulator display;
	private DisplayController displayController;
	private ByteArrayOutputStream outStream;

}
