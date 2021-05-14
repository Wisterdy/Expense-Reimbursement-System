package com.Wisterdy.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtility {

	private static SessionFactory sessionfactory;
	
	public synchronized static SessionFactory getSession() {
		
	    if(sessionfactory==null)
	    {
	    	sessionfactory=  new Configuration().
	    			setProperty("hibernate.connection.username", "root")
	    			.setProperty("hibernate.connection.password", "admin")
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
	    	
	    	
	    }
	    
	   // return sessionfactory.openSession();
	    return sessionfactory;
	}
}
