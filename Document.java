//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

public class Document {
   
	
   private String tag;
   private int documentCounter = 0;
   private int documentIDCount;
   
   //Constructor for Document that takes in a String
   //Increments documentCounter and documentIDCount everytime a document is created
    public Document(String t){
    	
       this.tag = t;
       this.documentCounter+=1;
       this.documentIDCount=documentCounter;
    }

    
    //Returns the tag of the document
    public String getDocumentTag(){
        
        return this.tag;
    }
    
    
    //Returns the id of the document
    public int getDocumentId(){
    
    	return this.documentIDCount;
    
    }
    
   
   
}
