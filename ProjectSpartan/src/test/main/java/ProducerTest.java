package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProducerTest {

    public Producer testProducer;
    public Document testDocument, testDocument2, testDocument3, testDocument4, testDocument5;
    public Simulation simulation;
    public ArrayList<Document> documents;

    @Before
    public void setUp() throws Exception {
        simulation = new Simulation();
        simulation.setNumberOfTags(3);
        testProducer = new Producer(new Simulation(), "testName", "testTaste");
        testDocument = new Document("testDocument", "testTag", testProducer);
        testDocument = new Document("testDocument", "testTag", testProducer);
        testDocument2 = new Document("testDocument2", "testTag", testProducer);
        testDocument3 = new Document("testDocument3", "testTag", testProducer);
        testDocument4 = new Document("testDocument4", "testTag", testProducer);
        testDocument5 = new Document("testDocument5", "testTag", testProducer);
        documents = new ArrayList<>();
        documents.add(testDocument);
        documents.add(testDocument2);
        documents.add(testDocument3);
        documents.add(testDocument4);
        documents.add(testDocument5);
    }

    @Test
    public void testAct() throws Exception {
        assert testProducer.act(documents, 3).size() == 3;
    }

    @Test
    public void testNewDoc() throws Exception {
        assert testProducer.newDoc("testDoc") != null;
    }

    @Test
    public void testUpdateLikesAndFollowers() throws Exception {
        testProducer.updateLikesAndFollowers(documents);
        int totalLikes = 0;
        for (final Document document : documents) {
            totalLikes += document.getLikedBy().size();
        }
        assert totalLikes >= 0;
    }

    @Test
    public void testCalculatePayoff() throws Exception {
        assert testProducer.getPayoffs().size() == 0;
        testProducer.calculatePayoff();
        assert testProducer.getPayoffs().size() == 1;
        assert testProducer.getPayoffs().get(0) >= 0;
    }

    @Test
    public void testTotalLikes() throws Exception {
        assert testProducer.totalLikes() == 0;
        testProducer.likeDocument(testDocument);
        assert testProducer.totalLikes() == 1;
    }

    @Test
    public void testGetCreated() throws Exception {
        assert testProducer.getCreated().size() == 0;
    }

}
