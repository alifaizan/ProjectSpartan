//Name: Ali Faizan
//Student Number: 10095765
//Date: October 19,2015
//Version #: 1

public class Document {
   
	
   private String tag;
   private int documentCounter = 0;
   private int documentIDCount;
   private int popularity;
   
   //Constructor for Document that takes in a String
   //Increments documentCounter and documentIDCount everytime a document is created
    public Document(String tag){
    	
       this.tag = tag;
       documentCounter+=1;
       documentIDCount=documentCounter;
       popularity = 0;
    }

    
    //Returns the tag of the document
    public String getDocumentTag(){
        
        return this.tag;
    }
    
    
    //Returns the id of the document
    public int getDocumentId(){
    
    	return this.documentIDCount;
    
    }
    
    public static void main(String[] args){
    	Document d1 = new Document("Test");
    	Document d2 = new Document("Test2");
    	System.out.println(d1.getDocumentId());
    	System.out.println(d2.getDocumentId());
    	
    }
   
}