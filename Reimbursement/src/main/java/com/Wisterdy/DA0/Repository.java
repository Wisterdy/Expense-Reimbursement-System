package com.Wisterdy.DA0;



import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;

import org.eclipse.jetty.server.Authentication.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Wisterdy.DTO.LoginDTO;
import com.Wisterdy.DTO.ReimbDTO;
import com.Wisterdy.DTO.ReimbUpdateDTO;
import com.Wisterdy.DTO.ReturnReimbDTO;
import com.Wisterdy.Exception.BadFormatException;
import com.Wisterdy.Exception.GeneralException;
import com.Wisterdy.Models.ReimburseModel;
import com.Wisterdy.Models.Status;
import com.Wisterdy.Models.Type;
import com.Wisterdy.Models.UserRole;
import com.Wisterdy.Models.Users;
import com.Wisterdy.Util.SessionUtility;

public class Repository {

	public Repository() {
	}

	
	
	
public void Setup() throws GeneralException
{
	try{
		  Session session=SessionUtility.getSession().openSession();
		  Transaction transaction=session.beginTransaction();
		 Type Lodging= new Type("Lodging");
		 Type Travel= new Type("Travel");
		 Type Food= new Type("Food");
		 Type Other= new Type("Other");
		 
		 UserRole employee= new UserRole("employee");
		 UserRole finance= new UserRole("Finance");
		 
		 Status Pending= new Status("Pending");
		 Status Deny= new Status("Deny");
		 Status Approve= new Status("Approve");
		 
		 //Type
		 session.save(Lodging);
		 session.save(Travel);
		 session.save(Food);
		 session.save(Other);
		 //Employee roles
		 session.save(employee);
		 session.save(finance);
		 //Reimb_status
		 session.save(Pending);
		 session.save(Deny);
		 session.save(Approve);
		 transaction.commit();
		 
	}catch(Exception e) {
	
		throw new GeneralException(e.getMessage());
	}
	
}




public int createUser(Users user) throws GeneralException {
	
	int result =-1;
	 try {
      Session session=SessionUtility.getSession().openSession();
	  Transaction transaction=session.beginTransaction();
	  Users newuser= user;
	  UserRole employeerole= session.find(UserRole.class, 1);
	  newuser.setRole(employeerole);
	  session.save(newuser);
	  transaction.commit();
	  result=1;
	 }catch(Exception e)
	 {
		  throw new GeneralException("Failed to create Employee " +e.getMessage());
	 }
		
	 return result;
	
}
	
	
public void createFinanceUser(Users user) throws GeneralException {
	
	 try {
     Session session=SessionUtility.getSession().openSession();
	  Transaction transaction=session.beginTransaction();
	  Users newuser= user;
	  UserRole employeerole= session.find(UserRole.class, 2);
	  newuser.setRole(employeerole);
	  session.save(newuser);
	  transaction.commit();
	 }catch(Exception e)
	 {
		  throw new GeneralException("Failed to create Employee " +e.getMessage());
	 }
		
	
}




public Users LoginUser(LoginDTO logindto) throws GeneralException {
	
	
	try {
    Session session= SessionUtility.getSession().openSession();
    //Transaction transaction = session.beginTransaction();
    String querystring= "From Users u WHERE u.user_name = " + ":username" + " and " + "u.UserPassword = " +
   ":password";
     Users user=null;
     Query query=session.createQuery(querystring);
     query.setParameter("username", logindto.getUsername());
     query.setParameter("password", logindto.getPassword());
     user = (Users)query.getSingleResult();
  	  
  
  	  
  	  if(user!=null)
  	  {
  		  return user;
  	  }
	}catch(Exception e)
	 {
		  throw new GeneralException("Failed to Login ");
	 }
	
	return null;
	
	
}




public void CreateReimbursement(ReimbDTO reimbdto , InputStream inputStream) throws GeneralException {
	
	try {
		 Session session= SessionUtility.getSession().openSession();
		 Transaction transaction = session.beginTransaction();
		 Users user = session.find(Users.class, reimbdto.getId());
		 
		 
		 int type=-1;
		 if(reimbdto.getType().toLowerCase().equals("food"))
			 type=3;
		 if(reimbdto.getType().toLowerCase().equals("lodging"))
			 type=1;
		 if(reimbdto.getType().toLowerCase().equals("travel"))
			 type=2;
		 if(reimbdto.getType().toLowerCase().equals("other"))
			 type=4;
		 
		 Type typeobject= session.find(Type.class, type);
		 
		 Status status= session.find(Status.class, 1);
		 
		 LocalDate date = LocalDate.now();
		 int month=date.getMonthValue();
		 int day= date.getDayOfYear();
		 int year= date.getYear();
		 Date thedate = new Date(Instant.now().toEpochMilli());
		// Date thedate2 = new Date();
		 byte array[]=new byte[5];
		 array[0]= 1;
		 array[0]= 24;
		 
		 Blob myblob= new SerialBlob(array);
		// Integer REIMB_Amount, Date REIMB__SUBMITTE, Date REIMB_RESOLVED, Blob  REIMB_RECEIPT, Users Reimb_Author)
		 ReimburseModel request= new ReimburseModel(reimbdto.getAmount(), thedate,null, myblob, user);
		 //InputStream in = new FileInputStream(reimbdto.getUrl());
		 request.setREIMB_RESOLVED(null);
		 InputStream in = inputStream;
		
		byte newbytearray[]=new byte[in.available()];
		 
		/* int start=0;
		 int readletter=in.read();
		 while(readletter!=1)
		 {
			 readletter=in.read();
			newbytearray[start]=(byte) readletter;
			 start=start+1;
			
		 }*/
		  in.read(newbytearray);
		byte[] urlencode=Base64.getEncoder().encode(newbytearray);
		 Blob realblob= new SerialBlob(urlencode);  //urlencode
		 request.setREIMB_RECEIPT(realblob);
	
		 
		
		 request.setREIMB_Type_ID(typeobject);
		 request.setREIMB_STATUS_ID(status);
		 session.save(request);
		 transaction.commit();
		
	}catch(Exception e)
	{
		 throw new GeneralException("Failed to create reimb " +e.getMessage());
	}
	
}




public  ArrayList<ReturnReimbDTO> GetReimbursement(Integer id) throws GeneralException {
	
	String username="";
	ArrayList<ReturnReimbDTO> resultlist= new ArrayList<>();
    ArrayList<ReimburseModel> list= new ArrayList<>();
	String result="";
	StringBuffer buffer= new StringBuffer();
	
	
	try {//pause
		
		
		 Session session= SessionUtility.getSession().openSession();
		 Users user = session.find(Users.class,id);
		 
		 username= user.getUser_name();
		//REIMB_Author
		 String querystring= "From ReimburseModel u WHERE u.REIMB_Author = " + ":username";
				   list= new ArrayList<>();
				     Query query=session.createQuery(querystring);
				     query.setParameter("username", user);

				    list = (ArrayList<ReimburseModel>) query.getResultList();
					String datesubmit="";
					
					String dateresolve="";
					
					String firstname=""; 
					String lastname="";
					
				    String resolvername=""; 
					Integer amount;
				    String status;
					String type; 
					
					String reimbid="";
				    for(int i=0; i< list.size(); i++)
				    {ReimburseModel temp= (ReimburseModel)list.get(i);
				    	
				         reimbid=temp.getREIMB_ID().toString();
				        if(temp.getREIMB__SUBMITTED()!=null)
				    	datesubmit= temp.getREIMB__SUBMITTED().toString();
				    	if(temp.getREIMB_RESOLVED()!=null) {
				    		//if(!temp.getREIMB_RESOLVED().toString().equals("1969-12-31"))
				    	dateresolve=temp.getREIMB_RESOLVED().toString();
				    		//if(!temp.getREIMB_RESOLVED().toString().equals("1969-12-31"))
				    			
				    	}
				    	if(temp.getREIMB_Author()!=null) {
				    	firstname=temp.getREIMB_Author().getFirstName();
				    	lastname= temp.getREIMB_Author().getLastName();
				    	}
				    	if(temp.getREIMB_RESOLVER()!=null)
				    	resolvername= temp.getREIMB_RESOLVER().getFirstName() + "  "+ temp.getREIMB_RESOLVER().getLastName();
				    	amount= temp.getREIMB_Amount();
				    	status= temp.getREIMB_STATUS_ID().getReimb_Status();
				    	type= temp.getREIMB_Type_ID().getReimb_Type();
				    	buffer.append(temp.getREIMB_STATUS_ID().getReimb_Status_ID().toString());
				    	
				    	ReturnReimbDTO actual= new ReturnReimbDTO(datesubmit, dateresolve, firstname, lastname, resolvername, amount, status, type ,reimbid);
				    	if(temp.getREIMB_RECEIPT()!=null)
				    	{
				    		actual.seturlString(temp.getREIMB_RECEIPT());
				    	}
				    	resultlist.add(actual);
				    	
				    	 datesubmit="";
							
					      dateresolve="";
						
						 firstname=""; 
						 lastname="";
						
					    resolvername=""; 
						 amount=0;
					    status="";
						type=""; 
				    	
				    }
				    
				    
				    
				    
		
				/*
			 if(temp.getREIMB_RECEIPT()!=null) {
			 Blob copy=temp.getREIMB_RECEIPT();
			 Integer length= (int)copy.length();
			 byte arraybyte[]= (copy.getBytes(1,  length));
			// byte decode[]= Base64.getDecoder().decode(arraybyte);
			 result= new String( arraybyte);
			  newreimb.setImageurl(result);
			 }
			 
					 
			 resultlist.add(newreimb);
		 }*/
		 return resultlist;
		 
		 
		/*
		 Session session= SessionUtility.getSession().openSession();
		 ReimburseModel reimb= session.find(ReimburseModel.class, id);
		 Blob copy=reimb.getREIMB_RECEIPT();
		 Integer length= (int)copy.length();
		 byte arraybyte[]= (copy.getBytes(1,  length));
		// byte decode[]= Base64.getDecoder().decode(arraybyte);
		 result= new String( arraybyte);
				 */
		   
		 
	}catch(Exception e)
	{
		//"Failed to get reimb in Respository "
		 throw new GeneralException("Failed to get reimb in Respository " + e.getMessage() + list.toString());
	}
	
	
	//return result;
}


public  ArrayList<ReturnReimbDTO> GetReimbursementforFianance() throws GeneralException {
	
	String username="";
	ArrayList<ReturnReimbDTO> resultlist= new ArrayList<>();
    ArrayList<ReimburseModel> list= new ArrayList<>();
	String result="";
	StringBuffer buffer= new StringBuffer();
	
	
	try {//pause
		
		
		 Session session= SessionUtility.getSession().openSession();
		 //Users user = session.find(Users.class,id);
		 
		// username= user.getUser_name();
		//REIMB_Author
		 String querystring= "From ReimburseModel";
				   list= new ArrayList<>();
				     Query query=session.createQuery(querystring);
				    // query.setParameter("username", user);

				    list = (ArrayList<ReimburseModel>) query.getResultList();
					String datesubmit="";
					
					String dateresolve="";
					
					String firstname=""; 
					String lastname="";
					
				    String resolvername=""; 
					Integer amount;
				    String status;
					String type; 
					
					String reimbid="";
				    for(int i=0; i< list.size(); i++)
				    {ReimburseModel temp= (ReimburseModel)list.get(i);
				    	
				         reimbid=temp.getREIMB_ID().toString();
				        if(temp.getREIMB__SUBMITTED()!=null)
				    	datesubmit= temp.getREIMB__SUBMITTED().toString();
				    	if(temp.getREIMB_RESOLVED()!=null) {
				    		//if(temp.getREIMB_RESOLVED().toString().equals("1969-12-31"))
				    	dateresolve=temp.getREIMB_RESOLVED().toString();
				    		//if(temp.getREIMB_RESOLVED().toString().equals("1969-12-31"))
				    			
				    	}
				    	if(temp.getREIMB_Author()!=null) {
				    	firstname=temp.getREIMB_Author().getFirstName();
				    	lastname= temp.getREIMB_Author().getLastName();
				    	}
				    	if(temp.getREIMB_RESOLVER()!=null)
				    	resolvername= temp.getREIMB_RESOLVER().getFirstName() + " "+ temp.getREIMB_RESOLVER().getLastName();
				    	amount= temp.getREIMB_Amount();
				    	status= temp.getREIMB_STATUS_ID().getReimb_Status();
				    	type= temp.getREIMB_Type_ID().getReimb_Type();
				    	buffer.append(temp.getREIMB_STATUS_ID().getReimb_Status_ID().toString());
				    	
				    	ReturnReimbDTO actual= new ReturnReimbDTO(datesubmit, dateresolve, firstname, lastname, resolvername, amount, status, type ,reimbid);
				    	if(temp.getREIMB_RECEIPT()!=null)
				    	{
				    		actual.seturlString(temp.getREIMB_RECEIPT());
				    	}
				    	resultlist.add(actual);
				    	
				    	 datesubmit="";
						
					      dateresolve="";
						
						 firstname=""; 
						 lastname="";
						
					    resolvername=""; 
						 amount=0;
					    status="";
						type=""; 
						
				    	
				    }
				    
				    
				    
				    
	
		 return resultlist;
		 
		 

		 
	}catch(Exception e)
	{
		//"Failed to get reimb in Respository "
		 throw new GeneralException("Failed to get reimb for finance manager in Respository " + e.getMessage() + list.toString());
	}
	
	
	//return result;
}




public int UpdateReimb(ReimbUpdateDTO reimbupdatedto) throws GeneralException, BadFormatException {
	
	int result=-1; 
  try {
		
		Integer status= Integer.parseInt(reimbupdatedto.getStatus());
		//the id of the reimbursement 
		Integer index= Integer.parseInt(reimbupdatedto.getApprove());
		Integer userid= Integer.parseInt(reimbupdatedto.getResolveid());
		
		
		 Session session= SessionUtility.getSession().openSession();
		 
		 ReimburseModel reimbursemodel= session.find(ReimburseModel.class, index);
		 Users userResolver= session.find(Users.class, userid);
		 Date thedate = new Date(Instant.now().toEpochMilli());
		//String resolvername= userResolver.getLastName() + userResolver.getFirstName();
	
		
		reimbursemodel.setREIMB_RESOLVED(thedate);
		reimbursemodel.setREIMB_RESOLVER(userResolver);
		
		 
		 
		 Transaction transaction = session.beginTransaction();
		//deny
		if(status==1) {
			Status denystatus= session.find(Status.class, 2);
			reimbursemodel.setREIMB_STATUS_ID(denystatus);
			session.merge(reimbursemodel);
			transaction.commit();
			return 1; 
		}
		if(status==2) {
			Status approvestatus= session.find(Status.class, 3);
			reimbursemodel.setREIMB_STATUS_ID(approvestatus);
			session.merge(reimbursemodel);
			transaction.commit();
			
			return 1;
		}
		 
		 }
         catch(Exception e)
		{
			 throw new GeneralException("Failed to updated Reimbursement" +e.getMessage());
		}
	
	return result;
}





	
}
