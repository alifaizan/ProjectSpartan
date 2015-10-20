//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.List;

public abstract class User {

    //Instance Variables
    private Simulation sim;
    private String taste, name;
    private int amountFollowed;
    private List<User> following;

    //Constructor for User that is passed "sim" of type Simulation 
    //and a "str" of type String such as the users name
    //Increments userCount and userIDCount every time a new user is created
    public User(Simulation simulation, String name, String taste) {
        this.name = name;
        this.taste = taste;
        this.sim = simulation;
        amountFollowed = 0;
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

    public void followUser(User user) {
        if (!this.getFollowing().contains(user)) {
            this.following.add(user);
            this.setAmountFollowed(this.getAmountFollowed() + 1);
        }
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

    public int getAmountFollowed() {
        return this.amountFollowed;
    }

    public void setAmountFollowed(int followed) {
        this.amountFollowed = followed;
    }

    public List<User> getFollowing() {
        return this.following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}