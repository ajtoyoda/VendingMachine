package org.lsmr.vendingmachine.simulator.GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.lsmr.vendingmachine.simulator.*;

public class VendingMachineGUI {

	private JFrame myFrame;
	private JTextField myLCDscreen;
	private JButton Pop_1, Pop_2, Pop_3, Pop_4, Pop_5,
                    Pop_6, Pop_7, Pop_8, Pop_9, Pop_10;
	private JButton coinReturn, Exit; 

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendingMachineGUI window = new VendingMachineGUI();
					window.myFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the GUI.
	 */
	public VendingMachineGUI(final HardwareSimulator myMachine){
		myFrame = new JFrame();
		//Set the size of our window, and center it in the screen
		myFrame.setSize(550, 610);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Use the GridBag layout manager
		myFrame.getContentPane().setLayout(new GridBagLayout());
		Insets myInsets = new Insets(0, 0, 0, 0);
		int centerInt = GridBagConstraints.CENTER;
		int northeastInt = GridBagConstraints.NORTHEAST;
		int fillBothInt = GridBagConstraints.BOTH;
		int fillNoneInt = GridBagConstraints.NONE;
		
		//Create LCD screen
		myLCDscreen = new JTextField();
		myLCDscreen.setText("Insert coins or select pop...");
		myLCDscreen.setColumns(15);
		myLCDscreen.setEditable(false);
		myFrame.getContentPane().add(myLCDscreen,
				getNewConstraints(0, 0, 3, 1, 1.0, 1.0, centerInt, GridBagConstraints.HORIZONTAL,
				myInsets, 0, 0));
		
		//Coin entry label text
		JLabel lblEnterCoin = new JLabel("Enter coin:");
		myFrame.getContentPane().add(lblEnterCoin,
				getNewConstraints(0, 1, 1, 3, 1.0, 1.0, northeastInt, fillNoneInt,
				myInsets, 0, 0));
		

		//$0.05 button
		JButton fiveCents = new JButton("$0.05");
		myFrame.getContentPane().add(fiveCents,
				getNewConstraints(1, 1, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		fiveCents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(5);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});
		
		//$0.10 button
		JButton tenCents = new JButton("$0.10");
		myFrame.getContentPane().add(tenCents, 
				getNewConstraints(2, 1, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		tenCents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(10);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});
		
		//$0.25 button
		JButton twentyFiveCents = new JButton("$0.25");
		myFrame.getContentPane().add(twentyFiveCents, 
				getNewConstraints(1, 2, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		twentyFiveCents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(25);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});
		
		//$1.00 button
		JButton oneDollar = new JButton("$1.00");
		myFrame.getContentPane().add(oneDollar, 
				getNewConstraints(2, 2, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		oneDollar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(100);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});
		
		//$2.00 button
		JButton twoDollars = new JButton("$2.00");
		myFrame.getContentPane().add(twoDollars, 
				getNewConstraints(1, 3, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		twoDollars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(200);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});

		//Pop selection label text
		JLabel lblSelectPop = new JLabel("Select pop:");
		myFrame.getContentPane().add(lblSelectPop,
				getNewConstraints(0, 4, 1, 3, 1.0, 1.0, northeastInt, fillNoneInt,
				myInsets, 0, 0));

		//First pop button
		//Pop_1 = new JButton(myMachine.getPopNameAt(0));
		Pop_1 = new JButton(new ImageIcon(getClass().getResource("Icons/1.png"), myMachine.getPopNameAt(0)));
		myFrame.getContentPane().add(Pop_1, 
				getNewConstraints(1, 4, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(0).press();
			}
		});

