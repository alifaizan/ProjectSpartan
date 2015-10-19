import java.util.*;
public class Consumer extends User {
	
	List<User> following;
	
	
	public Consumer(String name, String taste){
		super(name, taste);
		following = new ArrayList<User>();
		
	}
	
	public void follow(User user){
		following.add(user);
	}
	
	
	
	public List<User> getFollowing(){
		return following;
	}
}
