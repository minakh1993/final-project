package EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import POJO.User;
import Security.SecurityMD5;

public class UserManager {

	SessionFactory factory=ProjectConnection.getConnection();

	public User userValidation(User user){
		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();
		User validatedUser=null;

		try{
			validatedUser = session.get(User.class, user.getUsername());
			//checking the password
			SecurityMD5 secure=new SecurityMD5();
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
		user.setPassword(md5Pass);
		
		session.save(user);
		t.commit();
		return true;
		}catch(Exception e){
			e.getMessage();
			e.printStackTrace();
			return false;
		}

	}
}
