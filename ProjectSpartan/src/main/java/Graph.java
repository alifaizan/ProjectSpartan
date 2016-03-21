package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph extends JPanel {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    private static final int SPACE = 10;
    private static final int POINT_SIZE = 4;
    private static int MAX;
    private List<Integer> scores;

    public Graph(List<Integer> scores) {
        this.scores = scores;
    }

    public static void createAndShowGui(List<Integer> scores) {
        Graph graph = new Graph(scores);
        graph.getMax();

        JFrame frame = new JFrame("Payoff Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graph);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void getMax() {
        for (final Integer score : scores) {
            if (score > MAX) MAX = score;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);

        List<Point> toGraph = new ArrayList<Point>();
        
        double xScale = ((double) getWidth() - (2 * 30) - 20) / (scores.size() - 1);
        double yScale = ((double) getHeight() - (2 * 30)) / (19);
        
        for(int i=0; i < scores.size(); i++){
        	int x = (int) (i * xScale + 50);
        	int y = (int) ((20 - scores.get(i)) * yScale + 30);
        	toGraph.add(new Point(x, y));
        }
        
        //x and y axes
        g2.drawLine(50, getHeight()-30, 50, 30);
        g2.drawLine(50, getHeight()-30, getWidth()-25, getHeight()-30);
        
        g.drawString("Payoff", 10, getHeight()/2);
        g.drawString("Time -> ", getWidth()/2, getHeight() - 15);
        
        for(int i = 0; i < toGraph.size()-1; i++){
        	int x1 = toGraph.get(i).x;
            int y1 = toGraph.get(i).y;
            int x2 = toGraph.get(i + 1).x;
            int y2 = toGraph.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);       
        	//g2.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
        }
        for (final Point point : toGraph) {
            int x = point.x - POINT_SIZE / 2;
            int y = point.y - POINT_SIZE / 2;
            int ovalW = POINT_SIZE;
            int ovalH = POINT_SIZE;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    /* For testing purposes only*/
    public static void main(String[] args){
    	List<Integer> l = new ArrayList<Integer>();
    	l.add(1);
    	l.add(10);
    	l.add(12);
    	l.add(4);
    	l.add(3);
    	l.add(2);
    	l.add(9);
    	l.add(3);
    	l.add(2);
    	l.add(9);
    	l.add(1);
    	//Graph g = new Graph(l);
    	createAndShowGui(l);
    }
}