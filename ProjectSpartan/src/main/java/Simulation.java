package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private static final SecureRandom random = new SecureRandom();
    private static int numberOfConsumers, numberOfProducers, numberOfDocuments, numberOfSearchResults, numberOfTags;
    private boolean quit;
    private List<Document> documents;
    private List<User> users;

    /**
     * Default constructor for Simulation class
     */
    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
    }

    /**
     * Randomly selects a tag from the enumerated class
     *
     * @param tags The class of tags to pick from
     * @return A random tag
     */
    public static Simulation.Tags randomTag(Class<Simulation.Tags> tags) {
        int index = random.nextInt(numberOfTags);
        return tags.getEnumConstants()[index];
    }

    public static void main(String[] args) {
        userInteraction();

        System.out.println("Beginning Simulation...");
        Simulation simulation = new Simulation();
        simulation.setupConsumers(numberOfConsumers);
        simulation.setupProducers(numberOfProducers);
        simulation.setupDocuments(numberOfDocuments);

        simulation.printSetup();

        while (!simulation.quit) {
            simulation.runIteration();
            User user = simulation.users.get(random.nextInt(simulation.users.size()));
            user.act(simulation.getDocuments(), numberOfSearchResults);
        }

        for (final User user : simulation.getUsers()) {
            System.out.println(user.payoffHistory());
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
                System.out.println("Please enter the number of consumers you would like the simulator to create (1-50): ");
                numberOfConsumers = Integer.parseInt(in.next());
                if (numberOfConsumers > 0 && numberOfConsumers < 51) {
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
                if (numberOfProducers > 0 && numberOfProducers < 51) {
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
                System.out.println("Please enter the number of documents you would like the simulator to create (1-10): ");
                numberOfDocuments = Integer.parseInt(in.next());
                if (numberOfDocuments > 0 && numberOfDocuments < 11) {
                    System.out.println("The simulator will create " + numberOfDocuments + " documents.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-10!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
        valid = false;

        while (!valid) {
            try {
                System.out.println("Please enter the number of tags you would like to use for the simulation (1-10): ");
                numberOfTags = Integer.parseInt(in.next());
                if (numberOfTags > 0 && numberOfTags < 11) {
                    System.out.println("The simulator will use " + numberOfTags + " tags.");
                    valid = true;
                } else {
                    System.out.println("ERROR: Please enter a number in the range 1-10!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please only enter numeric characters!");
            }
        }
    }

    private void printSetup() {
        System.out.println("The tags which will be used are: ");
        for (int i = 0; i < numberOfTags; i++) {
            System.out.print(Tags.values()[i].name + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("The consumers which will be used are: ");
        users.forEach((user) -> {
            if (user instanceof Consumer) System.out.print(user.getName() + " - " + user.getTaste() + ", ");
        });
        System.out.println();
        System.out.println();

        System.out.println("The producers which will be used are: ");
        users.forEach((user) -> {
            if (user instanceof Producer) System.out.print(user.getName() + " - " + user.getTaste() + ", ");
        });
        System.out.println();
        System.out.println();

        System.out.println("The documents which will be used are: ");
        documents.forEach((document) -> System.out.print(document.getName() + " - " + document.getTag() + ", "));
        System.out.println();
        System.out.println();
    }

    /**
     * Creates a specified number of consumers with a random taste
     *
     * @param number The amount of consumers to create
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
     *
     * @param number The amount of producers to create
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
     *
     * @param number The amount of documents to create
     */
    private void setupDocuments(int number) {
        System.out.println("Setting up Documents: ");
        for (int i = 1; i < number + 1; i++) {
            User user = users.get(random.nextInt(users.size()));
            while (user instanceof Consumer)
                user = users.get(random.nextInt(users.size())); //Keep picking a random user until it is a producer
            documents.add(((Producer) user).newDoc("Document #" + String.valueOf(i)));
        }
        System.out.println("-----------------------------");
    }

    private void runIteration() {
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean valid = false;

        System.out.println("A random user/producer is going to be selected to search.");
        while (!valid) {
            try {
                System.out.println("Please enter the number of documents you would like to search for (1-" + this.getNumberOfDocuments() + ") or 'q' to quit: ");
                input = in.next();
                numberOfSearchResults = Integer.parseInt(input);
                if (numberOfSearchResults > numberOfDocuments) {
                    System.out.println("You have requested a higher number of search results than there are documents! Using max number of documents instead.");
                    numberOfSearchResults = numberOfDocuments;
                }
                System.out.println("The simulator will show " + numberOfSearchResults + " search results.");
                valid = true;
            } catch (Exception e) {
                if (input.equals("q")) {
                    this.quit = true;
                    valid = true;
                } else {
                    System.out.println("ERROR: Please only enter numeric characters!");
                }
            }
        }
    }

    public int getNumberOfDocuments() {
        return this.getDocuments().size();
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

    //-----Enums------
    private enum Tags {
        Technology("Technology"),
        Sports("Sports"),
        Music("Music"),
        Comedy("Comedy"),
        Cartoons("Cartoons"),
        Movies("Movies"),
        Animals("Animals"),
        Environment("Environment"),
        Food("Food"),
        Drinks("Drinks");

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
