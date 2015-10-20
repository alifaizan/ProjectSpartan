//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;

public class Producer extends User {

    private List<User> followers;
    private List<Document> created;

    public Producer(Simulation simulation, String name, String taste) {
        super(simulation, name, taste);
        followers = new ArrayList<User>();
        created = new ArrayList<Document>();
    }

    public List<Document> act(List<Document> documentList, int numberToReturn) {
        return new ArrayList<Document>();
    }

    public void addfollower(User user) {
        followers.add(user);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public Document newDoc(String name, String tag) {
        Document d = new Document(name, tag, this);
        created.add(d);
        return d;
    }

    public List<Document> search(List<Document> documentList, int numberToReturn) {

        return new ArrayList<Document>();
    }

    public void calculatePayoff(List<Document> documents) {
        int payoff = 0;

        for (final Document document : documents) {
            if (document.getTag().equals(super.getTaste()) && !(super.likes(document))) payoff++;
        }

        System.out.println("This search gave " + this.getName() + " a payoff of: " + String.valueOf(payoff));
    }
}
