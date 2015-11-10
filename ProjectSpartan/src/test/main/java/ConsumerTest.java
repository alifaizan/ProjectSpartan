package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;


public class ConsumerTest {

	public Consumer testConsumer;
	public Document testDocument;

    @Before
    public void setUp() throws Exception {
    	testConsumer = new Consumer(new Simulation(), "testName","testTaste");
    	testDocument = new Document("testDocument", "testTag", new Producer(simulation, "testProducer", "testTag"));
    }

    @Test
    public void testAct() throws Exception {
    	assert testConsumer.act() == 0;
    }

    @Test
    public void testCalculatePayoff() throws Exception {
    	assert testConsumer.calculatePayoff() == 0;
    }
}
