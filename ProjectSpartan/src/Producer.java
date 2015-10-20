//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;

public class Producer extends User{
	
	List<User> followers;

	public Producer(Simulation simulation, String name, String taste) {
		super(simulation, name, taste);
		followers = new ArrayList<>();
	}

	public List<Document> act(List<Document> documentList) {
		return new ArrayList<>();
	}

	public void addfollower(User user){
		followers.add(user);
	}
	
	public List<User> getFollowers(){
		return followers;
	}
}
