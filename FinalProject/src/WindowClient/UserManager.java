package WindowClient;

import POJO.User;
import UI.ClientAccessPanel;

/** this class is a singleton and for each user one of this class exist
*/
public class UserManager {
	
	private static String name="guest";
	private static int accessLevel=0;
	private static UserManager instance=new UserManager();
	
	//user Panael
	
	private UserManager(){
		
	}
	
	public static UserManager getUserManager(){
		return instance;
		
	}
	
	public static void signIn(User user){
		if(user!=null && user.getName()!=null){
		name=user.getName();
		accessLevel=user.getAccessLevel();
		//Opening a user Panel
		ClientAccessPanel.getPanel().setVisible(true);
		}
		
		
		
		
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		UserManager.name = name;
	}

	public static int getAccessLevel() {
		return accessLevel;
	}

	public static void setAccessLevel(int accessLevel) {
		UserManager.accessLevel = accessLevel;
	}
	
	
	
	
	
	
	

}
