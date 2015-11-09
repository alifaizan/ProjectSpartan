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

    private static final int MAX_PRODUCERS = 50;
    private static final int MAX_CONSUMERS = 50;
    private static final int MAX_DOCUMENTS = 10;
    private static final int MAX_TAGS = 10;
    private static final SecureRandom random = new SecureRandom();
    private static int numberOfConsumers, numberOfProducers, numberOfDocuments, numberOfSearchResults, numberOfTags;
    private boolean quit;
    private List<Document> documents;
    private List<User> users;
    static GUI gui;

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
     * @return A random tag
     */
    public static Simulation.Tags randomTag() {
        int index = random.nextInt(numberOfTags);
        return Tags.class.getEnumConstants()[index];
    }

    public static void main(String[] args) {
    	gui = new GUI();
        userInteraction();

        printToGUI("Beginning Simulation...");
        Simulation simulation = new Simulation();
        simulation.setupConsumers(numberOfConsumers);
        simulation.setupProducers(numberOfProducers);
        simulation.setupDocuments(numberOfDocuments);

        simulation.printSetup();

        while (!simulation.quit) {
            User user = simulation.runIteration();
            user.act(simulation.getDocuments(), numberOfSearchResults);
        }

        for (final User user : simulation.getUsers()) {
            printToGUI(user.payoffHistory());
        }

        printToGUI("Simulation Terminated.");
    }

    /**
     * Obtains parameter values from users
     */
    private static void userInteraction() {
        Scanner in = new Scanner(System.in);
        boolean valid = false;

        printToGUI("Welcome to the Project Spartan simulation.");

        while (!valid) {
            try {
            	numberOfConsumers = gui.dialog("Please enter the number of consumers you would like the simulator to create (1-50): ");
                
                if (numberOfConsumers > 0 && numberOfConsumers <= MAX_CONSUMERS) {
                    printToGUI("The simulator will create " + numberOfConsumers + " consumers.");
                    valid = true;
                } else {
                    printToGUI("ERROR: Please enter a number in the range 1-" + MAX_CONSUMERS + "!");
                    numberOfConsumers = gui.dialog("Please enter the number of consumers you would like the simulator to create (1-50): ");
                }
            } catch (Exception e) {
                printToGUI("ERROR: Please only enter numeric characters!");
                numberOfConsumers = gui.dialog("Please enter the number of consumers you would like the simulator to create (1-50): ");
            }
        }
        valid = false;

        while (!valid) {
            try {
            	numberOfProducers = gui.dialog("Please enter the number of producers you would like the simulator to create (1-50): ");
                
                if (numberOfProducers > 0 && numberOfProducers <= MAX_PRODUCERS) {
                    printToGUI("The simulator will create " + numberOfProducers + " producers.");
                    valid = true;
                } else {
                    printToGUI("ERROR: Please enter a number in the range 1-" + MAX_PRODUCERS + "!");
                    numberOfProducers = gui.dialog("Please enter the number of producers you would like the simulator to create (1-50): ");
                }
            } catch (Exception e) {
                printToGUI("ERROR: Please only enter numeric characters!");
                numberOfProducers = gui.dialog("Please enter the number of producers you would like the simulator to create (1-50): ");
            }
        }
        valid = false;

        while (!valid) {
            try {
            	numberOfDocuments = gui.dialog("Please enter the number of documents you would like the simulator to create (1-10): ");
                
                if (numberOfDocuments > 0 && numberOfDocuments <= MAX_DOCUMENTS) {
                    printToGUI("The simulator will create " + numberOfDocuments + " documents.");
                    valid = true;
                } else {
                    printToGUI("ERROR: Please enter a number in the range 1-" + MAX_DOCUMENTS + "!");
                    numberOfDocuments = gui.dialog("Please enter the number of documents you would like the simulator to create (1-10): ");
                }
            } catch (Exception e) {
                printToGUI("ERROR: Please only enter numeric characters!");
                numberOfDocuments = gui.dialog("Please enter the number of documents you would like the simulator to create (1-10): ");
            }
        }
        valid = false;

        while (!valid) {
            try {
            	numberOfTags = gui.dialog("Please enter the number of tags you would like to use for the simulation (1-10): ");
                
                if (numberOfTags > 0 && numberOfTags <= MAX_TAGS) {
                    printToGUI("The simulator will use " + numberOfTags + " tags.");
                    valid = true;
                } else {
                    printToGUI("ERROR: Please enter a number in the range 1-" + MAX_TAGS + "!");
                    numberOfTags = gui.dialog("Please enter the number of tags you would like to use for the simulation (1-10): ");                    
                }
            } catch (Exception e) {
                printToGUI("ERROR: Please only enter numeric characters!");
                numberOfTags = gui.dialog("Please enter the number of tags you would like to use for the simulation (1-10): ");
            }
        }
    }

    public static void printToGUI(String s){
    	gui.print(s);
    }
    private void printSetup() {
        String toPrintConsumer = "The consumers which will be used are: \n";
        String toPrintProducer = "The producers which will be used are: \n";
        String toPrintDocument = "The documents which will be used are: \n";
        printToGUI("The tags which will be used are: ");
        for (int i = 0; i < numberOfTags; i++) {
            System.out.print(Tags.values()[i].name + " ");
        }
        printToGUI("");
        printToGUI("");

        for (final User user : users) {
            if (user instanceof Consumer) toPrintConsumer += user.getName() + " - " + user.getTaste() + ", ";
            if (user instanceof Producer) toPrintProducer += user.getName() + " - " + user.getTaste() + ", ";
        }

        for (final Document document : documents) {
            toPrintDocument += document.getName() + " - " + document.getTag() + ", ";
        }

        printToGUI(toPrintConsumer.substring(0, toPrintConsumer.length() - 2)); //format to get rid of extra trailing comma
        printToGUI(toPrintProducer.substring(0, toPrintProducer.length() - 2));
        printToGUI(toPrintDocument.substring(0, toPrintDocument.length() - 2));
        printToGUI("");
    }

    /**
     * Creates a specified number of consumers with a random taste
     *
     * @param number The amount of consumers to create
     */
    private void setupConsumers(int number) {
        printToGUI("Setting up Consumers: ");
        for (int i = 1; i < number + 1; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + String.valueOf(i), randomTag().toString());
            users.add(consumer);
            printToGUI(consumer.getName() + "  created");
        }
        printToGUI("-----------------------------");
    }

    /**
     * Creates a specified number of producers with a random taste
     *
     * @param number The amount of producers to create
     */
    private void setupProducers(int number) {
        printToGUI("Setting up Producers: ");
        for (int i = 1; i < number + 1; i++) {
            Producer producer = new Producer(this, "Producer #" + String.valueOf(i), randomTag().toString());
            users.add(producer);
            printToGUI(producer.getName() + "  created");
        }
        printToGUI("-----------------------------");
    }

    /**
     * Creates a specified number of documents with a random tag
     *
     * @param number The amount of documents to create
     */
    private void setupDocuments(int number) {
        printToGUI("Setting up Documents: ");
        for (int i = 1; i < number + 1; i++) {
            User user = users.get(random.nextInt(users.size()));
            while (user instanceof Consumer)
                user = users.get(random.nextInt(users.size())); //Keep picking a random user until it is a producer
            documents.add(((Producer) user).newDoc("Document #" + String.valueOf(i)));
        }
        printToGUI("-----------------------------");
    }

    private User runIteration() {
        boolean valid = false;
        User user = this.users.get(random.nextInt(this.users.size()));

        printToGUI("\n" + user.getName() + " was randomly selected to perform a search.");
        while (!valid) {
            try {
                numberOfSearchResults = gui.dialog("Please enter the number of documents you would like to search for (1-" + this.getNumberOfDocuments() + ") or 'q' to quit: ");
                
                if (numberOfSearchResults > numberOfDocuments) {
                    printToGUI("You have requested a higher number of search results than there are documents! Using max number of documents instead.");
                    numberOfSearchResults = numberOfDocuments;
                }
                printToGUI("The simulator will show " + numberOfSearchResults + " search results.");
                valid = true;
            } catch (Exception e) {
            	 printToGUI("ERROR: Please only enter numeric characters or 'q'!");
            	 numberOfSearchResults = gui.dialog("Please enter the number of documents you would like to search for (1-" + this.getNumberOfDocuments() + ") or 'q' to quit: ");
            }
        }

        return user;
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
    public enum Tags {
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

        public Tags getTags() {
            return this;
        }

    }
}
