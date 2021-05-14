package com.Wisterdy.Exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.Wisterdy.DTO.MessageDTO;
import com.Wisterdy.Endpoint.Controllers;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controllers {
	private Logger logger = LoggerFactory.getLogger( ExceptionController.class);
	
	public ExceptionController() {
		
	}
	
	
	private ExceptionHandler<GeneralException> generalExceptions = (e, ctx) ->{
		
		MessageDTO message= new MessageDTO(e.getMessage());
		logger.info(e.getMessage());
		ctx.json(message);
		ctx.status(400);
		
	};
	
private ExceptionHandler<BadFormatException> badformatExceptions = (e, ctx) ->{
		
		MessageDTO message= new MessageDTO(e.getMessage());
		logger.info(e.getMessage());
		ctx.json(message);
		ctx.status(400);
		
	};

	@Override
	public void Endpoints(Javalin app) {
		// TODO Auto-generated method stub
		app.exception(GeneralException.class, generalExceptions);
		app.exception(BadFormatException.class, badformatExceptions);
		
	}

}
