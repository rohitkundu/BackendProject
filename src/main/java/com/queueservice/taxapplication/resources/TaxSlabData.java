package com.queueservice.taxapplication.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.queueservice.taxapplication.fetch.FetchTax;
import com.queueservice.taxapplication.model.TaxSlabModel;



@RestController
@Service
@CrossOrigin(origins = "*")
public class TaxSlabData {
	
	@Autowired
	FetchTax fetchTax;
	
	@GetMapping(path = "gettax")
	public List<TaxSlabModel> getTax() {
		List<TaxSlabModel> tax = new ArrayList<TaxSlabModel>();
		fetchTax.findAll().forEach(tax::add);
		return tax;
	}

	@GetMapping(path = "gettax/{id}")
	public Optional<TaxSlabModel> getTaxDetail(@PathVariable("id") Integer id) {
		  return fetchTax.findById(id);
		  
	}
	
	@PostMapping(path = "gettax")
	public void addTaxSlab(@RequestBody TaxSlabModel taxSlab) {
		fetchTax.save(taxSlab);
	}
	
	@PutMapping(path = "gettax/{id}")
	public void updateTaxSlab(@PathVariable("id") String id,@RequestBody TaxSlabModel taxSlab) {
		fetchTax.save(taxSlab);
	}
	
	@DeleteMapping(path = "deletetax/{id}")
	public void deleteTaxSlab(@PathVariable("id") String id) throws NumberFormatException{
		int eid = Integer.parseInt(id);
		fetchTax.deleteById(eid);
	}
	
	@GetMapping(path = "gettax/{taxDeducted}/{totalAmount}/{year}")
	public TaxSlabModel calculateTax(@PathVariable("taxDeducted") int taxDeducted,
			@PathVariable("totalAmount") int totalAmount,
			@PathVariable("year") int year) {
		
		TaxSlabModel tax = fetchTax.findByFinancialYear(year);
		return tax;
		
	}
	
}
