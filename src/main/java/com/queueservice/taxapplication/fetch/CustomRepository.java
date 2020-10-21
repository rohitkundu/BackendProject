package com.queueservice.taxapplication.fetch;

import com.queueservice.taxapplication.model.TaxSlabModel;

public interface CustomRepository<T, I> {
	
	public TaxSlabModel findByFinancialYear(int year);

}
