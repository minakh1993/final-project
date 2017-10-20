package RequestResponseManager;

public abstract class RequestResponseSetting {
	
	protected String constant="http://";
	protected String IP;
	protected String port="8080";
	protected String sharedPath="FinalProject/phoneBook/user";  //shared path between different requests
	
	
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
