package com.Wisterdy.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
		
	private Integer UserRoleID;
	
	@Column(name= "UserRole", length = 10)
	private String UserRole;
	
	
	public UserRole() {
		super();
		
	}

	
	public UserRole( String UserRole)
	{
		this.UserRoleID=UserRoleID;
		this.UserRole=UserRole;
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserRole == null) ? 0 : UserRole.hashCode());
		result = prime * result + ((UserRoleID == null) ? 0 : UserRoleID.hashCode());
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
		UserRole other = (UserRole) obj;
		if (UserRole == null) {
			if (other.UserRole != null)
				return false;
		} else if (!UserRole.equals(other.UserRole))
			return false;
		if (UserRoleID == null) {
			if (other.UserRoleID != null)
				return false;
		} else if (!UserRoleID.equals(other.UserRoleID))
			return false;
		return true;
	}


	public Integer getUserRoleID() {
		return UserRoleID;
	}


	public void setUserRoleID(Integer userRoleID) {
		UserRoleID = userRoleID;
	}


	public String getUserRole() {
		return UserRole;
	}


	public void setUserRole(String userRole) {
		UserRole = userRole;
	}


	@Override
	public String toString() {
		return "UserRole [UserRoleID=" + UserRoleID + ", UserRole=" + UserRole + "]";
	}
	

	
}
