package com.Wisterdy.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.Wisterdy.DA0.Repository;
import com.Wisterdy.DTO.LoginDTO;
import com.Wisterdy.DTO.ReimbUpdateDTO;
import com.Wisterdy.DTO.UserDTO;
import com.Wisterdy.Exception.BadFormatException;
import com.Wisterdy.Exception.GeneralException;
import com.Wisterdy.Models.Users;
import com.Wisterdy.Services.Service;

public class ProjectTest {

	private static Repository mockrepository;
	private static Service service;
	private static Users user;
	private static LoginDTO logindto;
	private static ReimbUpdateDTO reimupdateDTO;
	
	@BeforeClass
	public  static  void SetupMock() throws GeneralException, BadFormatException {
		
	  user= new Users("donjoe",  "password", "don", "joe", "donjone2020@gmail.com");
	  Users userblank= new Users();
	   UserDTO userdto= new UserDTO("donjoe",  "password", "don", "joe", "donjone2020@gmail.com");
	   mockrepository = mock(Repository.class);
	  when(mockrepository.createUser(user)).thenReturn(1);
	  when(mockrepository.createUser(userblank)).thenReturn(-1);
	  logindto= new LoginDTO("donjoe", "password");
	  when(mockrepository.LoginUser(logindto)).thenReturn(user);
	  reimupdateDTO= new ReimbUpdateDTO("1", "3", "12");
	  when(mockrepository.UpdateReimb(reimupdateDTO)).thenReturn(1);
	}
	@Before
	public void beforeTest()
	{
		service= new Service(mockrepository);
	}
	
	
	@Test
	public void CreateUserTest() throws GeneralException
	{
		try {
		int expected =1;
		
	    int actual = service.newUser(user);
	    assertEquals(expected, actual);
		}catch(Exception e )
		{
			fail();
		}
		//actual 
	}
	
	
	@Test
	public void GetUserTest() throws GeneralException
	{
		try {
		Users expected =user;
		
	   Users actual = service.LoginUser(logindto);
	    assertEquals(expected, actual);
		}catch(Exception e )
		{
			fail();
		}
		//actual 
	}
	
	@Test
	public void GetUserTestWrongAuthenticationPassword() throws GeneralException
	{
		try {
		Users expected =user;
		LoginDTO loginwrong= new LoginDTO("donjoe", "ewpppwep");
		service.LoginUser(loginwrong);
		fail();
		}catch(Exception e )
		{
			 assertEquals(e.getMessage(), "Couln't login user with the provided username and password" );
		}
		//actual 
	}
	
	
	@Test
	public void GetUserTestWrongAuthenticationUsername() throws GeneralException
	{
		try {
		Users expected =user;
		LoginDTO loginwrong= new LoginDTO("wrongusername", "password");
		service.LoginUser(loginwrong);
		fail();
		}catch(Exception e )
		{
			 assertEquals(e.getMessage(), "Couln't login user with the provided username and password" );
		}
		//actual 
	}
	
	
	@Test
	public void GetUserTestWrongAuthenticationUsernameNull() throws GeneralException
	{
		try {
		Users expected =user;
		LoginDTO loginwrong= new LoginDTO();
		loginwrong.setPassword("hello");
		this.service.LoginUser(loginwrong);
		fail();
		}catch(Exception e )
		{
			 assertEquals(e.getMessage().trim(), "Username can't not be empty".trim() );
		}
		//actual 
	}
	
	@Test
	public void GetUserTestWrongAuthenticationPasswordNull() throws GeneralException
	{
		try {
		Users expected =user;
		LoginDTO loginwrong= new LoginDTO();
		loginwrong.setUsername("notnull");
		service.LoginUser(loginwrong);
		fail();
		}catch(Exception e )
		{
			 assertEquals(e.getMessage().trim(), "Password can't not be empty".trim() );
		}
		//actual 
	}
	
	@Test
	public void GetUserTestWrongAuthenticationBothNull() throws GeneralException
	{
		try {
		Users expected =user;
		LoginDTO loginwrong= new LoginDTO();
		
		service.LoginUser(loginwrong);
		fail();
		}catch(Exception e )
		{
			 assertEquals(e.getMessage().trim(), "Username can't not be empty Password can't not be empty".trim() );
		}
		//actual 
	}
	
	@Test
	public void UpdateuserifDTOisvalid() throws GeneralException
	{
		try {
		int expected=1;
		
		
		int actual =service.UpdateReimb(reimupdateDTO);
		
		assertEquals(expected, actual);
		}catch(Exception e )
		{
			fail();
			 
		}
	
	}
	
	
	/*dont work since it valid in the frontend and since i dont have NumberFomating Exeception"
	@Test
	public void UpdateuserfailDTOinvalid() throws GeneralException
	{
		try {
		
		
		ReimbUpdateDTO failed = new ReimbUpdateDTO();
		failed.setApprove("le");
		failed.setApprove("ew");
		service.UpdateReimb(failed);
		fail();
		}catch(Exception e )
		{
			assertEquals(e.getMessage(), "sorry");
			 
		}
	
	}
	
	/*
	@Test
	public void CreateUserTestinvalid() throws GeneralException
	{
		try {
		int expected =-1;
		
		Users test= new Users();
	    int actual = this.service.newUser(test);
	    assertEquals(expected, actual);
		}catch(Exception e )
		{
			fail();
		}
		//actual 
	}*/

}




