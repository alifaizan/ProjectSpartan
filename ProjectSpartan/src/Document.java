//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.HashSet;
import java.util.Set;

public class Document implements Comparable<Document> {

    //Instance Variables
    private String tag;
    private String name;
    private int popularity;
    private Set<User> likedBy;
    private Producer producer;

    //Constructor for Document that takes in a String
    //Increments documentCounter and documentIDCount every time a document is created

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
        this.popularity = 0;
        this.producer = p;
        this.likedBy = new HashSet<>();
    }

    /**
     * Adds a user to the list of users that like the document
     * @param user  The user that liked the document
     */
    public void likeDocument(User user) {
        if (!this.getLikedBy().contains(user)) {
            this.likedBy.add(user);
            this.popularity++;
        }
    }

    public int compareTo(Document document) {
        return document.getScore() - this.getScore();
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

    public int getPopularity() {
        return this.popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Set<User> getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public int getScore() {
        return this.getPopularity() + this.getProducer().getFollowers().size();
    }
}