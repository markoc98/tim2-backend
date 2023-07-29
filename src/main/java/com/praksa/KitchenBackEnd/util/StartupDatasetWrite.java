package com.praksa.KitchenBackEnd.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;

@Component
public class StartupDatasetWrite implements ApplicationListener<ApplicationReadyEvent>{

	
	private static final Logger logger = Logger.getLogger(StartupDatasetWrite.class.getName());
	@Value("${spring.jpa.hibernate.ddl-auto}")
	String ddlStatus;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event){
		logger.info("DDL Status: "  + ddlStatus);
		if(ddlStatus.equals("update")) {
			logger.info("Populating database");
			InputStream resource;
			CSVReader reader;
		
				try {
					resource = new ClassPathResource("data/ingredients.csv").getInputStream();
					reader = new CSVReaderBuilder(new InputStreamReader(resource)).build();	
					@SuppressWarnings({ "rawtypes", "unchecked" })
					List<Ingredient> ingredients = new CsvToBeanBuilder(reader)
							.withType(Ingredient.class)
							.build().parse();
					for(Ingredient ingredient: ingredients) {
						logger.info(ingredient.getName() + " " + ingredient.getCalories());
					}
					logger.info("Number of ingredients " + ingredients.size());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			
		} else
			logger.info("Skipping DB write");
	}

}

//try {
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	List<Ingredient> ingredients = new CsvToBeanBuilder(new FileReader(file))
//			.withType(Ingredient.class).build().parse();
////	repo.saveAll(ingredients);
//	for(Ingredient ingredient : ingredients) {
//		System.out.println(ingredient);
//		repo.save(ingredient);
//	}
//	
//} catch (IllegalStateException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (FileNotFoundException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

