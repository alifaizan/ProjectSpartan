//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    private List<Document> documents;
    private List<User> users;
    private List<String> tags;


    public Simulation() {
        documents = new ArrayList<>();
        users = new ArrayList<>();
        tags = new ArrayList<>();
        tags.add("News");
        tags.add("Sports");
        tags.add("Music");
        tags.add("Comedy");
        tags.add("Cartoons");
        tags.add("Movies");
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.setupConsumers(5);
        simulation.setupProducers(10);
        simulation.setupDocuments(20);
    }

    private void setupConsumers(int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + i, tags.get(random.nextInt(tags.size())));
            users.add(consumer);
        }
    }

    private void setupProducers(int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Producer producer = new Producer(this, "Producer #" + i, tags.get(random.nextInt(tags.size())));
            users.add(producer);
        }
    }

    private void setupDocuments(int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Document document = new Document("Document #" + i, tags.get(random.nextInt(tags.size())));
            documents.add(document);
        }
    }

    protected ArrayList<Document> search() {

        return new ArrayList<Document>();
    }

    //-----Getters and Setters------

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
