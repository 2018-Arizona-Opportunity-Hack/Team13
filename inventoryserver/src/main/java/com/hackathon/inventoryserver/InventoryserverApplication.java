package com.hackathon.inventoryserver;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import models.FileStorageProperties;
import util.CategorySingleton;


@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class InventoryserverApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(InventoryserverApplication.class, args);
		CategorySingleton.getInstance();
	}
}
