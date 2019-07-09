package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "product_electronic")
@PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("Electronic")

public class ProductElectronic extends Product{

	@Column(name ="power_consumption", nullable = false)
	private String powerConsumption;
	
	
}
