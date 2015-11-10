package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;

public class DocumentTest {

    Producer testProducer;
    Document testDocument;

    @Before
    public void setUp() throws Exception {
        Producer testProducer = new Producer(new Simulation(), "testName", "testTaste");
        Document testDocument = new Document("testDocument", "testTag", testProducer);
    }

    @Test
    public void testLikeDocument() throws Exception {

        //Make sure user isn't originally in liked list
        assert !testDocument.getLikedBy().contains(testProducer);
        testDocument.likeDocument(testProducer);
        //Make sure user is in liked list after they like the document
        assert testDocument.getLikedBy().contains(testProducer);
    }

    @Test
    public void testCompareTo() throws Exception {

    }

    @Test
    public void testGetTag() throws Exception {

    }

    @Test
    public void testSetTag() throws Exception {

    }

    @Test
    public void testGetProducer() throws Exception {

    }

    @Test
    public void testSetProducer() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {

    }

    @Test
    public void testGetLikedBy() throws Exception {

    }

    @Test
    public void testSetLikedBy() throws Exception {

    }

    @Test
    public void testGetScore() throws Exception {

    }
}