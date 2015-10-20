//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    private static final SecureRandom random = new SecureRandom();
    private static final int NUMBER_OF_ITERATIONS = 100;
    private List<Document> documents;
    private List<User> users;
    private Map<User, ArrayList<Document>> likedDocuments;

    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
        likedDocuments = new HashMap<User, ArrayList<Document>>();
    }

    public static Simulation.Tags randomTag(Class<Simulation.Tags> tag) {
        int index = random.nextInt(tag.getEnumConstants().length);
        return tag.getEnumConstants()[index];
    }

    public static void main(String[] args) {
        System.out.println("Beginning Simulation...");
        Simulation simulation = new Simulation();
        simulation.setupConsumers(5);
        simulation.setupProducers(10);
        simulation.setupDocuments(20);

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            User user = simulation.users.get(random.nextInt(simulation.users.size()));
            for (final Document document : user.act(simulation.getDocuments(), 10)) {
                simulation.likeDocument(user, document);
            }
        }
        System.out.println("Simulation Terminated.");
    }

    private void setupConsumers(int number) {
        System.out.println("Setting up Consumers: ");
        for (int i = 1; i < number + 1; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + String.valueOf(i), randomTag(Tags.class).toString());
            users.add(consumer);
            System.out.println(consumer.getName() + "  created");
        }
        System.out.println("-----------------------------");
    }

    private void setupProducers(int number) {
        System.out.println("Setting up Producers: ");
        for (int i = 1; i < number + 1; i++) {
            Producer producer = new Producer(this, "Producer #" + String.valueOf(i), randomTag(Tags.class).toString());
            users.add(producer);
            System.out.println(producer.getName() + "  created");
        }
        System.out.println("-----------------------------");
    }

    private void setupDocuments(int number) {
        System.out.println("Setting up Documents: ");
        for (int i = 1; i < number + 1; i++) {
            User user = users.get(random.nextInt(users.size()));
            while (user instanceof Consumer)
                user = users.get(random.nextInt(users.size())); //Keep picking a random user until it is a producer
            Document document = new Document("Document #" + String.valueOf(i), randomTag(Tags.class).toString(), (Producer) user);
            documents.add(document);
            System.out.println(document.getName() + "  created");
        }
        System.out.println("-----------------------------");
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

    //-----Enums------
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
