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
		
		avenger = new Avenger();
		avenger.setId("aaaa-aaaa-aaaa-aaab");
		avenger.setName("Captain America");
		avenger.setSecretIdentity("Steve Rogers");
		
		map.put("aaaa-aaaa-aaaa-aaab", avenger);
		
	}
	
	Map<String, Avenger> map= new HashMap<String, Avenger>();
	

	public Avenger find(String id) {
		Avenger avenger = map.get(id);
		return avenger;
	}

}
