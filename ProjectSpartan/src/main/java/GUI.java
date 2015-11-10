package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends Simulation implements ActionListener{

	JFrame frame;
	JMenuBar menuBar;
	JMenu simulation, test;
	JMenuItem run, exit;
	JTextArea display;
	JScrollPane pane;
	
	/**
	 * 	Constructor for the GUI class
	 */
	public GUI(){
		frame = new JFrame("Milestone 2");
		menuBar = new JMenuBar();
		simulation = new JMenu("Simulation");
		test = new JMenu("Test");
		run = new JMenuItem("Run");
		run.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		display = new JTextArea();
		pane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Setting up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setJMenuBar(menuBar);
		frame.add(pane);
		
		//menus
		menuBar.add(simulation);
		simulation.add(run);
		simulation.add(exit);
		
	}
	
	/**
	 * 	Creates a new Simulation 
	 */
	public void run(){
		Simulation sim = new Simulation();
		String[] args = new String[] {"123"};
		sim.main(args);
	}
	
	/**
	 * 	Creates a JOptionPane and returns the user's input
	 * @param s 	String for the JOptionPane
	 * @return		Whatever the user input as an integer
	 */
	public int dialog(String s){
		JOptionPane parameters = new JOptionPane("Simulation Parameters");
		int input = Integer.parseInt(parameters.showInputDialog(s));
		return input;
	}
	
	public String dialogString(String s){
		JOptionPane parameters = new JOptionPane("Simulation Parameters");
		String input = (parameters.showInputDialog(s));
		return input;
	}
	
	public void stop(){
		// TODO
		print("Simulation terminated");
		System.exit(0);
	}
	
	/**
	 * 	Prints to the JTextArea in GUI
	 * @param s		String to be printed
	 */
	public void print(String s){
		display.append(s + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Run"))
			run();
		if(e.getActionCommand().equals("Exit"))
			stop();
	}
	
}
