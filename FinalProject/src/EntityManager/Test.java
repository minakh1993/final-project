package EntityManager;

import org.hibernate.SessionFactory;

import POJO.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager userManager=new UserManager();
		userManager.userSignUp(new User("mina74", "0912", "mina", "kh", "user",3));
		
		

	}

}
