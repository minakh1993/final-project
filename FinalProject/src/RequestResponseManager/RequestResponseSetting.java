package RequestResponseManager;

public  class RequestResponseSetting {
	
	protected static String constant="http://";
	protected static String IP="localhost";
	protected static String port="8080";
	protected static String sharedPath="FinalProject/phoneBook/user";  //shared path between different requests
	
	
	public RequestResponseSetting() {
		super();
	}


	public String getIP() {
		return IP;
	}


	public void setIP(String iP) {
		IP = iP;
	}
	
	
	
	

}
