package main.java;//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;

public class Producer extends User {

    private List<Document> created;
    private Producer_Strategy producerStrategy;

    /**
     * The default constructor for the Producer class
     *
     * @param simulation The associated simulation
     * @param name       The name of the producer
     * @param taste      The taste of the producer
     */
    public Producer(Simulation simulation, String name, String taste) {
        super(simulation, name, taste);
        producerStrategy = Producer_Strategy.A;
        created = new ArrayList<Document>();
        this.setFollowing(new ArrayList<>());
    }

    /**
     * Analyzes a given list of documents and determines how related it is to the user and whether to like/follow anyone else
     *
     * @param documentList   The list of documents to analyze
     * @param numberToReturn The number of documents to return
     * @return List of documents that have been sorted and analyzed
     */
    public List<Document> act(List<Document> documentList, int numberToReturn) {
        this.getSim().getDocuments().add(this.newDoc("Document #" + String.valueOf(this.getSim().getNumberOfDocuments() + 1)));

        List<Document> relevantDocuments = search(documentList, numberToReturn);

        calculatePayoff();

        updateLikesAndFollowers(relevantDocuments);

        return relevantDocuments;
    }

    public void undoAct() {
        //Remove document that was created
        getSim().getDocuments().remove(getSim().getDocuments().size() - 1);
        created.remove(created.size() - 1);

        //remove last payoff
        this.removeLastPayoff();

        this.unlikeAndUnfollowPrevious();
    }

    public Document newDoc(String name) {
        Document d = new Document(name, this.getTaste(), this);
        created.add(d);
        setToPrint(name + " created by " + this.getName());
        return d;
    }

    /**
     * Likes given documents and follows associated users
     *
     * @param documents The documents to analyze
     */
    public void updateLikesAndFollowers(List<Document> documents) {
        if (this.producerStrategy == Producer_Strategy.A) super.updateLikesAndFollowers(documents, this.getTaste());

        else {
            String randomTag = this.getTaste(); //Find a taste other than the producers taste to like these documents as per project description
            while (randomTag.equals(this.getTaste())) randomTag = getSim().randomTag().toString();

            super.updateLikesAndFollowers(documents, randomTag);
        }
    }

    /**
     * Calculates payoff based on total followers and likes between all produced documents
     */
    public String calculatePayoff() {
        int followers = this.getFollowers().size();
        int totalDocumentLikes = this.totalLikes();

        int payoff = followers + totalDocumentLikes;

        this.updatePayoffs(payoff);

        return this.getName() + " now has a payoff of: " + String.valueOf(payoff) + ", from " + followers + " total followers and " + totalDocumentLikes + " total document likes.";
    }

    public int totalLikes() {
        int totalDocumentLikes = 0;

        for (final Document document : this.getCreated()) {
            totalDocumentLikes += document.getLikedBy().size();
        }

        return totalDocumentLikes;
    }

    public List<Document> getCreated() {
        return this.created;
    }

    public void setProducerStrategy(Producer_Strategy strategy) {
        this.producerStrategy = strategy;
    }

    //-----Enums------
    public enum Producer_Strategy {
        A("A"),
        B("B");

        private final String name;

        private Producer_Strategy(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}
