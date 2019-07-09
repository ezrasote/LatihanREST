package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter // auto add Getter Setter method (harus install lombok terlebih dahulu)
//@EqualsAndHashCode
//===atau
@Data
@EqualsAndHashCode(callSuper = true)//callSuper = true => untuk mengikut sertakan properti di superclass
@ToString(callSuper = true)
//=================
@Entity
@Table(name ="Brand")

public class Brand extends BaseEntity{
	
//	@Id
//	@GeneratedValue(strategy =  GenerationType.IDENTITY) // auto increment
//	private Long id; // dikomen karna sudah didefine di class BaseEntity
	
	@Column(nullable = false)// not null
	private String name;
	
	@Column(name = "product_type")
	private String productType;
}
