package test.main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;
import main.java.*;

public class SimulationTest {

    public Simulation simulation;
    public Document document;
    public Producer producer;

    @Before
    public void setUp() throws Exception {
        simulation = new Simulation();
        document = new Document("testDocument", "testTag", new Producer(simulation, "testProducer", "testTag"));
        producer = new Producer(simulation, "testName", "testTag");
    }

    @Test
    public void testGetNumberOfDocuments() throws Exception {
        assert simulation.getHowManyDocuments() == 0;
    }

    @Test
    public void testGetDocuments() throws Exception {
        assert simulation.getDocuments().size() == 0;
    }

    @Test
    public void testGetUsers() throws Exception {
        assert simulation.getUsers().size() == 0;
    }
}