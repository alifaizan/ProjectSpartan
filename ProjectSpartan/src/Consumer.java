//Name: Yash Patel
//Student Number: 100943654
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;

public class Consumer extends User {
	
	List<User> following;

	public Consumer(Simulation simulation, String name, String taste) {
		super(simulation, name, taste);
		following = new ArrayList<>();
	}
	
	public void follow(User user){
		following.add(user);
		System.out.println(super.getName() + " followed " + user.getName());
	}

	public void act() {
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
}
