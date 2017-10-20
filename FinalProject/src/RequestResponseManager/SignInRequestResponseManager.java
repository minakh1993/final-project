package RequestResponseManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import POJO.User;
import WindowClient.JsonHandler;

public class SignInRequestResponseManager extends RequestResponseSetting {

	private String loginPath = "";

	public void logIn(String IP, User user) {
		try {

			HttpURLConnection conn = connectionProperties();
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

			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private HttpURLConnection connectionProperties() {
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
