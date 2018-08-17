package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao;

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		context.getLogger().log("[#] - Updating Avenger with id: " + avenger.getId());

		try {
			final Avenger retrivedAvenger = dao.find(avenger.getId());
			final Avenger savedAvenger = dao.save(retrivedAvenger);
			context.getLogger().log("[#] - Avenger found! Updating...");
			return HandlerResponse.builder()
					.setStatusCode(200)
					.setObjectBody(savedAvenger)
					.build();
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avender id: " + avenger.getId() + " not found ");
		}
	}
	
}
