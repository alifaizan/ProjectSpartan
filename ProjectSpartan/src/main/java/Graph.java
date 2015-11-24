package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int SPACE = 10;
    private static final int POINT_SIZE = 4;
    private static int MAX;
    private List<Integer> scores;

    public Graph(List<Integer> scores) {
        this.scores = scores;
    }

    public static void createAndShowGui(List<Integer> scores2) {
        List<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add(random.nextInt(maxScore));
        }

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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.DARK_GRAY);

        List<Point> toGraph = new ArrayList<Point>();
        for (final Integer score : scores) {
            toGraph.add(new Point((int) (score * ((double) getWidth() - 2 * SPACE) / (scores.size() - 1) + SPACE), (int) ((MAX - score) * ((double) getHeight() - 2 * SPACE) / (MAX - 1) + SPACE)));
        }

        for (final Point point : toGraph) {
            int x = point.x - POINT_SIZE / 2;
            int y = point.y - POINT_SIZE / 2;
            int ovalW = POINT_SIZE;
            int ovalH = POINT_SIZE;
            graphics2D.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}