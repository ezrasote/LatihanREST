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
import com.eksad.latihanrest.model.Brand;

@RestController
@RequestMapping("brand") // slash di depan kalau mau nambahin. belakang kalau mau nambahin
public class BrandController {

	// 1 buat restcontroller, abis itu bikin branddao branddao, baru tambahin
	// autowired.

	@Autowired
	BrandDao brandDao;

	@RequestMapping("getAll")
	public List<Brand> getAll() {

		List<Brand> result = new ArrayList<>();

		brandDao.findAll().forEach(result::add);

		return result;
		// pilih java util list

	}

	@RequestMapping("getOne/{id}") // {}wajib artinya. kalo request param opt
	public Brand getOne(@PathVariable Long id) {
		return brandDao.findById(id).orElse(null);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestBody Brand brand) {
		// request body membaca data apa yang akan dikirim dalam bentuk parameter brand

		// brandDao.save(brand);
		// return "Berhasil Tersimpan"; di try catch

		try {
			brandDao.save(brand);
			return "Berhasil Tersimpan";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Gagal Tersimpan";
		}
	}
//	
//	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
//	public String update(@RequestBody Brand brand, @PathVariable Long id) {
//		Brand brandSelected = brandDao.findById(id).orElse(null);
//		if (brandSelected != null) {
//			brandSelected.setName(brand.getName());
//			brandSelected.setProductType(brand.getProductType());
//
//			brandDao.save(brandSelected);
//			return "Berhasil memperbarui";
//		} else {
//			return "Gagal memperbaharui";
//		} //muncul berhasil disimpan
//----------------------------------------------------------------------------------------------------
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Brand update(@RequestBody Brand brand, @PathVariable Long id) {
		Brand brandSelected = brandDao.findById(id).orElse(null);
		if (brandSelected != null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());

			return brandDao.save(brandSelected);
		} else {
			return null;
		}

	}
	
//----------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
}
