package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.dao.ProductDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductDao productDao;

	@Autowired
	BrandDao brandDao;

	@RequestMapping("getByBrandId/{brandId}") // gaboleh ada spasi disini
	public List<Product> getByBrandId(@PathVariable long brandId) {
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		return result;
	}

	// -------------------------------------------------------------------
//	@RequestMapping(value = "save", method = RequestMethod.POST)
//	public Product save(@RequestBody Product product) {
//			return productDao.save(product);
//		}
//		
	// save product dengan memanggil brand id
	// --------------------------------------------------------------------
	//
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Product save(@RequestBody Product product) {
		Brand brand = brandDao.findById(product.getBrandId()).orElse(null);
		if (brand != null) {
			product.setBrand(brand);
			return productDao.save(product);
		}
		return null;

	}

//-------------------------------------------------------------------------
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		Product productSelected = productDao.findById(id).orElse(null);
		if (productSelected != null) {
			productSelected.setName(product.getName());
			productSelected.setBrandId(product.getBrandId());
			productSelected.setPrice(product.getPrice());
			productSelected.setBrand(product.getBrand());

			return productDao.save(productSelected);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
	
	@RequestMapping(value = "getByName/{name}", method = RequestMethod.GET)

	public List<Product> getByName(@PathVariable String name) {

		List<Product> result = new ArrayList<Product>();

		productDao.findByName(name).forEach(result::add);

		

		return result;
	}
}