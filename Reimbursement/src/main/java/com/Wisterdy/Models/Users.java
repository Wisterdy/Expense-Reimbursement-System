package com.Wisterdy.Models;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;





//create role fist then create user since it will contain the foreign key constraint the other table
//must first exists
@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="UserID" )
	private Integer user_id;
	@Column(name ="UserName", length = 50 , unique = true)
	private String  user_name;
	@Column(name ="UserPassword" ,length = 50)
	private String  UserPassword; 
	@Column(name="Firstname" , length = 100)
	private String  firstName;
	@Column(name="Lastname" , length = 100)
	private String  LastName;
	@Column(name ="Email", length = 150 , unique = true)
	private String  Email;
	
	
	//One user can have many role;
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole Role;
	public Users() {
		super();
	}

public Users( String username,  String UserPassword, String firstName, String LastName, String Email) {
		
		this.user_name=username;
		this.UserPassword=UserPassword;
		this.firstName=firstName;
		this.LastName=LastName;
		this.Email=Email;
		//UserRole myrole= new UserRole(1, "ename");
		
	}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Email == null) ? 0 : Email.hashCode());
	result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
	result = prime * result + ((Role == null) ? 0 : Role.hashCode());
	result = prime * result + ((UserPassword == null) ? 0 : UserPassword.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
	result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
	Users other = (Users) obj;
	if (Email == null) {
		if (other.Email != null)
			return false;
	} else if (!Email.equals(other.Email))
		return false;
	if (LastName == null) {
		if (other.LastName != null)
			return false;
	} else if (!LastName.equals(other.LastName))
		return false;
	if (Role == null) {
		if (other.Role != null)
			return false;
	} else if (!Role.equals(other.Role))
		return false;
	if (UserPassword == null) {
		if (other.UserPassword != null)
			return false;
	} else if (!UserPassword.equals(other.UserPassword))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (user_id == null) {
		if (other.user_id != null)
			return false;
	} else if (!user_id.equals(other.user_id))
		return false;
	if (user_name == null) {
		if (other.user_name != null)
			return false;
	} else if (!user_name.equals(other.user_name))
		return false;
	return true;
}

public Integer getUser_id() {
	return user_id;
}

public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}

public String getUser_name() {
	return user_name;
}

public void setUser_name(String user_name) {
	this.user_name = user_name;
}

public String getUserPassword() {
	return UserPassword;
}

public void setUserPassword(String userPassword) {
	UserPassword = userPassword;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return LastName;
}

public void setLastName(String lastName) {
	LastName = lastName;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public UserRole getRole() {
	return Role;
}

public void setRole(UserRole role) {
	Role = role;
}

@Override
public String toString() {
	return "Users [user_id=" + user_id + ", user_name=" + user_name + ", UserPassword=" + UserPassword + ", firstName="
			+ firstName + ", LastName=" + LastName + ", Email=" + Email + ", Role=" + Role + "]";
}



}
