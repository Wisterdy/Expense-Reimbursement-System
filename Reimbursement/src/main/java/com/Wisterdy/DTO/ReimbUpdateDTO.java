package com.Wisterdy.DTO;

public class ReimbUpdateDTO {

	private String status;
	private String approve;
	private String resolveid;
	public ReimbUpdateDTO() {
		super();
	}
	
	public ReimbUpdateDTO(String status, String approve, String resolveid) {
	this.status= status;
	this.approve=approve;
	this.resolveid= resolveid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result + ((resolveid == null) ? 0 : resolveid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ReimbUpdateDTO other = (ReimbUpdateDTO) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (resolveid == null) {
			if (other.resolveid != null)
				return false;
		} else if (!resolveid.equals(other.resolveid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbUpdateDTO [status=" + status + ", approve=" + approve + ", resolveid=" + resolveid + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getResolveid() {
		return resolveid;
	}

	public void setResolveid(String resolveid) {
		this.resolveid = resolveid;
	}

	
	

	

}
