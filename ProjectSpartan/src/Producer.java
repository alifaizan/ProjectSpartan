//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Producer extends User {

    private List<User> followers;
    private List<Document> created;

    /**
     * The default constructor for the Producer class
     *
     * @param simulation The associated simulation
     * @param name       The name of the producer
     * @param taste      The taste of the producer
     */
    public Producer(Simulation simulation, String name, String taste) {
        super(simulation, name, taste);
        followers = new ArrayList<User>();
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
        List<Document> relevantDocuments = search(documentList, numberToReturn);

        calculatePayoff(relevantDocuments);

        updateLikesAndFollowers(relevantDocuments);

        return relevantDocuments;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public Document newDoc(String name, String tag) {
        Document d = new Document(name, tag, this);
        created.add(d);
        return d;
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
        System.out.println("Searching for top " + String.valueOf(numberToReturn) + " documents...");
        Collections.sort(documentList);

        for (int i = 0; i < numberToReturn; i++) {
            documentsToReturn.add(documentList.get(i));
            System.out.println("Returning " + documentList.get(i).getName() + " with a score of: " + String.valueOf(documentList.get(i).getScore()));
        }

        return documentsToReturn;
    }

    /**
     * Likes given documents and follows associated users
     *
     * @param documents The documents to analyze
     */
    public void updateLikesAndFollowers(List<Document> documents) {
        documents.forEach((document) -> {
            if (document.getTag().equals(this.getTaste()) && !document.getLikedBy().contains(this)) {
                document.likeDocument(this);
                System.out.println(this.getName() + " just liked: " + document.getName());
            }
            document.getLikedBy().forEach((user) -> {
                if (user instanceof Producer && user != this && !this.getFollowing().contains(user)) {
                    this.followUser(user);
                    System.out.println(this.getName() + " just followed: " + user.getName());
                }
            });
        });
    }

    /**
     * Calculates payoff based on ranked documents
     * @param documents The documents to analyze
     */
    public void calculatePayoff(List<Document> documents) {
        int payoff = 0;

        for (final Document document : documents) {
            if (document.getTag().equals(this.getTaste()) && !(this.likes(document))) payoff += 2;
            else if (document.getTag().equals(this.getTaste())) payoff++;
        }

        System.out.println("This search gave " + this.getName() + " a payoff of: " + String.valueOf(payoff));
    }
}
