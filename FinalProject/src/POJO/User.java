package POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import Security.SecurityMD5;

@Entity
@Table(name="user")
public class User implements Serializable{
	

	@Id
	@Column(name="username", nullable=false , length=20)
	private String username;
	
	@Column(name="password", nullable=false, length=100)
	private String password;
	
	@Column(name="name", nullable=false , length=30)
	private String name;
	
	@Column(name="family", nullable=false, length=50)
	private String family;
	
	@Column(name="role", nullable=false, length=50)
	private String role="user";
	
	@Column(name="accessLevel", nullable=false, length=4)
	private int accessLevel=3; //default value

	public User(String username, String password, String name, String family, String role, int accessLevel) {
		super();
		this.username = username;
		
		SecurityMD5 secure=new SecurityMD5();
		//secure the password
		this.password =secure.md5(password);
		this.name = name;
		this.family = family;
		this.role = role;
		this.accessLevel = accessLevel;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	
	
}
