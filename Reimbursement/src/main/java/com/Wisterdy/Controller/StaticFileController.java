package com.Wisterdy.Controller;

import java.io.InputStream;

import com.Wisterdy.Endpoint.Controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import java.io.FileInputStream;
import java.io.InputStream;
public class StaticFileController  implements Controllers {

	public StaticFileController() {
		
		
		
	}
	
	
private Handler createFileHandler(String classpathPath) {
		return ctx -> {
			InputStream is = StaticFileController.class.getResourceAsStream(classpathPath);
			
			//byte[] ourFileInBytes= new byte[is.available()];
		byte ourFileInBytes[]=new byte[is.available()];
		
			is.read(ourFileInBytes);
			//= is.readAllBytes();
			String html = new String(ourFileInBytes);
			ctx.html(html);
			ctx.status(200);
		};
}

	@Override
public void Endpoints(Javalin app) {
		app.get("/", createFileHandler("/static/index.html"));
		//app.get("/test", createFileHandler("/static/test.html"));
		
	}

}
