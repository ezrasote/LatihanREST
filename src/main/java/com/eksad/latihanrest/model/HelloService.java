package com.eksad.latihanrest.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.eksad.latihanrest.dao.BrandDao;

public class HelloService {
	
	@Autowired
	private BrandDao brandDao;
	
	private String greetings;
	
//	@Transactional(propagation = Propagation)
	public String sayHello(String name) {
		
//		brandDao.save(null);
//		brandDao.findAll().forEach(System.out::println);
		
		return "Hello"+ name;
		
	}
}
