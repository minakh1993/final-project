package WindowClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import POJO.ID;
import POJO.PhoneRecord;
import POJO.User;
import RequestResponseManager.DeleteContactRequestResponseManager;
import RequestResponseManager.ShowAllContactsRequestResponseManager;
import RequestResponseManager.ShowAllUserRequestResponseManager;
import RequestResponseManager.SignInRequestResponseManager;
import RequestResponseManager.SignUpRequestResponseManager;
import RequestResponseManager.UpdateContactRequestResponseManager;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*SignInRequestResponseManager rrh=new SignInRequestResponseManager();
		User user=new User();
		user.setUsername("haleh");
		user.setPassword("0356");
		
		rrh.SignIn("localhost", user);
		
		
		SignUpRequestResponseManager srr=new SignUpRequestResponseManager();
		User usersignup=new User("ahmad654", "0912", "ahmad", "ut", "user", 0);
		srr.SignUp("localhost", usersignup);
		*/
		
		/*ShowAllContactsRequestResponseManager s=new ShowAllContactsRequestResponseManager();
		System.out.println(s.showAllContacts().get(1).getEmail());*/
		
		
		/*UpdateContactRequestResponseManager u=new UpdateContactRequestResponseManager();
		PhoneRecord phoneRecord=new PhoneRecord();
		phoneRecord.setId(new ID("mahya", "salehi"));
		phoneRecord.setEmail("test@gmail");
		phoneRecord.setMobileNumber("3884884");
		phoneRecord.setPhoneNumber("938843");
		
		System.out.println(u.updateContact(phoneRecord));
		
		DeleteContactRequestResponseManager d=new DeleteContactRequestResponseManager();
		d.deleteContact(new ID("mahya","salehi"));
		*/
		/*
		ShowAllUserRequestResponseManager s=new ShowAllUserRequestResponseManager();
		System.out.println(s.showAllUsers().get(0).getClass());*/
		
		
		Map <String , String> test=new HashMap<String, String>();
		test.put("r", "t");
		String o=test.get("s");System.out.println(o);
		Date date=new Date();
		System.out.println(date);
	}
	

}
