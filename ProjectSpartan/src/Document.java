//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.*;

public class Document {

    //Instance Variables
    private String tag;
    private String name;
    private int popularity;
    private Set<User> likedBy;
    List<Producer> producer;

    //Constructor for Document that takes in a String
    //Increments documentCounter and documentIDCount every time a document is created
    public Document(String tag, String name, Producer p) {
        this.tag = tag;
        this.name = name;
        this.popularity = 0;
        producer = new ArrayList<Producer>();
        producer.add(p);
    }

    public void likeDocument(User user) {
        if (!this.getLikedBy().contains(user)) {
            this.likedBy.add(user);
            this.popularity++;
        }
    }

    //-----Getters and Setters------
    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Producer getProducer(){
    	return producer.get(0);
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
}