package main.java;//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.*;

public abstract class User {

    String toPrint;
    //Instance Variables
    private Simulation sim;
    private String taste, name;
    private List<Integer> payoffs;
    private List<User> followers;
    private List<User> following;
    private Set<Document> likedDocuments;
    private ArrayList<String> printStrings;
    private Search_Strategy strategy;


    //Constructor for User that is passed "sim" of type Simulation 
    //and a "str" of type String such as the users name
    //Increments userCount and userIDCount every time a new user is created

    /**
     * Default constructor for User class
     *  @param simulation The associated simulation
     * @param name       The name of the producer
     * @param taste      The taste of the producer
     */
    public User(Simulation simulation, String name, String taste) {
        this.name = name;
        this.taste = taste;
        this.sim = simulation;
        payoffs = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        likedDocuments = new HashSet<>();
        printStrings = new ArrayList<>();
        this.strategy = Search_Strategy.LIKE_SIMILARITY;
    }

    //-----Abstract Methods to be Implemented Separately in Consumer and Producer classes-----
    //Allows the users to find the top documents and likes it based on users taste
    public abstract List<Document> act(List<Document> documentList, int numberToReturn);

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
        if (user instanceof Producer) {
            ((Producer) user).calculatePayoff();
        }
    }

    public void updatePayoffs(int payoff) {
        this.payoffs.add(payoff);
    }

    public String payoffHistory() {
        String payoffString = "Payoff History for User " + this.getName() + ": ";
        for (final Integer payoff : payoffs) {
            payoffString += payoff + ", ";
        }
        payoffString = payoffString.substring(0, payoffString.length() - 2);
        payoffString += "\n";

        payoffString += "Total Followers =  " + this.getFollowers().size() + ", Total Following =  " + this.getFollowing().size() + ", Total Liked Documents = " + this.getLikedDocuments().size();

        if (this instanceof Producer) {
            payoffString += ", Total Created = " + ((Producer) this).getCreated().size() + ", Total Document Likes = " + ((Producer) this).totalLikes();
        }

        return payoffString;
    }

    /**
     * Likes given documents and follows associated users
     *
     * @param documents The documents to analyze
     */
    public void updateLikesAndFollowers(List<Document> documents, String taste) {
        documents.forEach((document) -> {
            if (document.getTag().equals(taste) && !document.getLikedBy().contains(this)) {
                document.likeDocument(this);
                //System.out.println(this.getName() + " just liked: " + document.getName());
                addtoPrintStrings(this.getName() + " just liked: " + document.getName());

            }
            document.getLikedBy().forEach((user) -> {
                if (user instanceof Producer && user != this && !this.getFollowing().contains(user)) {
                    this.followUser(user);
                    //System.out.println(this.getName() + " just followed: " + user.getName());
                    addtoPrintStrings(this.getName() + " just followed: " + user.getName());

                }
            });
        });
    }

    /**
     * Ranks list of documents according to taste
     *
     * @param documentList   List of documents to rank
     * @param numberToReturn The number of documents to return
     * @return List of sorted documents
     */
    public List<Document> search(List<Document> documentList, int numberToReturn) {
        ArrayList<Document> documentsToReturn = new ArrayList<>();
        addtoPrintStrings("Searching for top " + String.valueOf(numberToReturn) + " documents for " + this.getName() + "...");
        switch (this.getStrategy()) {
            case DOCUMENT_POPULARITY:
                Collections.sort(documentList, Document.documentPopularity);
                break;
            case USER_POPULARITY:
                Collections.sort(documentList, Document.userPopularity);
                break;
            case USER_DISTANCE:
                Collections.sort(documentList, Document.userDistance);
                break;
            case LIKE_SIMILARITY:
                Collections.sort(documentList, Document.likeSimilarity);
                break;
            case FOLLOW_SIMILARITY:
                Collections.sort(documentList, Document.followSimilarity);
                break;
        }


        for (int i = 0; i < numberToReturn; i++) {
            documentsToReturn.add(documentList.get(i));
            addtoPrintStrings("Returning " + documentList.get(i).getName());

        }

        return documentsToReturn;
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

    public List<User> getFollowing() {
        return this.following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Integer> getPayoffs() {
        return this.payoffs;
    }

    public Set<Document> getLikedDocuments() {
        return this.likedDocuments;
    }

    public String getToPrint() {
        return toPrint;
    }
    
    public void setToPrint(String s){
    	toPrint = s;
    }

    public ArrayList<String> getPrintStrings(){
    	return printStrings;
    }

    public void addtoPrintStrings(String s){
    	printStrings.add(s);
    }

    public Search_Strategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(Search_Strategy strategy) {
        this.strategy = strategy;
    }

    //-----Enums------
    public enum Search_Strategy {
        DOCUMENT_POPULARITY("DocumentPopularity"),
        USER_POPULARITY("UserPopularity"),
        USER_DISTANCE("UserDistance"),
        LIKE_SIMILARITY("LikeSimilarity"),
        FOLLOW_SIMILARITY("FollowSimilarity");

        private final String name;

        private Search_Strategy(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}