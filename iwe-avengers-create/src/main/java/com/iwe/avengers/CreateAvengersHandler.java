package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	private AvengerDAO dao;

	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {

		context.getLogger().log("[#] - Creating Avenger with : \n" + newAvenger.toString());
		
		final Avenger saveddAvenger = dao.save(newAvenger);
		
		final HandlerResponse response = HandlerResponse.builder()
										.setStatusCode(201)
										.setObjectBody(saveddAvenger)
										.build();
		
		context.getLogger().log("[#] - Avenge registred " + saveddAvenger.getId());
		
		return response;

	}
}
