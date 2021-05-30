package com.bertonisolutions.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.bertonisolutions.helpers.ReadDataFromFile;

/**
 * This class has the test for the ReadDataFromFile.java methods.
 * 
 * @author gndelossantos
 *
 */
public class ReadDataFromFileTests {
	
	static final Logger logger = Logger.getLogger(ReadDataFromFile.class);
	
	/**
	 * Test that the static method of the ReadDataFromFile can find the file in this case in the class path
	 * 
	 * @throws IOException
	 */
	@Test
	public void readPokerDataFromFileTest() throws IOException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("pokerdata.txt").getFile());
		
		List<String> readPokerDataFromFile = ReadDataFromFile.readPokerDataFromFile(file.getPath());
		
		assertTrue(!readPokerDataFromFile.isEmpty());
	}
	
	/**
	 * 
	 * Test that the static method of the ReadDataFromFile return a IOException is the file do not exists. 
	 * 
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void readPokerDataFromFileTest_IOException() throws IOException {
		
		ReadDataFromFile.readPokerDataFromFile("pokerdata.txt");
	}

}
