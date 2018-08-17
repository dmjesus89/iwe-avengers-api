package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao;

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		context.getLogger().log("[#] - Deleting Avenger with id: " + avenger.getId());

		try {

			dao.find(avenger.getId());

			dao.delete(avenger);

			context.getLogger().log("[#] - Avenger deleted " + avenger.getId());
			return HandlerResponse.builder().setStatusCode(204)
					// .setObjectBody(retrivedAvenger)
					.build();

		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avender id: " + avenger.getId() + " not found ");
		}

	}
}
