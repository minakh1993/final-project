package RequestResponseManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import POJO.PhoneRecord;
import POJO.User;
import WindowClient.JsonHandler;

public class UpdateUserRequestResponseManager extends RequestResponseSetting {
	

	


	String updateUsertPath="/updateUser";
	
	
	public boolean updateUser(User user){
		try {

			HttpURLConnection conn = connectionProperties();
			if (conn == null) {
				return false;
			}

			// converting ID to json
			JsonHandler j = new JsonHandler();
			String requestBody = j.createJson(user);
			System.out.println(requestBody);

			OutputStream os = conn.getOutputStream();
			os.write(requestBody.getBytes());
			os.flush();

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
			if(result.equals("success")){
				return true;
			}else{
				return false;
			}
			
			

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
private HttpURLConnection connectionProperties() {
		

		try {
			URL url = new URL(constant + IP + ":" + port + "/" + sharedPath + updateUsertPath);
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			

			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	
	




}
