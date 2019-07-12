package com.cox.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cox.model.*;
import com.cox.service.ServiceCall;


@SpringBootApplication
public class CoxProjectApplication {

	 private static final Logger log = LoggerFactory.getLogger(CoxProjectApplication.class);


    @Bean
    public String getApi() {
        return "http://api.coxauto-interview.com/api/";
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ServiceCall getSampleService(String apiCallUrl, RestTemplate restTemplate) {
        return new ServiceCall(restTemplate, apiCallUrl);
    }

    @Bean
    public CommandLineRunner run(ServiceCall serviceCall) throws Exception {
        return args -> {
            try {
                DataSet dataSet = serviceCall.getDataSet();
                log.info(dataSet.toString());
//                System.out.println(dataSet.getDatasetId());
//                System.out.println(dataSet.getOp());
               // System.out.println(dataSet.toString());
                FinalJson finalJson = serviceCall.postFinalJson(dataSet.getDatasetId(), dataSet.getOp());
                log.info(finalJson.toString());
            } catch (Exception e) {
                log.error("Failed to get data set and post back to server ", e);
            }
        };
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(CoxProjectApplication.class, args);
	}

}
