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
	private int mobileNumber;

	@Column(name = "phoneNumber", length = 20)
	private int phoneNumber;

	@Column(name = "email", length = 30)
	private String Email;

	
	
	

	public PhoneRecord(ID id, int mobileNumber, int phoneNumber, String email) {
		super();
		this.id = new ID(id.name, id.family);
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		Email = email;
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

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Embeddable
	public class ID implements Serializable {

		@Column(name = "name", nullable = false, length = 20)
		protected String name;

		@Column(name = "family", nullable = false, length = 30)
		protected String family;

		public ID(String name, String family) {
			super();
			this.name = name;
			this.family = family;
		}

		public ID() {
			super();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((family == null) ? 0 : family.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ID other = (ID) obj;

			if (family == null) {
				if (other.family != null)
					return false;
			} else if (!family.equals(other.family))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private PhoneRecord getOuterType() {
			return PhoneRecord.this;
		}

	}

}
