package com.queueservice.taxapplication.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.queueservice.taxapplication.fetch.FetchCompanyTaxDetails;
import com.queueservice.taxapplication.model.CompanyTaxInfo;
import com.queueservice.taxapplication.model.TaxSlabModel;

@RestController
@CrossOrigin(origins = "*")
public class AwsSQSController {
	
	@Autowired
	FetchCompanyTaxDetails fetchCompTax;
	
	Logger logger= LoggerFactory.getLogger(AwsSQSController.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    public String endpoint;

    @GetMapping("/send/{message}")
    public void sendMessageToQueue(@PathVariable String message) {
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/send/{companycode}/{employeeid}/{financialyear}/{tax}/{mobile}")
    public void sendTaxInfoToQueue(@PathVariable String companycode, @PathVariable String employeeid,
    		@PathVariable String financialyear, @PathVariable String tax, @PathVariable String mobile 
    		) throws JsonProcessingException {
    	
    	CompanyTaxInfo comptax = new CompanyTaxInfo(employeeid, companycode, financialyear, tax, mobile);
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonMsg = mapper.writeValueAsString(comptax); 
    	
    	System.out.println(jsonMsg);
    	
    	
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(jsonMsg).build());
        
    }
    
    @GetMapping("/getcomptax")
    public List<CompanyTaxInfo> getCompanyTax() {
        List<CompanyTaxInfo> compTax = new ArrayList<CompanyTaxInfo>();
        fetchCompTax.findAll().forEach(compTax::add);
        return compTax;
    }
    
    @GetMapping(path = "getcomptax/{id}")
	public Optional<CompanyTaxInfo> getTaxDetail(@PathVariable("id") String id) {
		  return fetchCompTax.findById(id);
		  
	}
    
    @SqsListener("tax")
    public void loadMessageFromSQS(String message) throws JsonMappingException, JsonProcessingException  {
        logger.info("message from SQS Queue {}",message);
        ObjectMapper mapper = new ObjectMapper();
        CompanyTaxInfo receivedTax = mapper.readValue(message, CompanyTaxInfo.class);
        System.out.println("After calling sqs");
        System.out.println(receivedTax.getCompanyCode() + " " + receivedTax.getEmployeeId());
        
        fetchCompTax.save(receivedTax);
        
        SendSms.sendSms("Dear Employee, Your tax received successfully !"+ new Date().toLocaleString(), "9075539986");
    }

}
