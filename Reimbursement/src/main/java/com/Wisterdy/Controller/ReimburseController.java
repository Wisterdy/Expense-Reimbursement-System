package com.Wisterdy.Controller;


import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Wisterdy.DTO.LoginDTO;
import com.Wisterdy.DTO.MessageDTO;
import com.Wisterdy.DTO.ReimbDTO;
import com.Wisterdy.DTO.ReimbUpdateDTO;
import com.Wisterdy.DTO.ReturnReimbDTO;
import com.Wisterdy.DTO.UserDTO;
import com.Wisterdy.Endpoint.Controllers;
import com.Wisterdy.Exception.ExceptionController;
import com.Wisterdy.Models.ReimburseModel;
import com.Wisterdy.Models.UserRole;
import com.Wisterdy.Models.Users;
import com.Wisterdy.Services.Service;
import com.Wisterdy.Util.SessionUtility;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimburseController implements Controllers {
	private Logger logger = LoggerFactory.getLogger( ReimburseController.class);
 Service service= null;
	
	  String error="";
	public ReimburseController() {
		this.service= new Service();
	}
	/*
	public ReimburseController(service) {
		whatever services
	}*/
	
	
	//many user can point to same role
    //example user 1 can be employee
	//user two can also be an employee
	//user three can can a fiancial advisers
	
	
  private Handler Homepage= ctx ->{
	  
	
	  ctx.result("home page" );
	  ctx.status(200);
	  
  };

  private Handler Setup= ctx ->{
	  
	//  ctx.result("setting up");
	  this.service.Setup();
	  
	  
  };

  private Handler CreateUser= ctx ->{
	 
	
	UserDTO userdto= ctx.bodyAsClass(UserDTO.class);
	Users newuser= new Users(userdto.getUsername(), userdto.getPassword(), userdto.getFirstname(), userdto.getLastname(), userdto.getEmail());
	 
	int result= this.service.newUser(newuser);
	
	
	 if(result==1) {
	  MessageDTO success= new MessageDTO("User sucessful created");
		 ctx.json(success);
		 logger.info("User sucessful created");
		ctx.status(200);
	 }
	  
  
	
  };
  
  private Handler CreateUserFinance= ctx ->{
		 
	  
		
	  UserDTO userdto= ctx.bodyAsClass(UserDTO.class);
		Users newuser= new Users(userdto.getUsername(), userdto.getPassword(), userdto.getFirstname(), userdto.getLastname(), userdto.getEmail());
		 this.service.newFinanceUser(newuser);
		 
		 MessageDTO success= new MessageDTO("User sucessful created");
		 logger.info("Finance Manager User sucessful created");
		 ctx.json(success);
		  ctx.status(200);
  };
  
  private Handler LoginUser= ctx ->{
		 
	     LoginDTO logindto= ctx.bodyAsClass(LoginDTO.class);
	 // LoginDTO logindto= new LoginDTO("Dony96", "ppassword");
	  
	 Users result= this.service.LoginUser(logindto);
	  if(result!=null) 
	  {
		  ctx.sessionAttribute("CurrentlyLoginuser" , result);
		  logger.info("User sucessful Login");
		 ctx.json(logindto);
		   ctx.status(200);
	  }
	  
	  if(result==null)
	  {
		  ctx.status(222);
	  }
	     
	     
	 
};


private Handler GetLoginUser= ctx ->{
	 
	Users user=  (Users)ctx.sessionAttribute("CurrentlyLoginuser");
    
	if( user==null)
	{
		MessageDTO message= new MessageDTO("User is not logged in");
		 logger.info("Retrieve Login User");
		ctx.json(message);
		ctx.status(400);
	}
	else
	{
		 logger.info("retrieve log in user sucessful" + user.toString());
		ctx.json(user);
		ctx.status(200);
	}

};
	 

private Handler LogoutUser= ctx ->{
	 
     ctx.req.getSession().invalidate();
    

};
	/*  
private Handler CreateReimb= ctx ->{
	 
    //String id= ctx.pathParam("id");
   // ReimbDTO reimbto= ctx.bodyAsClass(ReimbDTO.class);
	//String  type=ctx.req.getContgetentType();
	//ctx.req.getPart("new");
	 //Integer length=ctx.req.getContentLength();
	 
	 //InputStream inputStream=ctx.req.getInputStream();
	 //ServletInputStream bufferedReader=ctx.req.getInputStream();
	 //bufferedReader.read();
	//InputStream inputStream = ctx.bodyAsInputStream();
	

	 //Integer result = bufferedReader.read();
	//Integer length=bytearray.length;
	//String s = new String(bytearray);
    //this.service.CreateReimb(reimbto);
	//String name = ctx.formParam("new");
      
 // MessageDTO message = new MessageDTO("trying to create reimbursment");
	MessageDTO message = new MessageDTO("trying to create reimbursment");
   ctx.json(message);
    ctx.status(200);
    
   

};*/

private Handler CreateReimb= ctx ->{
	 
   
	 
	 InputStream inputStream=ctx.bodyAsInputStream();
	 

	 //Integer result = bufferedReader.read();
	//Integer length=bytearray.length;
	//String s = new String(bytearray);
    //this.service.CreateReimb(reimbto);
	//String name = ctx.formParam("new");
	
      
	 String  id= ctx.pathParam("id");
	 Integer ID= Integer.parseInt(id);
	 String amount = ctx.pathParam("amount");
	 Integer priceamount= Integer.parseInt(amount);
	 String type = ctx.pathParam("type");
	 
	 ReimbDTO reimbdto= new ReimbDTO(ID,priceamount, "", type);
	 this.service.CreateReimb(reimbdto, inputStream);
 // MessageDTO message = new MessageDTO("trying to create reimbursment");
	MessageDTO message = new MessageDTO("trying " + id + " amount "+ amount + " type "+ type );
   ctx.json(message);
   
   logger.info("Reimbursement succesfully created");
    ctx.status(200);
    
   

};

private Handler GetReimb= ctx ->{
	
   //String result= this.service.GetReimbursement(14);
	Integer userid= Integer.parseInt(ctx.pathParam("id"));
	ArrayList<ReturnReimbDTO> result= new ArrayList();
    result=  this.service.GetReimbursement( userid);
   
   // MessageDTO message = new MessageDTO(result.toString());
   // ctx.json(message);
    logger.info("sucessful Retrieve Reimbursement for loginuser");
    ctx.json(result);
    ctx.status(200);
    
};


private Handler GetReimbFinance = ctx ->{
	
	
	
		ArrayList<ReturnReimbDTO> result= new ArrayList();
	    result=  this.service.GetReimbursementFinace();
	   
	    logger.info("sucessful Retrieve Reimbursement for loginuser finance Manager");
	    ctx.json(result);
	    ctx.status(200);
	    
	};
	
	
	
	private Handler UpdateReimbFinance = ctx ->{
		
		  ReimbUpdateDTO reimbupdatedto= ctx.bodyAsClass(ReimbUpdateDTO.class);
		
		 // ctx.result("Updating finance");
		  
		  this.service.UpdateReimb(reimbupdatedto);
		  logger.info("sucessful Update Reimbursement as a finance");
		  ctx.json(reimbupdatedto);
		    ctx.status(200);
		    
};
	  
	@Override
	public void Endpoints(Javalin app) {
      //app.get("/", Homepage);
      //Setup status, option, Roles,
     /*app.get("/setup", Setup);*/
      //login is tricky first post and then get a user if exist;// Username and Password;
      app.post("/LoginUser",  LoginUser);
      app.get("/LoginUser",  GetLoginUser);
      app.post("/LogoutUser",  LogoutUser);
      
      //create user ---> post 
      app.post("/CreateUser",  CreateUser);
      app.post("/CreateUserFinance",  CreateUserFinance);
      //update userinfo ---put
      //delete user --delete all reimbursement thats pending or reject approve stay in system\-->delete
      
      //create reimb
     // app.post("/CreateReimb", CreateReimb);
      app.post("/CreateReimb/:id/:amount/:type", CreateReimb);
      app.get("/GetReimb/:id", GetReimb);
      app.get("/GetReimbFinance", GetReimbFinance);
      app.put("/UpdateReimbFinance", UpdateReimbFinance);
      //get Reimbursement associate with user ---get
      //delete reimbersement -only pending reimbursement can be deleted; -->delete
      //update reimbersementinfo- --put owner of reimbursement
      //upate  approve reimbersement status--finance manager 
     
		
		
	}
	
	
	
	
	
	

}
