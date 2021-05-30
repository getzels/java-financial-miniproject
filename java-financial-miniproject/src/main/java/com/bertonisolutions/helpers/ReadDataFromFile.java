package com.bertonisolutions.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * This class a helper that have the method needed to read data from a file
 * 
 * @author gndelossantos
 *
 */
public class ReadDataFromFile {

	static final Logger logger = Logger.getLogger(ReadDataFromFile.class);

	private ReadDataFromFile() {
		//Constructor private to avoid initialization. 
	}

	/**
	 * This method read the data from a file a return a list of the line
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static List<String> readPokerDataFromFile(String fileName) throws IOException {

		List<String> lines = new ArrayList<>();

		//Use try-with-resource to automatically close the file.
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {	

			String line;
			//Get the line from the BufferedReader and check if if null
			//The loop while will execute until no line is found.
			while((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}

			return lines;
		} catch (IOException e) {
			logger.error("Error reading the file", e);
			throw e; 
		}
	}

}
