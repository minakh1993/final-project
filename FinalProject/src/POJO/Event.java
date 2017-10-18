package POJO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class Event implements Serializable{

	@Id
	@Column(name="code", nullable=false)
	private int code;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user", insertable = false)
	private User user;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private Date date;

	public Event(int code, User user, String description, Date date) {
		super();
		this.code = code;
		this.user = user;
		this.description = description;
		this.date = date;
	}

	public Event() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
}
