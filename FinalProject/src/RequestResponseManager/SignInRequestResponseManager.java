package RequestResponseManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import POJO.User;
import WindowClient.JsonHandler;
import WindowClient.UserManager;

public class SignInRequestResponseManager extends RequestResponseSetting {

	private String loginPath = "";

	public void SignIn(String IP, User user) {
		try {

			HttpURLConnection conn = connectionProperties(IP);
			if (conn == null) {
				return;
			}

			// changing user to json type
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
			//informing user Manager
			informUserManager(result);

			conn.disconnect();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void informUserManager(String output) {
		
		//changing String to Object
		System.out.println(output);
		JsonHandler jsonHandler=new JsonHandler();
		User user=(User) jsonHandler.createObject(output, User.class);
		
		//informing the userManager of this client
		UserManager.signIn(user);
		
		
		
		
	}

	private HttpURLConnection connectionProperties(String IP) {
		super.IP = IP;

		try {
			URL url = new URL(constant + super.IP + ":" + port + "/" + sharedPath + loginPath);
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");

			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
