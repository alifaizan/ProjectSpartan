package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private static final SecureRandom random = new SecureRandom();
    public boolean stopPressed;
    private int numberOfConsumers, numberOfProducers, numberOfDocuments, numberOfSearchResults, numberOfTags;
    private List<Document> documents;
    private List<User> users;
    private List<String> consumerNames, producerNames;
    private User lastActed;

    /**
     * Default constructor for Simulation class
     */
    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
        consumerNames = new ArrayList<>();
        producerNames = new ArrayList<>();
        stopPressed = false;
    }

    public static void printToGUI(String s) {
        //gui.print(s);
    }
    
    /**
     * Randomly selects a tag from the enumerated class
     *
     * @return A random tag
     */
    public Simulation.Tags randomTag() {
        int index = random.nextInt(numberOfTags);
        return Tags.class.getEnumConstants()[index];
    }

    public String runSim() {
        String toReturn = "";

        toReturn += setupConsumers(numberOfConsumers);
        toReturn += setupProducers(numberOfProducers);
        toReturn += setupDocuments(numberOfDocuments);
        String[] userArr = new String[consumerNames.size()];
        consumerNames.toArray(userArr);
        userArr = new String[producerNames.size()];
        producerNames.toArray(userArr);
        toReturn += printSetup();

        return toReturn;
    }

    public void terminate() {
        for (final User user : getUsers()) {
            printToGUI(user.payoffHistory());
        }

        printToGUI("Simulation Terminated.");
    }

    private String printSetup() {
        String toReturn = "";
        toReturn += "The consumers which will be used are: \n";
        toReturn += "The producers which will be used are: \n";
        toReturn += "The documents which will be used are: \n";
        toReturn += "The tags which will be used are: ";
        for (int i = 0; i < numberOfTags; i++) {
            printToGUI(Tags.values()[i].name + " ");
        }

        toReturn += "\n";

        for (final User user : users) {
            if (user instanceof Consumer) toReturn += user.getName() + " - " + user.getTaste() + ", ";
            if (user instanceof Producer) toReturn += user.getName() + " - " + user.getTaste() + ", ";
        }

        for (final Document document : documents) {
            toReturn += document.getName() + " - " + document.getTag() + ", ";
        }

        return toReturn;
    }

    /**
     * Creates a specified number of consumers with a random taste
     *
     * @param number The amount of consumers to create
     */
    private String setupConsumers(int number) {
        String toReturn = "";
        toReturn += ("Setting up Consumers: \n");
        for (int i = 1; i < number + 1; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + String.valueOf(i), randomTag().toString());
            users.add(consumer);
            consumerNames.add(consumer.getName());
            toReturn += (consumer.getName() + "  created\n");
        }
        toReturn += ("-----------------------------\n");

        return toReturn;
    }

    /**
     * Creates a specified number of producers with a random taste
     *
     * @param number The amount of producers to create
     */
    private String setupProducers(int number) {
        String toReturn = "";
        toReturn += ("Setting up Producers: \n");
        for (int i = 1; i < number + 1; i++) {
            Producer producer = new Producer(this, "Producer #" + String.valueOf(i), randomTag().toString());
            users.add(producer);
            producerNames.add(producer.getName());
            toReturn += (producer.getName() + "  created\n");
        }
        toReturn += ("-----------------------------\n");

        return toReturn;
    }

    /**
     * Creates a specified number of documents with a random tag
     *
     * @param number The amount of documents to create
     */
    private String setupDocuments(int number) {
        String toReturn = "";
        toReturn += ("Setting up Documents: ");
        for (int i = 1; i < number + 1; i++) {
            User user = users.get(random.nextInt(users.size()));
            while (user instanceof Consumer)
                user = users.get(random.nextInt(users.size())); //Keep picking a random user until it is a producer
            documents.add(((Producer) user).newDoc("Document #" + String.valueOf(i)));
            toReturn += (user.getToPrint() + "\n");
        }
        toReturn += ("-----------------------------\n");

        return toReturn;
    }

    public String search() {
        String toReturn = "";
        boolean valid = false;
        User user = this.users.get(random.nextInt(this.users.size()));

        toReturn += ("\n" + user.getName() + " was randomly selected to perform a search.\n");

        user.act(getDocuments(), numberOfSearchResults);
        for (String s : user.getPrintStrings()) {
            toReturn += (s + "\n");
        }

        return toReturn;
    }

    public int getHowManyDocuments() {
        return this.getDocuments().size();
    }

    public int getNumberOfDocuments() {
        return numberOfDocuments;
    }

    //-----Getters and Setters------

    public void setNumberOfDocuments(int numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public List<User> getUsers() {
        return this.users;
    }
    public List<String> getConsumerNames(){
    	return this.consumerNames;
    }
    
    public List<String> getProducerNames(){
    	return this.producerNames;
    }

    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }

    public void setNumberOfConsumers(int numberOfConsumers) {
        this.numberOfConsumers = numberOfConsumers;
    }

    public int getNumberOfProducers() {
        return numberOfProducers;
    }

    public void setNumberOfProducers(int numberOfProducers) {
        this.numberOfProducers = numberOfProducers;
    }

    public int getNumberOfSearchResults() {
        return numberOfSearchResults;
    }

    public void setNumberOfSearchResults(int numberOfSearchResults) {
        this.numberOfSearchResults = numberOfSearchResults;
    }

    public int getNumberOfTags() {
        return numberOfTags;
    }

    public void setNumberOfTags(int num) {
        this.numberOfTags = num;
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
