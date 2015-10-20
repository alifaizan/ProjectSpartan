//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.*;

public class Consumer extends User {
	
	private List<User> following;

	public Consumer(Simulation simulation, String name, String taste) {
		super(simulation, name, taste);
		following = new ArrayList<User>();
	}
	
	public void follow(User user){
		following.add(user);
		System.out.println(super.getName() + " followed " + user.getName());
	}

	public List<Document> act(List<Document> documentList) {
		return new ArrayList<Document>();
	}
	
	public List<User> getFollowing(){
		return following;
	}

	private void followUser(User user) {
		if (!this.getFollowing().contains(user)) {
			this.following.add(user);
			this.setAmountFollowed(this.getAmountFollowed() + 1);
		}
	}
	
	public void updatePayoff(Document doc){
		if(doc.getTag() == super.getTaste() && !(super.likes(doc))){
			payoff++;
		}
	}
}