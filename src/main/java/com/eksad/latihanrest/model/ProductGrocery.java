package com.eksad.latihanrest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "product_grocery")
@PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("Grocery")

public class ProductGrocery extends Product{

	
	@Column(name ="expiry_date", nullable = false)
	@Temporal(TemporalType.DATE) //date tanggal + waktu. yang disimpan tanggal doang, sehingga ngasitau datenya aja
	private Date expiryDate;
	
	
}
