package com.Wisterdy.Models;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bytebuddy.asm.Advice.This;

@Entity
@Table(name ="reimburse_model")
public class ReimburseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	Integer REIMB_ID;
	
	@Column(name = "reimb_amount")
	Integer REIMB_Amount;
	@Column(name = "reimb_submitted")
	Date REIMB__SUBMITTED;
	@Column(name = "reimb_resolved")
	Date REIMB_RESOLVED;
	@Column(name = "reimb_receipt")
	Blob  REIMB_RECEIPT;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "reimb_author")
	Users REIMB_Author; // First and Last of user who posted submission user ID
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reimb_resolver")
    Users REIMB_RESOLVER; // User ID who have who authority to accept submission
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reimb_status_id")
	Status REIMB_STATUS_ID; //
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reimb_type_id")
	Type REIMB_Type_ID;
	
	
	public ReimburseModel() {
		super();
	}
	
	
	public ReimburseModel(Integer REIMB_Amount, Date REIMB__SUBMITTE, Date REIMB_RESOLVED, Blob  REIMB_RECEIPT, Users Reimb_Author) {
		
	
	this.REIMB_Amount=REIMB_Amount;
	this.REIMB__SUBMITTED=REIMB__SUBMITTE;
	this.REIMB_RESOLVED= REIMB_RESOLVED;
	this.REIMB_RECEIPT=REIMB_RECEIPT;
    this.REIMB_Author=Reimb_Author;
	
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((REIMB_Amount == null) ? 0 : REIMB_Amount.hashCode());
		result = prime * result + ((REIMB_Author == null) ? 0 : REIMB_Author.hashCode());
		result = prime * result + ((REIMB_ID == null) ? 0 : REIMB_ID.hashCode());
		result = prime * result + ((REIMB_RESOLVED == null) ? 0 : REIMB_RESOLVED.hashCode());
		result = prime * result + ((REIMB_RESOLVER == null) ? 0 : REIMB_RESOLVER.hashCode());
		result = prime * result + ((REIMB_STATUS_ID == null) ? 0 : REIMB_STATUS_ID.hashCode());
		result = prime * result + ((REIMB_Type_ID == null) ? 0 : REIMB_Type_ID.hashCode());
		result = prime * result + ((REIMB__SUBMITTED == null) ? 0 : REIMB__SUBMITTED.hashCode());
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
		ReimburseModel other = (ReimburseModel) obj;
		if (REIMB_Amount == null) {
			if (other.REIMB_Amount != null)
				return false;
		} else if (!REIMB_Amount.equals(other.REIMB_Amount))
			return false;
		if (REIMB_Author == null) {
			if (other.REIMB_Author != null)
				return false;
		} else if (!REIMB_Author.equals(other.REIMB_Author))
			return false;
		if (REIMB_ID == null) {
			if (other.REIMB_ID != null)
				return false;
		} else if (!REIMB_ID.equals(other.REIMB_ID))
			return false;
		if (REIMB_RESOLVED == null) {
			if (other.REIMB_RESOLVED != null)
				return false;
		} else if (!REIMB_RESOLVED.equals(other.REIMB_RESOLVED))
			return false;
		if (REIMB_RESOLVER == null) {
			if (other.REIMB_RESOLVER != null)
				return false;
		} else if (!REIMB_RESOLVER.equals(other.REIMB_RESOLVER))
			return false;
		if (REIMB_STATUS_ID == null) {
			if (other.REIMB_STATUS_ID != null)
				return false;
		} else if (!REIMB_STATUS_ID.equals(other.REIMB_STATUS_ID))
			return false;
		if (REIMB_Type_ID == null) {
			if (other.REIMB_Type_ID != null)
				return false;
		} else if (!REIMB_Type_ID.equals(other.REIMB_Type_ID))
			return false;
		if (REIMB__SUBMITTED == null) {
			if (other.REIMB__SUBMITTED != null)
				return false;
		} else if (!REIMB__SUBMITTED.equals(other.REIMB__SUBMITTED))
			return false;
		return true;
	}


	public Integer getREIMB_ID() {
		return REIMB_ID;
	}


	public void setREIMB_ID(Integer rEIMB_ID) {
		REIMB_ID = rEIMB_ID;
	}


	public Integer getREIMB_Amount() {
		return REIMB_Amount;
	}


	public void setREIMB_Amount(Integer rEIMB_Amount) {
		REIMB_Amount = rEIMB_Amount;
	}


	public Date getREIMB__SUBMITTED() {
		return REIMB__SUBMITTED;
	}


	public void setREIMB__SUBMITTED(Date rEIMB__SUBMITTED) {
		REIMB__SUBMITTED = rEIMB__SUBMITTED;
	}


	public Date getREIMB_RESOLVED() {
		return REIMB_RESOLVED;
	}


	public void setREIMB_RESOLVED(Date rEIMB_RESOLVED) {
		REIMB_RESOLVED = rEIMB_RESOLVED;
	}


	public Blob getREIMB_RECEIPT() {
		return REIMB_RECEIPT;
	}


	public void setREIMB_RECEIPT(Blob rEIMB_RECEIPT) {
		REIMB_RECEIPT = rEIMB_RECEIPT;
	}


	public Users getREIMB_Author() {
		return REIMB_Author;
	}


	public void setREIMB_Author(Users rEIMB_Author) {
		REIMB_Author = rEIMB_Author;
	}


	public Users getREIMB_RESOLVER() {
		return REIMB_RESOLVER;
	}


	public void setREIMB_RESOLVER(Users rEIMB_RESOLVER) {
		REIMB_RESOLVER = rEIMB_RESOLVER;
	}


	public Status getREIMB_STATUS_ID() {
		return REIMB_STATUS_ID;
	}


	public void setREIMB_STATUS_ID(Status rEIMB_STATUS_ID) {
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
	}


	public Type getREIMB_Type_ID() {
		return REIMB_Type_ID;
	}


	public void setREIMB_Type_ID(Type rEIMB_Type_ID) {
		REIMB_Type_ID = rEIMB_Type_ID;
	}
	/*
	public void seturlString()
	{
		if(this.REIMB_RECEIPT!=null ) {
		 
			
		 Integer length;
		try {
			if(this.REIMB_RECEIPT.length()>1) {
			length = (int)this.REIMB_RECEIPT.length();
			 byte arraybyte[]= (this.REIMB_RECEIPT.getBytes(1,  length));
				// byte decode[]= Base64.getDecoder().decode(arraybyte);
				this.imageurl= new String( arraybyte);
			}
				  
		} catch (SQLException e) {
					
		}
		
		}
	}*/


	@Override
	public String toString() {
		return "ReimburseModel [REIMB_ID=" + REIMB_ID + ", REIMB_Amount=" + REIMB_Amount + ", REIMB__SUBMITTED="
				+ REIMB__SUBMITTED + ", REIMB_RESOLVED=" + REIMB_RESOLVED + ", REIMB_Author=" + REIMB_Author
				+ ", REIMB_RESOLVER=" + REIMB_RESOLVER + ", REIMB_STATUS_ID=" + REIMB_STATUS_ID + ", REIMB_Type_ID="
				+ REIMB_Type_ID + "]";
	}
	
	

}
