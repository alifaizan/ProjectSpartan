//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.security.SecureRandom;
import java.util.*;

public class Simulation {

    private static final SecureRandom random = new SecureRandom();
    private static int numberOfIterations, numberOfConsumers, numberOfProducers, numberOfDocuments, numberOfSearchResults;
    private List<Document> documents;
    private List<User> users;
    private Map<User, ArrayList<Document>> likedDocuments;

    /**
     * Default constructor for Simulation class
     */
    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
        likedDocuments = new HashMap<User, ArrayList<Document>>();
    }

    /**
     * Randomly selects a tag from the enumerated class
     *
     * @param tags The class of tags to pick from
     * @return A random tag
     */
    public static Simulation.Tags randomTag(Class<Simulation.Tags> tags) {
        int index = random.nextInt(tags.getEnumConstants().length);
        return tags.getEnumConstants()[index];
    }

    public static void main(String[] args) {
        userInteraction();

        System.out.println("Beginning Simulation...");
        Simulation simulation = new Simulation();
        simulation.setupConsumers(numberOfConsumers);
        simulation.setupProducers(numberOfProducers);
        simulation.setupDocuments(numberOfDocuments);

        for (int i = 0; i < numberOfIterations; i++) {
            User user = simulation.users.get(random.nextInt(simulation.users.size()));
            for (final Document document : user.act(simulation.getDocuments(), numberOfSearchResults)) {
                simulation.likeDocument(user, document);
            }
        }
        System.out.println("Simulation Terminated.");
    }

    /**
     * Obtains parameter values from users
     */
    private static void userInteraction() {
        Scanner in = new Scanner(System.in);
        boolean valid = false;

        System.out.println("Welcome to the Project Spartan simulation.");

        while (!valid) {
            try {
                System.out.println("Please enter the number of iterations you would like the simulator to perform (1-1000): ");
                numberOfIterations = Integer.parseInt(in.next());
                if (numberOfIterations > 1 && numberOfIterations < 1001) {
                    System.out.println("The simulator will run for " + numberOfIterations + " iterations.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-1000!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
        valid = false;

        while (!valid) {
            try {
                System.out.println("Please enter the number of consumers you would like the simulator to create (1-50): ");
                numberOfConsumers = Integer.parseInt(in.next());
                if (numberOfConsumers > 1 && numberOfConsumers < 51) {
                    System.out.println("The simulator will create " + numberOfConsumers + " consumers.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-50!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
        valid = false;

        while (!valid) {
            try {
                System.out.println("Please enter the number of producers you would like the simulator to create (1-50): ");
                numberOfProducers = Integer.parseInt(in.next());
                if (numberOfProducers > 1 && numberOfProducers < 51) {
                    System.out.println("The simulator will create " + numberOfProducers + " producers.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-50!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
        valid = false;

        while (!valid) {
            try {
                System.out.println("Please enter the number of documents you would like the simulator to create (1-1000): ");
                numberOfDocuments = Integer.parseInt(in.next());
                if (numberOfDocuments > 1 && numberOfDocuments < 1001) {
                    System.out.println("The simulator will create " + numberOfDocuments + " documents.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-1000!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
        valid = false;

        while (!valid) {
            try {
                System.out.println("Please enter the number of search results you would like to show for each iteration (1-50): ");
                numberOfSearchResults = Integer.parseInt(in.next());
                if (numberOfSearchResults > 1 && numberOfDocuments < 51) {
                    System.out.println("The simulator will show " + numberOfSearchResults + " search results.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-1000!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
    }

    /**
     * Creates a specified number of consumers with a random taste
     * @param number    The amount of consumers to create
     */
    private void setupConsumers(int number) {
        System.out.println("Setting up Consumers: ");
        for (int i = 1; i < number + 1; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + String.valueOf(i), randomTag(Tags.class).toString());
            users.add(consumer);
            System.out.println(consumer.getName() + "  created");
        }
        System.out.println("-----------------------------");
    }

    /**
     * Creates a specified number of producers with a random taste
     * @param number    The amount of producers to create
     */
    private void setupProducers(int number) {
        System.out.println("Setting up Producers: ");
        for (int i = 1; i < number + 1; i++) {
            Producer producer = new Producer(this, "Producer #" + String.valueOf(i), randomTag(Tags.class).toString());
            users.add(producer);
            System.out.println(producer.getName() + "  created");
        }
        System.out.println("-----------------------------");
    }

    /**
     * Creates a specified number of documents with a random tag
     * @param number    The amount of documents to create
     */
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

    /**
     * Adds document and user pairing to a map
     * @param user      The user that liked the document
     * @param document  The document that was liked
     */
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
