package WindowClient;

/** this class is a singleton and for each user one of this class exist
*/
public class UserManager {
	
	private String name="guest";
	private int accessLevel=0;
	private static UserManager instance=new UserManager();
	
	private UserManager(){
		
	}
	
	public static UserManager getUserManager(){
		return instance;
		
	}
	
	//log in
	public void userLogin(String name, int accessLevel){
		this.name=name;
		this.accessLevel=accessLevel;
		
	}
	
	
	
	
	
	

}
