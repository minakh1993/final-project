package EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import POJO.User;
import Security.SecurityMD5;

public class UserEntityManager {

	SessionFactory factory=ProjectConnection.getConnection();

	public User userValidation(User user){
		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();
		User validatedUser=null;

		try{
			System.out.println(user.getPassword());
			validatedUser = session.get(User.class, user.getUsername());
			System.out.println(validatedUser.getPassword());
			//checking the password
			SecurityMD5 secure=new SecurityMD5();
			System.out.println(secure.md5(user.getPassword()));
			if(validatedUser.getPassword().equals(secure.md5(user.getPassword()))){
				//means the username and password is right
				return validatedUser;
			}else{
				
				return null;
			}

		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
			return null;
		}finally{
			session.close();
		}


	}


	public boolean userSignUp(User user){

		try{
		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();
		
		//making the password secure
		SecurityMD5 secure=new SecurityMD5();
		String pass=user.getPassword();
		String md5Pass=secure.md5(pass);
		//making a new user
		User finalUser=new User(user.getUsername(),md5Pass,user.getName(),user.getFamily(),user.getRole(),user.getAccessLevel());
		session.save(finalUser);
		t.commit();
		return true;
		}catch(Exception e){
			e.getMessage();
			e.printStackTrace();
			return false;
		}

	}
}
