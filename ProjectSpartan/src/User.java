//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class User {

    //Instance Variables
    private Simulation sim;
    private String taste, name;
    private List<User> followers;
    private List<User> following;
    private Set<Document> likedDocuments;

    //Constructor for User that is passed "sim" of type Simulation 
    //and a "str" of type String such as the users name
    //Increments userCount and userIDCount every time a new user is created

    /**
     * Default constructor for User class
     *
     * @param simulation The associated simulation
     * @param name       The name of the producer
     * @param taste      The taste of the producer
     */
    public User(Simulation simulation, String name, String taste) {
        this.name = name;
        this.taste = taste;
        this.sim = simulation;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        likedDocuments = new HashSet<>();
    }

    //-----Abstract Methods to be Implemented Separately in Consumer and Producer classes-----
    //Allows the users to find the top documents and likes it based on users taste
    public abstract List<Document> act(List<Document> documentList, int numberToReturn);

    //Return specified number of documents based on users taste
    public abstract List<Document> search(List<Document> documentList, int numberToReturn);

    //Return true if the document passed is liked by the user
    //Returns false otherwise
    public boolean likes(Document doc) {
        return doc.getLikedBy() != null && doc.getLikedBy().contains(this);
    }

    /**
     * Adds user to following list and increases number of followers the user has
     *
     * @param user The user to follow
     */
    public void followUser(User user) {
        if (!this.getFollowing().contains(user)) {
            this.following.add(user);
            user.followers.add(this);
        }
    }

    /**
     * Adds document and user pairing to a map
     *
     * @param document The document that was liked
     */
    public void likeDocument(Document document) {
        likedDocuments.add(document);
    }

    //-----Getters and Setters------
    public Simulation getSim() {
        return this.sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }

    public String getTaste() {
        return this.taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return this.following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}