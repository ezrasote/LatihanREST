package com.eksad.latihanrest.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
//	@Temporal(TemporalType.DATE) // DATE-> menympan tanggal saja
//	@Temporal(TemporalType.TIME) // TIME-> menympan waktu saja saja
	@Temporal(TemporalType.TIMESTAMP)// TIMESTAMP -> menyimpan tanggal dan waktu, setiap tipe data "Date", sebaiknya pakai temporal
	private Date date;
	
	private String remark;
	
	
	@EqualsAndHashCode.Exclude
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction",
//			fetch = FetchType.EAGER)//defult fetch LAZY
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction",
	fetch = FetchType.LAZY)//defult fetch LAZY
	private Set<TransactionDetail> detail;
}
