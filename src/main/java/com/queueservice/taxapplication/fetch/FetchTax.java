package com.queueservice.taxapplication.fetch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.queueservice.taxapplication.model.TaxSlabModel;


@Repository
public interface FetchTax extends CrudRepository<TaxSlabModel, Integer>, CustomRepository<TaxSlabModel, Integer>{

}
