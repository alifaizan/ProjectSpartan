package main.java;//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Document {

    //------Comparators to be used for different search strategies------
    static Comparator<Document> documentPopularity = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            return o1.getLikedBy().size() - o2.getLikedBy().size();
        }
    };
    static Comparator<Document> userPopularity = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            return o1.getProducer().getFollowers().size() - o2.getProducer().getFollowers().size();
        }
    };
    static Comparator<Document> userDistance = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            return 0;
        }
    };
    static Comparator<Document> likeSimilarity = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            o1.getProducer().getLikedDocuments().retainAll(o2.getProducer().getLikedDocuments());
            return o1.getProducer().getLikedDocuments().size();
        }
    };

    //Constructor for Document that takes in a String
    //Increments documentCounter and documentIDCount every time a document is created
    static Comparator<Document> followSimilarity = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            o1.getProducer().getFollowers().retainAll(o2.getProducer().getFollowers());
            return o1.getProducer().getFollowers().size();
        }
    };
    //Instance Variables
    private String tag;
    private String name;
    private Set<User> likedBy;
    private Producer producer;

    /**
     * Default constructor for the Document class
     *
     * @param name Name of the document
     * @param tag  Tag for the document
     * @param p    Producer of document
     */
    public Document(String name, String tag, Producer p) {
        this.tag = tag;
        this.name = name;
        this.producer = p;
        this.likedBy = new HashSet<>();
    }

    /**
     * Adds a user to the list of users that like the document
     *
     * @param user The user that liked the document
     */
    public void likeDocument(User user) {
        this.likedBy.add(user);
        this.getProducer().calculatePayoff();
    }

    //-----Getters and Setters------
    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getLikedBy() {
        return this.likedBy;
    }
}