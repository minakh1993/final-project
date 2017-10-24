package EntityManager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import POJO.PhoneRecord;
import POJO.User;
import Security.SecurityMD5;

public class UserEntityManager {

	SessionFactory factory = ProjectConnection.getConnection();

	public User userValidation(User user) {
		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();
		User validatedUser = null;

		try {
			System.out.println(user.getPassword());
			validatedUser = session.get(User.class, user.getUsername());

			// checking the password
			SecurityMD5 secure = new SecurityMD5();

			if (validatedUser.getPassword().equals(secure.md5(user.getPassword()))) {
				// means the username and password is right
				return validatedUser;
			} else {

				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return null;
		} finally {
			session.close();
		}

	}

	public boolean userSignUp(User user) {

		try {
			// creating session object
			Session session = factory.openSession();

			// creating transaction object
			Transaction t = session.beginTransaction();

			// making the password secure
			SecurityMD5 secure = new SecurityMD5();
			String pass = user.getPassword();
			String md5Pass = secure.md5(pass);
			System.out.println(md5Pass);
			// making a new user
			User finalUser = new User(user.getUsername(), md5Pass, user.getName(), user.getFamily(), user.getRole(),
					user.getAccessLevel());
			session.save(finalUser);
			t.commit();
			return true;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			return false;
		}

	}

	public List<User> showAllUsers() {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		List<User> list = session.createQuery("from User", User.class).list();
		t.commit();

		session.close();
		return list;

	}

	public boolean updateUser(User user) {

		Session session = factory.openSession();
		try {
			Transaction t = session.beginTransaction();
			session.update(user);
			t.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

	}
	
	public boolean deleteUser(String username){
		
		User user=new User(username," "," "," "," ",1);
		
		Session session = factory.openSession();
		try {
			Transaction t = session.beginTransaction();
			session.delete(user);
			t.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
		
	}

}
