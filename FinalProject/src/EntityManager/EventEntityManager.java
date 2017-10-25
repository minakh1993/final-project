package EntityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import POJO.Event;
import POJO.User;

public class EventEntityManager {

	SessionFactory factory = ProjectConnection.getConnection();

	public void saveEvent(String description, User user) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Date date = new Date();// get current time
		
		Event event = new Event(0, user, description, date);
		session.save(event);
		t.commit();
		session.close();

	}
	
	public List<Event> showAllEvents(){
		try{
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		
		List<Event> list=(List<Event>)session.createQuery("from Event").list();
		t.commit();
		
		session.close();
		
		return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
