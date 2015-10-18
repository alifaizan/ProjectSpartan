//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

import java.util.*;

public class User {
    
    private ArrayList<Document> liked;
    private Simulation sim;
    private String userTaste;
    private int userIDCount;
    private int userCount=0;
  
    
    //Constructor for User that is passed "sim" of type Simulation 
    //and a "str" of type String such as the users name
    //Increments userCount and userIDCount everytime a new user is created
    public User(Simulation sim, String str){
    	
     this.sim= sim;
     this.userTaste = str;
     this.liked = new ArrayList<Document>();
     this.userCount+=1;
     this.userIDCount=userCount;
    }

    //Allows the users to find the top documents and likes it based on users taste
    public void act(){
    	
        for(Document doc: sim.search()){
        	
            if(doc.getDocumentTag()== userTaste){
            	
            	like(doc);
            }
        }
    
    }
    
    //Returns the Id of the user
    public int getUserId(){
    
    	return this.userIDCount;
    }
    
    
    //Returns the taste of the user
    public String getUserTaste(){
    	
    	return this.userTaste;
    }
    
   
    //Adds a document that is liked to the liked ArrayList
    public void like(Document doc){
    
    	liked.add(doc);
    }
    
   
    //Return true if the document passed matches the document in the liked ArrayList
    //Returns false otherwise
    public boolean likes(Document doc){
        
    	for(Document userDocument: liked){
            
        	return userDocument == doc;
        }
        return false;
    
    }
    
}