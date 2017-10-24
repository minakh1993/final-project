package POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phoneBook")
public class PhoneRecord implements Serializable {

	
	@EmbeddedId
	ID id;

	@Column(name = "mobile", length = 20)
	private String mobileNumber;

	@Column(name = "phoneNumber", length = 20)
	private String phoneNumber;

	@Column(name = "email", length = 30)
	private String email;

	
	
	

	public PhoneRecord(ID id, String mobileNumber, String phoneNumber, String email) {
		super();
		this.id = new ID(id.name, id.family);
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public PhoneRecord() {
		super();
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

		

}
