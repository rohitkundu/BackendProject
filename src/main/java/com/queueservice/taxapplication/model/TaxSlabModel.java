package com.queueservice.taxapplication.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "taxslab")
public class TaxSlabModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private int id;
	
	@Column(name="financialyear")
	Integer financialYear;
	
	@Column(name="startincome")
	Integer startIncome;
	
	@Column(name="endincome")
	Integer endIncome;
	
	@Column(name="taxpercentage")
	Integer taxPercentage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getStartIncome() {
		return startIncome;
	}

	public void setStartIncome(Integer startIncome) {
		this.startIncome = startIncome;
	}

	public Integer getEndIncome() {
		return endIncome;
	}

	public void setEndIncome(Integer endIncome) {
		this.endIncome = endIncome;
	}

	public Integer getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Integer taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	

}
