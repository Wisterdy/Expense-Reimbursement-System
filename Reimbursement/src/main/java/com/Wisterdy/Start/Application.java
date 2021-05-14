package com.Wisterdy.Start;

import com.Wisterdy.Controller.ReimburseController;
import com.Wisterdy.Controller.StaticFileController;
import com.Wisterdy.Endpoint.Controllers;
import com.Wisterdy.Exception.ExceptionController;

import io.javalin.Javalin;

public class Application {
	 private static Javalin app=null;
	public Application() {
	
	}

	public static void main(String[] args) {
	
		
		
		
		app= Javalin.create(config->
		{config.enableCorsForAllOrigins();
				}
				
				);
				
				
	handleEndpoints(new ReimburseController(), new ExceptionController(), new StaticFileController());

		
		app.start(7000);
	 
	     
	}
	
	
private static void handleEndpoints(Controllers...controllers) {
	
	for(Controllers control:  controllers)
	{
		control.Endpoints(app);
	}
}

}
