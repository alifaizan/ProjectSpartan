package main.java;
//Name: Yash Patel
//Student Number: 100943654
//Date: November 7, 2015

import main.java.Producer.Producer_Strategy;
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
	JComboBox consumerStrategy, consumers, producerStrategy, producers;
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
		frame = new JFrame("Milestone 2");
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
		
		String[] consStrategies = {"DocumentPopularity", "UserPopularity", "UserDistance", "LikeSimilarity", "FollowSimilarity"};
		consumerStrategy = new JComboBox(consStrategies);
		consumerStrategy.addActionListener(this);
		consumerStrategy.setEditable(false);
		consumerStrategy.addActionListener(this);
		users = new String[]{};
		consumers = new JComboBox(users);
		consumers.setEditable(false);
		consumers.addActionListener(this);
		String[] prodStrategies = {"A", "B"};
		producerStrategy = new JComboBox(prodStrategies);
		producerStrategy.addActionListener(this);
		producerStrategy.setEditable(false);
		producerStrategy.addActionListener(this);
		producers = new JComboBox(users);
		producers.setEditable(false);
		producers.addActionListener(this);
		
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
		textFieldPanel.add(consumerStrategy);
		textFieldPanel.add(consumers);
		textFieldPanel.add(producerStrategy);
		textFieldPanel.add(producers);
		textFieldPanel.add(changeStrategy);
		textFieldPanel.add(graph);
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

	public void setConsumers(String[] s) {
		DefaultComboBoxModel model = new DefaultComboBoxModel(s);
		this.users = s;
		consumers.setModel(model);
	}
	
	public int getProducers(){
		if(producerField.getText() == null)
			JOptionPane.showMessageDialog(null, "Number of Producers not entered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
		return Integer.parseInt(producerField.getText());
	}

	public void setProducers(String[] s) {
		DefaultComboBoxModel model = new DefaultComboBoxModel(s);
		this.users = s;
		producers.setModel(model);
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
	
	public User getConsumer(){
		User selected = simulation.getUsers().get(0);
		java.util.List<String> listedUsers = simulation.getConsumerNames();
		for(int i=0; i <= simulation.getUsers().size(); i++){
			if(simulation.getUsers().get(i).getName().equals(listedUsers.get(i))){
				selected = simulation.getUsers().get(i);
			}
		}
		return selected;
	}
	
	public User getProducer(){
		User selected = simulation.getUsers().get(0);
		java.util.List<String> listedUsers = simulation.getProducerNames();
		for(int i=0; i <= simulation.getUsers().size(); i++){
			if(simulation.getUsers().get(i).getName().equals(listedUsers.get(i))){
				selected = simulation.getUsers().get(i);
			}
		}
		return selected;
	}
	
	public void changeProducerStrategy(){
		Producer prod = (Producer)getProducer();
		prod.setProducerStrategy(Producer_Strategy.valueOf(consumerStrategy.getActionCommand()));
	}
	
	public void changeConsumerStrategy(){
		getConsumer().setStrategy(Search_Strategy.valueOf(consumerStrategy.getActionCommand()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		if(e.getActionCommand().equals("Change Strategy")){
			changeProducerStrategy();
			changeConsumerStrategy();		
		}
		if (e.getActionCommand().equals("Graph"))
			Graph.createAndShowGui(new ArrayList<>());
	}
	
}
