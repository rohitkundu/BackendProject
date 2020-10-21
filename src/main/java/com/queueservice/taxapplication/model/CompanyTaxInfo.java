package com.queueservice.taxapplication.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Entity
@Table(name = "companytaxinfo")
public class CompanyTaxInfo {
	
	@Id
    @Column(name = "employeeid")
	public String employeeId;
	
	@Column(name="companycode")
	private String companyCode;
	
	@Column(name="financialyear")
	private String financialYear;
	
	@Column(name="tax")
	private String tax;
	
	@Column(name="mobile")
	private String mobile;

	public String getEmployeeId() {
		return employeeId;
	}
	
	public CompanyTaxInfo() {
		
	}

	public CompanyTaxInfo(String employeeId, String companyCode, String financialYear, String tax, String mobile) {
		
		this.employeeId = employeeId;
		this.companyCode = companyCode;
		this.financialYear = financialYear;
		this.tax = tax;
		this.mobile = mobile;
	}



	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
