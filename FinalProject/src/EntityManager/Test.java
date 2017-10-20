package EntityManager;

import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import POJO.PhoneRecord;
import POJO.PhoneRecord.ID;
import POJO.User;
import WindowClient.JsonHandler;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*UserEntityManager userManager=new UserEntityManager();
		userManager.userSignUp(new User("mina74", "0912", "mina", "kh", "user",3));
		
		Gson g=new Gson();*/
		
		PhoneRecord p=new PhoneRecord();
		
		ID id=p.new ID("mina", "alavi");
		
		p.setEmail("mina@gmail");
		p.setId(id);
		p.setMobileNumber("123");
		p.setPhoneNumber("8374");
		
		JsonHandler j=new JsonHandler();
		System.out.println(j.createJson(p));
		
		PhoneRecord p1=(PhoneRecord) j.createObject(j.createJson(p), PhoneRecord.class);
		System.out.println(p1.getId().getFamily());

	}

}
