package EntityManager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import POJO.ID;
import POJO.PhoneRecord;

public class PhoneNumberEntityManager {

	SessionFactory factory = ProjectConnection.getConnection();

	public boolean addContact(PhoneRecord contact) {
		Session session = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(contact);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();

		}

	}


	public List<PhoneRecord> showAllContacts(){
		Session session = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			List<PhoneRecord> phoneBook=(List<PhoneRecord>) session.createQuery("from PhoneRecord",PhoneRecord.class).list();
			System.out.println(phoneBook.size());
			transaction.commit();
			return phoneBook;


		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

	}

	public boolean  deleteContact(ID id){
		Session session=null;
		try{
			session=factory.openSession();
			Transaction t=session.beginTransaction();
			PhoneRecord phoneRecord=new PhoneRecord();
			phoneRecord.setId(id);

			session.delete(phoneRecord);
			t.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}

	}

	public boolean updateContact(PhoneRecord phoneRecord){
		Session session=null;
		try{
			session=factory.openSession();
			Transaction t=session.beginTransaction();

			session.update(phoneRecord);

			t.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}

	}
}


