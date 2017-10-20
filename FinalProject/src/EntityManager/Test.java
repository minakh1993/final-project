package EntityManager;

import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import POJO.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserEntityManager userManager=new UserEntityManager();
		userManager.userSignUp(new User("mina74", "0912", "mina", "kh", "user",3));
		
		Gson g=new Gson();

	}

}
