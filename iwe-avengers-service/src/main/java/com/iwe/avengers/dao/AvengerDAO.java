package com.iwe.avengers.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDAO {

	public AvengerDAO() {
		Avenger avenger = new Avenger();
		avenger.setId("aaaa-aaaa-aaaa-aaaa");
		avenger.setName("Iron Man");
		avenger.setSecretIdentity("Tony Stark");

		map.put("aaaa-aaaa-aaaa-aaaa", avenger);

	}

	Map<String, Avenger> map = new HashMap<String, Avenger>();

	public Avenger find(String id) {
		Avenger avenger = map.get(id);
		return avenger;
	}

	public Avenger save(Avenger avenger) {
		String id = "1" ;
		avenger.setId(id);
		map.put(avenger.getId(), avenger);
		return avenger;
	}
	
	public Avenger update(Avenger avenger) {
		String id = map.size() + "" ;
		avenger.setId(id);
		map.replace(avenger.getId(), avenger);
		return avenger;
	}
	
	public boolean delete(String id) {
		map.remove(id);
		return true;
	}

}
