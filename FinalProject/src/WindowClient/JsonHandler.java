package WindowClient;

import com.google.gson.Gson;

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

}
