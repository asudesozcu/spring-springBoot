package com.exercise.businesscalculationservise;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component 
public class BusinesscalculationserviseApplication {
	private DataService dataService;

	public BusinesscalculationserviseApplication(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int FindMax() {
		return Arrays.stream(dataService.retrieveData()).max().orElse(0);
	}

	public static void main(String[] args) {

	}

}
