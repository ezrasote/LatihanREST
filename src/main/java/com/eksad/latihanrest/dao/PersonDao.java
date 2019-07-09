package com.eksad.latihanrest.dao;

import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Person;

public interface PersonDao extends CrudRepository<Person, Long>{
	
}
