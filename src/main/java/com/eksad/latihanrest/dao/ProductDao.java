package com.eksad.latihanrest.dao;



import java.util.List;



import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;



import com.eksad.latihanrest.model.Brand;

import com.eksad.latihanrest.model.Product;





public interface ProductDao extends CrudRepository<Product, Long>

{

	/**

	 * @param brandId

	 * @return

	 */

	@Query("select p from Product p where p.brand.id = :brandId")

	public Iterable<Product> findByBrandId(@Param("brandId") Long brandId);

	

	public List<Product> findByName(String name);

	





	

	

	

	

	

	



}