		//Second pop button
		//Pop_2 = new JButton(myMachine.getPopNameAt(1));
		Pop_2 = new JButton(new ImageIcon(getClass().getResource("Icons/2.jpg"), myMachine.getPopNameAt(1)));
		myFrame.getContentPane().add(Pop_2, 
				getNewConstraints(2, 4, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(1).press();
			}
		});

		//Third pop button
		//Pop_3 = new JButton(myMachine.getPopNameAt(2));
		Pop_3 = new JButton(new ImageIcon(getClass().getResource("Icons/3.jpg"), myMachine.getPopNameAt(2)));
		myFrame.getContentPane().add(Pop_3, 
				getNewConstraints(1, 5, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(2).press();
			}
		});

		//Fourth pop button
		//Pop_4 = new JButton(myMachine.getPopNameAt(3));
		Pop_4 = new JButton(new ImageIcon(getClass().getResource("Icons/4.jpg"), myMachine.getPopNameAt(3)));
		myFrame.getContentPane().add(Pop_4, 
				getNewConstraints(2, 5, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(3).press();
			}
		});

		//Fifth pop button
		//Pop_5 = new JButton(myMachine.getPopNameAt(4));
		Pop_5 = new JButton(new ImageIcon(getClass().getResource("Icons/5.png"), myMachine.getPopNameAt(4)));
		myFrame.getContentPane().add(Pop_5, 
				getNewConstraints(1, 6, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(4).press();
			}
		});

		//Sixth pop button
		//Pop_6 = new JButton(myMachine.getPopNameAt(5));
		Pop_6 = new JButton(new ImageIcon(getClass().getResource("Icons/6.jpg"), myMachine.getPopNameAt(5)));
		myFrame.getContentPane().add(Pop_6, 
				getNewConstraints(2, 6, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(5).press();
			}
		});

		//Seventh pop button
		//Pop_7 = new JButton(myMachine.getPopNameAt(6));
		Pop_7 = new JButton(new ImageIcon(getClass().getResource("Icons/7.jpg"), myMachine.getPopNameAt(6)));
		myFrame.getContentPane().add(Pop_7, 
				getNewConstraints(1, 7, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(5).press();
			}
		});

		//Eigth pop button
		//Pop_8 = new JButton(myMachine.getPopNameAt(7));
		Pop_8 = new JButton(new ImageIcon(getClass().getResource("Icons/8.jpg"), myMachine.getPopNameAt(7)));
		myFrame.getContentPane().add(Pop_8, 
				getNewConstraints(2, 7, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(5).press();
			}
		});

		//Ninth pop button
		//Pop_9 = new JButton(myMachine.getPopNameAt(8));
		Pop_9 = new JButton(new ImageIcon(getClass().getResource("Icons/9.png"), myMachine.getPopNameAt(8)));
		myFrame.getContentPane().add(Pop_9, 
				getNewConstraints(1, 8, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(5).press();
			}
		});

		//Tenth pop button
		//Pop_10 = new JButton(myMachine.getPopNameAt(9));
		Pop_10 = new JButton(new ImageIcon(getClass().getResource("Icons/10.jpg"), myMachine.getPopNameAt(9)));
		myFrame.getContentPane().add(Pop_10, 
				getNewConstraints(2, 8, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Pop_10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				myMachine.getSelectionButton(5).press();
			}
		});

		//Coin return button
		coinReturn = new JButton("Coin Return");
		myFrame.getContentPane().add(coinReturn, 
				getNewConstraints(0, 9, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		coinReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					myMachine.getCoinReceptacle().returnCoins();
				} catch (CapacityExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DisabledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		//Exit button
		Exit = new JButton("Exit");
		//Exit.setBounds(179, 199, 78, 52);
		myFrame.getContentPane().add(Exit, 
				getNewConstraints(2, 9, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
				myInsets, 0, 0));
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2){
				myFrame.dispose();
				
			}
		});
		
		/*class Mylistener implements ActionListener{
			private int buttonName;
			Mylistener(int buttonName){
				this.buttonName = buttonName;
			}
			public void actionPerformed(ActionEvent e) {
				int total_coin = 0;
				switch(buttonName){
				case 0:
					total_coin = total_coin + buttonName;
					myLCDscreen.setText("You've entered " + total_coin + " dollars");
				}
				
			}
			
		}*/
		myFrame.setVisible(true);
	}
	
	//This function lets us avoid having to make a new GridBagConstraints
	//object every time
	public GridBagConstraints getNewConstraints(
			int cellX, int cellY,
			int cellHeight, int cellWidth,
			double weightX, double weightY,
			int anchor, int fill,
			Insets ins, int padX, int padY){
		return new GridBagConstraints(cellX, cellY, cellHeight, cellWidth,
				weightX, weightY, anchor, fill, ins, padX, padY);
	}
}
