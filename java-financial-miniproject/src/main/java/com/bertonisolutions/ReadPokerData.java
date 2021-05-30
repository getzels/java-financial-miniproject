/**
 * 
 */
package com.bertonisolutions;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bertonisolutions.helpers.HandRank;
import com.bertonisolutions.helpers.PokerRulesHelper;
import com.bertonisolutions.helpers.ReadDataFromFile;

/**
 * This class has the main entry point of the application.
 * 
 * @author gndelossantos
 *
 */
public class ReadPokerData {
	
	static final Logger logger = Logger.getLogger(ReadPokerData.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
		
		logger.info("Enter the fully qualified name of the file:");
		String filePath = scanner.next();
		scanner.close();
		
		try {
			List<String> fileLines = ReadDataFromFile.readPokerDataFromFile(filePath);
			
			getTheWinHand(fileLines);
		} catch (IOException e) {
			logger.error("Error reading the file: ",e);			
		}
	}
	
	
	/**
	 * 
	 * @param lines
	 * @return
	 */
	public static void getTheWinHand(List<String> lines) {
		
		/*Arrays that define the two hands each one with 5 cards*/
		String[] handOne = new String[5];
		String[] handTwo = new String[5];	
		
		int handOneW = 0;
		int handTwoW = 0;
		int handTie = 0;
		
		int theWinner = 0;
		
		//This variable will sum the franquency of each hand 
		int winFrequencyPlayerOneCounter = 0;
		int winFrequencyPlayerTwoCounter = 0;
		
		HandRank handOneRank;
		HandRank handTwoRank;
				
		for(String line: lines) {
			setHands(handOne,handTwo, line);			

			handOneRank = PokerRulesHelper.getHandRank(handOne);
			handTwoRank = PokerRulesHelper.getHandRank(handTwo);	
			
			winFrequencyPlayerOneCounter += handOneRank.value;
			winFrequencyPlayerTwoCounter += handTwoRank.value;
			
			if(handOneRank == handTwoRank) {
				theWinner = compareHandRank(handOne, handTwo, handOneRank);
			}else {
				if(handOneRank.value < handTwoRank.value) {
					theWinner = 1;
				}else {
					theWinner = 2;
				}
			}
			
			if(theWinner == 1) {
				handOneW++;
			}else if(theWinner == 2) {
				handTwoW++;
			}else {
				handTie++;
			}
		}
		
		logger.info("1: " + handOneW);
		logger.info("2: " + handTwoW);
		logger.info("3: " + handTie);
		
		logger.info("---------PLAYER 1 --------- | ------ PLAYER 2 --------------");
		logger.info(String.format("         %.2f%%             |        %.2f%%",
				//The line size represents the numbers of hand in the file.
				calculateProbability(winFrequencyPlayerOneCounter, lines.size()), 
				calculateProbability(winFrequencyPlayerTwoCounter, lines.size())));
	} 
	
	private static double calculateProbability(final long winFrequencyPlayerOneCounter, final long totalOfHand) {
		long totalFrequency = 2598960L * totalOfHand;
		
		return (((double)winFrequencyPlayerOneCounter/totalFrequency) * 100);
	}


	/**
	 * This method slip the line into two array, setting the hand one and two.
	 * 
	 * @param handOne
	 * @param handTwo
	 * @param line
	 */
	private static void setHands(String[] handOne, String[] handTwo, String line) {
		System.arraycopy(line.split(" "), 0, handOne, 0, 5);
		System.arraycopy(line.split(" "), 5, handTwo, 0, 5);
	}
	
	/**
	 * This method get the winner of two hand if the hand are the same it use the {@link compareHandRank}
	 * to compare the rank of the hand.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int getTheWinner(String[] playerOne, String[] playerTwo) {
		HandRank handOneRank = PokerRulesHelper.getHandRank(playerOne);
		HandRank handTwoRank = PokerRulesHelper.getHandRank(playerTwo);	

		if(handOneRank == handTwoRank) {
			return compareHandRank(playerOne, playerTwo, handOneRank);
		}

		return handOneRank.value < handTwoRank.value ? 1 : 2;  
	}

	/**
	 * This method serve as a helper to compare two hand and get the rank
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @param handOneRank
	 * @return
	 */
	private static int compareHandRank(String[] playerOne, String[] playerTwo, HandRank handOneRank) {

		switch (handOneRank) {
		case ROYAL_FLUSH:
			return 0;
		case STRAIGHT_FLUSH:	
			return PokerRulesHelper.noPairRankComparator(playerOne, playerTwo);
		case FOUR_OF_KIND:	
			return PokerRulesHelper.fourOfKindRankComparator(playerOne, playerTwo);	
		case FULL_HOUSE:	
			return PokerRulesHelper.fullHouseComparator(playerOne, playerTwo);	
		case FLUSH:	
			return PokerRulesHelper.noPairRankComparator(playerOne, playerTwo);	
		case STRAIGHT:	
			return PokerRulesHelper.noPairRankComparator(playerOne, playerTwo);	
		case THREE_OF_A_KIND:	
			return PokerRulesHelper.noPairRankComparator(playerOne, playerTwo);	
		case TWO_PAIRS:	
			return PokerRulesHelper.twoPairComparator(playerOne, playerTwo);	
		case ONE_PAIR:	
			return PokerRulesHelper.onePairComparator(playerOne, playerTwo);	
		case HIGH_CARD:	
			return PokerRulesHelper.noPairRankComparator(playerOne, playerTwo);	
		default:
			break;
		}

		return 0;
	}



	
	

}
