package com.Wisterdy.Services;

import java.io.InputStream;
import java.util.ArrayList;

import com.Wisterdy.DA0.Repository;
import com.Wisterdy.DTO.LoginDTO;
import com.Wisterdy.DTO.ReimbDTO;
import com.Wisterdy.DTO.ReimbUpdateDTO;
import com.Wisterdy.DTO.ReturnReimbDTO;
import com.Wisterdy.Exception.BadFormatException;
import com.Wisterdy.Exception.GeneralException;
import com.Wisterdy.Models.ReimburseModel;
import com.Wisterdy.Models.Users;

public class Service {
 
	private Repository repository;
	public Service() {
		this.repository = new Repository();
	}
	
	public Service(Repository Mockuprepository)
	{
		this.repository= Mockuprepository;
	}

	

	
 public void Setup() throws GeneralException {
	 
	  try{
	 
		  this.repository.Setup();
	  }catch(Exception e)
	  {
		  throw new GeneralException ("Expection occured during setup " + e.getMessage());
	  }
			  
	
	//login
	
	//createuser sign up
	
	//
}

public int newUser(Users user) throws GeneralException {
	
int result=-1;
 try {
  this.repository.createUser(user);
  result =1;
 }catch(Exception e)
 {
	  throw new GeneralException( "Employee user account can't be created" + e.getMessage());
 }
 
 return result;
	
}


public void newFinanceUser(Users user) throws GeneralException {
	
	 try {
	  this.repository.createFinanceUser(user);
	 }catch(Exception e)
	 {
		  throw new GeneralException("Employee Finance Manager account can't be created" + e.getMessage());
	 }
		
	}

public Users LoginUser(LoginDTO logindto) throws GeneralException {
	Users result=null;
	String error="";
	try {
		  //this.repository.createFinanceUser(user);
		   if(logindto.getUsername()==null)
		   {
			   error= error + "Username can't not be empty ";
		   }
		   
		   if(logindto.getPassword()==null)
		   {
			   error= error + "Password can't not be empty ";
		   }
		   
		   if(error.length()>2)
		   {
			   throw new BadFormatException(error);
		   }
		  result= this.repository.LoginUser(logindto);
		   
		   if(result==null)
		   {
			   throw new BadFormatException("Couln't login user with the provided username and password");
		   }
		   
		 
		   
		   //this.repository.LoginUser(logindto);
		 }catch(Exception e)
		 {
			  throw new GeneralException(e.getMessage());
		 }
			
	   return result;
		
	
}
 

public int CreateReimb( ReimbDTO Reimbto , InputStream inputStream) throws GeneralException
{

	try {
	this.repository.CreateReimbursement(Reimbto , inputStream);
	}catch(Exception e)
	{
		 throw new GeneralException("Failed to create reimb from services " +e.getMessage());
	}
	return 1;
	
}

public ArrayList<ReturnReimbDTO> GetReimbursement(Integer id) throws GeneralException {
	
	ArrayList<ReturnReimbDTO> result= new ArrayList();
	try {
		result=this.repository.GetReimbursement(id);
		 }catch(Exception e)
		{
			 throw new GeneralException("Trying to get REIMB " +e.getMessage());
		}
	
	return result;
}

public ArrayList<ReturnReimbDTO> GetReimbursementFinace() throws GeneralException {
	
	ArrayList<ReturnReimbDTO> result= new ArrayList();
	try {
		result=this.repository.GetReimbursementforFianance();
		 }catch(Exception e)
		{
			 throw new GeneralException("Trying to get all Reimb for finance manager " +e.getMessage());
		}
	
	return result;
}

public int UpdateReimb(ReimbUpdateDTO reimbupdatedto) throws GeneralException {
	int result=-1;
	try {
		
		result=this.repository.UpdateReimb(reimbupdatedto);
		 }catch(Exception e)
		{
			 throw new GeneralException("Trying to get all Reimb for finance manager " +e.getMessage());
		}
	return result;
}
}