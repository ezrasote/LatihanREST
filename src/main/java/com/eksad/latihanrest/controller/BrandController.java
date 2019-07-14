package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.model.Brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = "Brand")
public class BrandController {
	@Autowired
	BrandDao brandDao;
	@ApiOperation(
			value = "API to retrieve all Brand's data",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getAllBrand")
	public List<Brand> getAll() {
		List<Brand> result = new ArrayList<>();
		
		brandDao.findAll().forEach(result::add);
	
		return result;
		
	}
	@ApiOperation(
			value = "API to retrieve one Brand's data",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getOneBrand/{id}")
	public Brand getOne(@PathVariable Long id) {
		return brandDao.findById(id).orElse(null);
	}
	@ApiOperation(
			value = "Saving new Brand's data",
		    notes = "Saving new Brand's data to database",
		    tags = "Data Manipulation API"
			)
	@PostMapping("saveBrand")
	public Brand save(@RequestBody Brand brand) {
		try {
			
			
			return brandDao.save(brand);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}
	@ApiOperation(
			value = "Update Brand's data",
		    notes = "Update Brand's data to database",
		    tags = "Data Manipulation API"
			)
	@PutMapping("updateBrand/{id}")
	public Brand update(@RequestBody Brand brand, @PathVariable Long id) {
		
		Brand brandSelected = brandDao.findById(id).orElse(null);
		
		if (brandSelected !=null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());
			
			
			return brandDao.save(brandSelected);
		}else {
			return null;
		}
	}
	@ApiOperation(
			value = "Delete Brand's data",
		    notes = "Delete Brand's data to database",
		    tags = "Data Manipulation API"
			)
	@DeleteMapping("deleteBrand/{id}")
	public HashMap<String, Object> delete (@PathVariable Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;

	}
}