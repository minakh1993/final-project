package RequestResponseManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import POJO.PhoneRecord;
import WindowClient.JsonHandler;

public class ShowAllContactsRequestResponseManager extends RequestResponseSetting {
	
	String showAllContactsPath="/showAllContacts";	
	
	public List<PhoneRecord> showAllContacts(){
		try {

			HttpURLConnection conn = connectionProperties();
			if (conn == null) {
				return null;
			}
			
			if (conn.getResponseCode() < 200 && conn.getResponseCode()>=300) {
				System.out.println(conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String result="";

			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				result+=output;
			}
			
			conn.disconnect();
			System.out.println(result);
			
			//the result is a list from objects in form of json String
			JsonHandler jsonHandler=new JsonHandler();
			return (List<PhoneRecord>) jsonHandler.JsonToPhoneList(result);
			
			
			
			

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	
	
	
private HttpURLConnection connectionProperties() {
		

		try {
			URL url = new URL(constant + IP + ":" + port + "/" + sharedPath + showAllContactsPath);
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			

			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
