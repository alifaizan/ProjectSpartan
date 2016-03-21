package test.main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import main.java.Consumer;

import org.junit.Before;
import org.junit.Test;
import main.java.*;
import java.util.ArrayList;


public class ConsumerTest {

	public Consumer testConsumer;
    public Document testDocument, testDocument2, testDocument3, testDocument4, testDocument5;
    public Simulation simulation;
    ArrayList<Document> documents;

    @Before
    public void setUp() throws Exception {
        simulation = new Simulation();
        testConsumer = new Consumer(simulation, "testName", "testTaste");
        testDocument = new Document("testDocument", "testTag", new Producer(simulation, "testProducer", "testTag"));
        testDocument2 = new Document("testDocument2", "testTag", new Producer(simulation, "testProducer", "testTag"));
        testDocument3 = new Document("testDocument3", "testTag", new Producer(simulation, "testProducer", "testTag"));
        testDocument4 = new Document("testDocument4", "testTag", new Producer(simulation, "testProducer", "testTag"));
        testDocument5 = new Document("testDocument5", "testTag", new Producer(simulation, "testProducer", "testTag"));
        documents = new ArrayList<>();
        documents.add(testDocument);
        documents.add(testDocument2);
        documents.add(testDocument3);
        documents.add(testDocument4);
        documents.add(testDocument5);
    }

    @Test
    public void testAct() throws Exception {
        assert testConsumer.act(documents, 3).size() == 3;
    }

    @Test
    public void testCalculatePayoff() throws Exception {
        assert testConsumer.getPayoffs().size() == 0;
        testConsumer.calculatePayoff(documents);
        assert testConsumer.getPayoffs().size() == 1;
        assert testConsumer.getPayoffs().get(0) >= 0;
    }
}
