package com.eksad.latihanrest.dao;

import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long>{

}
