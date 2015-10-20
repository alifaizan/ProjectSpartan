//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.*;

public class Producer extends User{
	
	private List<User> followers;
	private List<Document> created;
	
	public Producer(Simulation simulation, String name, String taste) {
		super(simulation, name, taste);
		followers = new ArrayList<User>();
		created = new ArrayList<Document>();
	}

	public List<Document> act(List<Document> documentList) {
		return new ArrayList<Document>();
	}

	public void addfollower(User user){
		followers.add(user);
	}
	
	public List<User> getFollowers(){
		return followers;
	}
	
	public Document newDoc(String name, String tag){
		Document d = new Document(name, tag, this);
		created.add(d);
		return d;
	}
	
	public void updatePayoff(){
		for(Document doc : created){
			if(doc.getLikedBy().size() > 0){
				payoff++;
			}
		}
	}
}
