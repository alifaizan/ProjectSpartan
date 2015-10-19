import java.util.ArrayList;
import java.util.List;


public class Producer extends User{
	
	List<User> followers;
	public Producer(String name, String taste){
		super(name, taste);
		followers = new ArrayList<User>();
	}
	
	public void addfollower(User user){
		followers.add(user);
	}
	
	public List<User> getFollowers(){
		return followers;
	}
}
