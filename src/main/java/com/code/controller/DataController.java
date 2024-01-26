package com.code.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	private static final String DATA_PATH = "/tmp/data/";

	@GetMapping("/data")
	public String getData(@RequestParam(name = "n") int n, @RequestParam(name = "m", required = false) Integer m)
			throws IOException {
		String fileName = DATA_PATH + n + ".txt";
		if (m != null) {
			return getLineFromFile(fileName, m);
		} else {
			return getFileContent(fileName);
		}
	}

	private String getLineFromFile(String fileName, int lineNumber) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			int currentLine = 0;
			while ((line = reader.readLine()) != null) {
				currentLine++;
				if (currentLine == lineNumber) {
					return line;
				}
			}
		}
		return "Line not found";
	}

	private String getFileContent(String fileName) throws IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		}
		return content.toString();
	}
}