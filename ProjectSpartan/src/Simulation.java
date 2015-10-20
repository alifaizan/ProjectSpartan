//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.security.SecureRandom;
import java.util.*;

public class Simulation {

    private static final SecureRandom random = new SecureRandom();
    private List<Document> documents;
    private List<User> users;
    private List<Producer> producers;
    private Map<User, ArrayList<Document>> likedDocuments;
    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
        producers = new ArrayList<Producer>();
        likedDocuments = new HashMap<User, ArrayList<Document>>();
    }

    public static Simulation.Tags randomTag(Class<Simulation.Tags> tag) {
        int index = random.nextInt(tag.getEnumConstants().length);
        return tag.getEnumConstants()[index];
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
        for (int i = 0; i < number; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + i, randomTag(Tags.class).toString());
            users.add(consumer);
        }
    }

    private void setupProducers(int number) {
        for (int i = 0; i < number; i++) {
            Producer producer = new Producer(this, "Producer #" + i, randomTag(Tags.class).toString());
            users.add(producer);
            producers.add(producer);
        }
    }

    private void setupDocuments(int number) {
        for (int i = 0; i < number; i++) {
            Document document = new Document("Document #" + i, randomTag(Tags.class).toString(), producers.get(random.nextInt(producers.size())));
            documents.add(document);
        }
    }

    private void likeDocument(User user, Document document) {
        if (likedDocuments.containsKey(user)) likedDocuments.get(user).add(document);
        else {
            ArrayList<Document> documents = new ArrayList<Document>();
            documents.add(document);
            likedDocuments.put(user, documents);
        }
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    //-----Getters and Setters------

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<User, ArrayList<Document>> getLikedDocuments() {
        return this.likedDocuments;
    }

    public void setLikedDocuments(Map<User, ArrayList<Document>> likedDocuments) {
        this.likedDocuments = likedDocuments;
    }

    private enum Tags {
        News("News"),
        Sports("Sports"),
        Music("Music"),
        Comedy("Comedy"),
        Cartoons("Cartoons"),
        Movies("Movies");

        private final String name;

        private Tags(String name) {
            this.name = name;
        }

        public boolean sameTag(String tag) {
            return (tag != null) && name.equals(tag);
        }

        public String toString() {
            return this.name;
        }

    }
}
