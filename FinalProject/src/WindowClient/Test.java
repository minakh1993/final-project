package WindowClient;

import POJO.User;
import RequestResponseManager.SignInRequestResponseManager;
import RequestResponseManager.SignUpRequestResponseManager;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SignInRequestResponseManager rrh=new SignInRequestResponseManager();
		User user=new User();
		user.setUsername("haleh");
		user.setPassword("0356");
		
		rrh.SignIn("localhost", user);
		
		
		SignUpRequestResponseManager srr=new SignUpRequestResponseManager();
		User usersignup=new User("ahmad654", "0912", "ahmad", "ut", "user", 0);
		srr.SignUp("localhost", usersignup);
		
		
		
	}

}
