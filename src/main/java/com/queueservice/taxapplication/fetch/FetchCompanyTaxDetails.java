package com.queueservice.taxapplication.fetch;

import org.springframework.data.repository.CrudRepository;

import com.queueservice.taxapplication.model.CompanyTaxInfo;

public interface FetchCompanyTaxDetails extends CrudRepository<CompanyTaxInfo, String>{

}
