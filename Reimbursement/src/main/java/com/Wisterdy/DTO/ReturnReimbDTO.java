package com.Wisterdy.DTO;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import com.Wisterdy.Models.Status;
import com.Wisterdy.Models.Type;
import com.Wisterdy.Models.Users;

public class ReturnReimbDTO {


	String datesubmit;
	
	String dateresolve;
	
	String firstname; 
	String lastname;
	
    String resolvername; 
	Integer amount;
    String status;
	String type; 
	
	String id;
	
	String imageurl;
	public ReturnReimbDTO() {
		super();
	}
	
	public ReturnReimbDTO(String datesubmit, String dateresolve, String firstname, String lastname,
	 String resolvername,
	Integer amount,
    String status,
	String type,
	String id
			) {
		this.datesubmit=datesubmit;
		this.dateresolve=dateresolve;
		this.firstname= firstname;
		this.lastname=lastname;
		this.resolvername=resolvername;
		this.amount=amount;
		this.status=status;
		this.type=type;
		this.id=id;
		
		
		
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((dateresolve == null) ? 0 : dateresolve.hashCode());
		result = prime * result + ((datesubmit == null) ? 0 : datesubmit.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageurl == null) ? 0 : imageurl.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((resolvername == null) ? 0 : resolvername.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ReturnReimbDTO other = (ReturnReimbDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (dateresolve == null) {
			if (other.dateresolve != null)
				return false;
		} else if (!dateresolve.equals(other.dateresolve))
			return false;
		if (datesubmit == null) {
			if (other.datesubmit != null)
				return false;
		} else if (!datesubmit.equals(other.datesubmit))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageurl == null) {
			if (other.imageurl != null)
				return false;
		} else if (!imageurl.equals(other.imageurl))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (resolvername == null) {
			if (other.resolvername != null)
				return false;
		} else if (!resolvername.equals(other.resolvername))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReturnReimbDTO [datesubmit=" + datesubmit + ", dateresolve=" + dateresolve + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", resolvername=" + resolvername + ", amount=" + amount + ", status="
				+ status + ", type=" + type + ", id=" + id + ", imageurl=" + imageurl + "]";
	}
	
	

	public String getDatesubmit() {
		return datesubmit;
	}

	public void setDatesubmit(String datesubmit) {
		this.datesubmit = datesubmit;
	}

	public String getDateresolve() {
		return dateresolve;
	}

	public void setDateresolve(String dateresolve) {
		this.dateresolve = dateresolve;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getResolvername() {
		return resolvername;
	}

	public void setResolvername(String resolvername) {
		this.resolvername = resolvername;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public void seturlString(Blob  REIMB_RECEIPT)
	{
		if(REIMB_RECEIPT!=null ) {
		 
			
		 Integer length;
		try {
			if(REIMB_RECEIPT.length()>1) {
			length = (int)REIMB_RECEIPT.length();
			 byte arraybyte[]= (REIMB_RECEIPT.getBytes(1,  length));
				// byte decode[]= Base64.getDecoder().decode(arraybyte);
				this.imageurl= new String( arraybyte);
			}
				  
		} catch (SQLException e) {
					
		}
		
		}
	}

}
