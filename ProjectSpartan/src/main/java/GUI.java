package main.java;
//Name: Yash Patel
//Student Number: 100943654
//Date: November 7, 2015

import main.java.User.Search_Strategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI implements ActionListener{

	Simulation simulation;
	JFrame frame;
	JMenuBar menuBar;
	JMenu simulationMenu, test;
	JMenuItem run, exit, stop;
	JTextArea display;
	JScrollPane pane;
	JTextField consumerText, producerText, documentText, tagText, searchText;
	JTextField consumerField, producerField, documentField, tagField, searchField;
	JPanel textFieldPanel;
	JButton runSim, search, changeStrategy, graph;
	JComboBox strategy, user;
	String[] users;
	boolean runPressed, searchPressed, stopPressed;
	
	/**
	 * 	Constructor for the GUI class
	 */
	public GUI(Simulation sim){
		this.simulation = sim;
		runPressed = false;
		searchPressed = false;
		stopPressed = false;
		frame = new JFrame("Milestone 3");
		menuBar = new JMenuBar();
		simulationMenu = new JMenu("Simulation");
		test = new JMenu("Test");
		run = new JMenuItem("Run");
		run.addActionListener(this);
		stop = new JMenuItem("Stop");
		stop.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		runSim = new JButton("Run Simulation!");
		runSim.addActionListener(this);
		search = new JButton("Search");
		search.addActionListener(this);
		changeStrategy = new JButton("Change Strategy");
		changeStrategy.addActionListener(this);
		graph = new JButton("Graph");
		graph.addActionListener(this);
		graph.setEnabled(false);
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
		searchText = new JTextField("Number of Documents to search");
		searchText.setEditable(false);
		searchField = new JTextField();
		searchField.addActionListener(this);
		
		String[] strategies = {"DocumentPopularity", "UserPopularity", "UserDistance", "LikeSimilarity", "FollowSimilarity"};
		strategy = new JComboBox(strategies);
		strategy.addActionListener(this);
		strategy.setEditable(false);
		strategy.addActionListener(this);
		users = new String[]{};
		user = new JComboBox(users);
		user.setEditable(false);
		user.addActionListener(this);

		//Setting up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(menuBar);
		frame.add(pane);
		frame.add(textFieldPanel, BorderLayout.NORTH);
		
		textFieldPanel.add(consumerText);
		textFieldPanel.add(consumerField);
		textFieldPanel.add(producerText);
		textFieldPanel.add(producerField);
		textFieldPanel.add(documentText);
		textFieldPanel.add(documentField);
		textFieldPanel.add(tagText);
		textFieldPanel.add(tagField);
		textFieldPanel.add(searchText);
		textFieldPanel.add(searchField);
		textFieldPanel.add(runSim);
		textFieldPanel.add(search);
		textFieldPanel.add(graph);
		textFieldPanel.add(strategy);
		textFieldPanel.add(user);
		textFieldPanel.add(changeStrategy);
		textFieldPanel.setLayout(new GridLayout(0,2));
		
		//menus
		menuBar.add(simulationMenu);
		simulationMenu.add(run);
		simulationMenu.add(stop);
		simulationMenu.add(exit);
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
	
	public boolean isSearchPressed(){
		System.out.println();
		return searchPressed;
	}
	
	public void setSearchPressed(boolean b){
		searchPressed = b;
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
	
	public void exit(){
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
			JOptionPane.showMessageDialog(null, "Number of Producers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(producerField.getText());
	}
	
	public int getDocuments(){
		if(documentField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Documents not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(documentField.getText());
	}
	
	public int getTags(){
		if(tagField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Tags not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(tagField.getText());
	}
	
	public int getSearchResults(){
		if(searchField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Search Results not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(searchField.getText());
	}

	public void setUsers(String[] s){
		DefaultComboBoxModel model = new DefaultComboBoxModel(s);
		this.users = s;
		user.setModel(model);
	}

	public User getUser(){
		User selected = simulation.getUsers().get(0);
		java.util.List<String> listedUsers = simulation.getUserName();
		for(int i=0; i <= simulation.getUsers().size(); i++){
			if(simulation.getUsers().get(i).getName().equals(listedUsers.get(i))){
				selected = simulation.getUsers().get(i);
			}
		}
		return selected;
	}

	public void changeStrategy(){
		getUser().setStrategy(Search_Strategy.valueOf(strategy.getActionCommand()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Run"))
			run();
		if(e.getActionCommand().equals("Stop"))
			simulation.setStop(true);
		if(e.getActionCommand().equals("Exit"))
			exit();
		if(e.getActionCommand().equals("Run Simulation!"))
			runPressed = true;
		if(e.getActionCommand().equals("Search"))
			searchPressed = true;
		if(e.getActionCommand().equals("Change Strategy"))
			changeStrategy();
		if (e.getActionCommand().equals("Graph"))
			Graph.createAndShowGui(new ArrayList<>());

	}
	
}
