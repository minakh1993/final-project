package Server;

public class EventDescriptionBuilder {
	
	private String eventType;
	private String IP;
	private String result;
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public EventDescriptionBuilder(String eventType, String iP, String result) {
		super();
		this.eventType = eventType;
		IP = iP;
		this.result = result;
	}
	
	@Override
	public String toString(){
		String event="Event type: "+eventType+" IP: "+IP+" result: "+result;
		return event;
		
	}
	

}
