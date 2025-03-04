package com.equiposseseaz.SistemaControlEquiposSESEAZ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SistemaControlEquiposSeseazApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaControlEquiposSeseazApplication.class, args);
	}

}
