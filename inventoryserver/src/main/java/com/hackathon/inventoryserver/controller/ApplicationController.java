package com.hackathon.inventoryserver.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.inventoryserver.service.FileStorageService;

import models.Response;
import util.ExtractCSV;

@RestController
public class ApplicationController {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/csv/{year}/{month}")
	public Response uploadFile(@RequestParam("file") MultipartFile file, @PathParam("month") String month,
			@PathParam("year") String year) {
		String fileName = fileStorageService.storeFile(file);
		System.out.println(fileName + "::" + month + "::" + year);
		File convFile = new File(fileName);
	
		try {
			FileReader fr = new FileReader(convFile);
			ExtractCSV.readCSVFile(fr);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return new Response(200, "success");
	}

	@GetMapping("/csv/{year}/{month}")
	public Response exportCsv(@RequestParam("file") MultipartFile file, @PathParam("month") String month,
			@PathParam("year") String year) {
		return new Response(200, "success");
	}

}
