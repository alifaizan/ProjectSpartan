package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;

public class ProducerTest {

    public Producer testProducer;
    public Document testDocument;

    @Before
    public void setUp() throws Exception {
        testProducer = new Consumer(new Simulation(), "testName","testTaste");
        testDocument = new Document("testDocument", "testTag", new Producer(simulation, "testProducer", "testTag"));
    }

    @Test
    public void testAct() throws Exception {
        assert testProducer.act() == 0;
    }

    @Test
    public void testNewDoc() throws Exception {
        assert testProducer == 0;
    }

    @Test
    public void testSearch() throws Exception {

    }

    @Test
    public void testUpdateLikesAndFollowers() throws Exception {

    }

    @Test
    public void testCalculatePayoff() throws Exception {

    }

    @Test
    public void testGetFollowers() throws Exception {

    }

    @Test
    public void testSetFollowers() throws Exception {

    }

    @Test
    public void testGetCreated() throws Exception {

    }

    @Test
    public void testSetCreated() throws Exception {

    }
}
