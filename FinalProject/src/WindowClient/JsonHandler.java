package WindowClient;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.*;

import POJO.Event;
import POJO.PhoneRecord;
import POJO.User;
import jersey.repackaged.org.objectweb.asm.TypeReference;

public class JsonHandler {
	
	
	
	public String createJson(Object object){
		
		Gson gson=new Gson();
		String result = gson.toJson(object);
		return result;
	}
	
	
	
	public Object createObject(String input, Class c){
		
		Gson gson=new Gson();
		Object obj= gson.fromJson(input, c);
		return obj;
		
		
	}
	
	public List<PhoneRecord> JsonToPhoneList(String input){
		Gson gson=new Gson();
		Type type = new TypeToken<List<PhoneRecord>>(){}.getType();
		List<PhoneRecord> list =gson.fromJson(input,type);
		return list;
	}



	public List<User> JsonToUserList(String input) {
		Gson gson=new Gson();
		Type type = new TypeToken<List<User>>(){}.getType();
		List<User> list =gson.fromJson(input,type);
		return list;
	}
	
	public List<Event> JsonToEventList(String input) {
		Gson gson=new Gson();
		Type type = new TypeToken<List<Event>>(){}.getType();
		List<Event> list =gson.fromJson(input,type);
		return list;
	}

}
