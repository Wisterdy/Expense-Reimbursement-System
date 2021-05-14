package com.Wisterdy.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="reimb_status")
public class Status {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column( name = "reimb_status_id")
	Integer  Reimb_Status_ID;
	
	@Column( name = "reimb_status", length = 10)
	String Reimb_Status;
	public Status() {
		super();
	}
	
	public Status (String Reimb_Status)
	{
		this.Reimb_Status= Reimb_Status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Reimb_Status == null) ? 0 : Reimb_Status.hashCode());
		result = prime * result + ((Reimb_Status_ID == null) ? 0 : Reimb_Status_ID.hashCode());
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
		Status other = (Status) obj;
		if (Reimb_Status == null) {
			if (other.Reimb_Status != null)
				return false;
		} else if (!Reimb_Status.equals(other.Reimb_Status))
			return false;
		if (Reimb_Status_ID == null) {
			if (other.Reimb_Status_ID != null)
				return false;
		} else if (!Reimb_Status_ID.equals(other.Reimb_Status_ID))
			return false;
		return true;
	}

	public Integer getReimb_Status_ID() {
		return Reimb_Status_ID;
	}

	public void setReimb_Status_ID(Integer reimb_Status_ID) {
		Reimb_Status_ID = reimb_Status_ID;
	}

	public String getReimb_Status() {
		return Reimb_Status;
	}

	public void setReimb_Status(String reimb_Status) {
		Reimb_Status = reimb_Status;
	}

	@Override
	public String toString() {
		return "Status [Reimb_Status_ID=" + Reimb_Status_ID + ", Reimb_Status=" + Reimb_Status + "]";
	}

	
}
