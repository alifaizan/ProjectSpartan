//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.util.*;

public class Simulation {

    private List<Document> documents;
    private List<User> users;
    private List<String> tags;
    private Map<User, ArrayList<Document>> likedDocuments;

    public Simulation() {
        documents = new ArrayList<>();
        users = new ArrayList<>();
        tags = new ArrayList<>();
        likedDocuments = new HashMap<>();
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
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            User user = simulation.users.get(random.nextInt(simulation.users.size()));
            for (final Document document : user.act(simulation.getDocuments())) {
                simulation.likeDocument(user, document);
            }
        }
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

    private void likeDocument(User user, Document document) {
        if (likedDocuments.containsKey(user)) likedDocuments.get(user).add(document);
        else {
            ArrayList<Document> documents = new ArrayList<>();
            documents.add(document);
            likedDocuments.put(user, documents);
        }
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

    public Map<User, ArrayList<Document>> getLikedDocuments() {
        return this.likedDocuments;
    }

    public void setLikedDocuments(Map<User, ArrayList<Document>> likedDocuments) {
        this.likedDocuments = likedDocuments;
    }
}
