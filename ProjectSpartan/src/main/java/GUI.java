package main.java;
//Name: Yash Patel
//Student Number: 100943654
//Date: November 7, 2015

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI implements ActionListener{

	JFrame frame;
	JMenuBar menuBar;
	JMenu simulation, test;
	JMenuItem run, exit;
	JTextArea display;
	JScrollPane pane;
	JTextField consumerText, producerText, documentText, tagText;
	JTextField consumerField, producerField, documentField, tagField;
	JPanel textFieldPanel;
	JButton runSim;
	boolean runPressed;
	
	/**
	 * 	Constructor for the GUI class
	 */
	public GUI(){
		runPressed = false;
		frame = new JFrame("Milestone 2");
		menuBar = new JMenuBar();
		simulation = new JMenu("Simulation");
		test = new JMenu("Test");
		run = new JMenuItem("Run");
		run.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		runSim = new JButton("Run Simulation!");
		runSim.addActionListener(this);
		display = new JTextArea();
		pane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textFieldPanel = new JPanel();
		
		//Text fields
		consumerText = new JTextField("Number of Consumers: ");
		consumerText.setEditable(false);
		consumerField = new JTextField();
		consumerField.addActionListener(this);
		producerText = new JTextField("Number of Producers: ");
		producerText.setEditable(false);
		producerField = new JTextField();
		producerField.addActionListener(this);
		documentText = new JTextField("Number of Documents: ");
		documentText.setEditable(false);
		documentField = new JTextField();
		documentField.addActionListener(this);
		tagText = new JTextField("Number of Tags: ");
		tagText.setEditable(false);
		tagField = new JTextField();
		tagField.addActionListener(this);
		
		//Setting up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(menuBar);
		frame.add(pane, BorderLayout.CENTER);
		frame.add(textFieldPanel, BorderLayout.NORTH);
		textFieldPanel.add(consumerText);
		textFieldPanel.add(consumerField);
		textFieldPanel.add(producerText);
		textFieldPanel.add(producerField);
		textFieldPanel.add(documentText);
		textFieldPanel.add(documentField);
		textFieldPanel.add(tagText);
		textFieldPanel.add(tagField);
		textFieldPanel.add(runSim);
		textFieldPanel.setLayout(new GridLayout(0,2));
		
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
	
	public boolean isRunPressed(){
		return runPressed;
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
	
	public void printError(String s){
		JOptionPane.showMessageDialog(null, s, "Error",
                JOptionPane.ERROR_MESSAGE);
	}
	
	public void setTextFieldText(JTextField tf, String s){
		tf.setText(s);
	}
	
	public void stop(){
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
	
	public int getConsumers(){
		if(consumerField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Consumers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(consumerField.getText());
	}
	
	public int getProducers(){
		if(producerField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Consumers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(producerField.getText());
	}
	
	public int getDocuments(){
		if(documentField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Consumers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(documentField.getText());
	}
	
	public int getTags(){
		if(tagField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Consumers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(tagField.getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Run"))
			run();
		if(e.getActionCommand().equals("Exit"))
			stop();
		if(e.getActionCommand().equals("Run Simulation!"))
			runPressed = true;
	}
	
	public static void main(String[] args){
		new GUI();
	}
	
	
}
