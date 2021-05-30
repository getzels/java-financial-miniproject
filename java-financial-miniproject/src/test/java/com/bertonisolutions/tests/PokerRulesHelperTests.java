package com.bertonisolutions.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.bertonisolutions.helpers.HandRank;
import com.bertonisolutions.helpers.PokerRulesHelper;

/**
 * This class has the test for the PokerRulesHelper.java methods.
 * 
 * @author gndelossantos
 *
 */
public class PokerRulesHelperTests {
	
	static final Logger logger = Logger.getLogger(PokerRulesHelperTests.class);
	
	/**
	 * This method test the checkForRoyalFlush method. 
	 * 
	 * To get a Royal Flush the hand needs: Ten, Jack, Queen, King, Ace, in same suit. 
	 * 
	 */
	@Test
	public void checkForRoyalFlushTest() {
		
		String[] hand1 = {"TC","JC","QC","KC","AC"};
		String[] hand2 = {"QC","KC","3S","JC","KD"};
		
		HandRank handOneRank = PokerRulesHelper.checkForRoyalFlush(hand1);
		HandRank handTwoRank2 = PokerRulesHelper.checkForRoyalFlush(hand2);
		
		assertEquals(HandRank.ROYAL_FLUSH, handOneRank);
		assertNull(handTwoRank2);
	}
	
	/**
	 * This method test the checkForStraightFlush method. 
	 * 
	 * All cards are consecutive values of same suit.
     *		
	 */
	@Test
	public void checkForStraightFlushTest() {
		
		String[] hand1 = {"TC","JC","QC","KC","AC"};
		String[] hand2 = {"QC","KC","3S","JC","KD"};
		
		HandRank handRank = PokerRulesHelper.checkForStraightFlush(hand1);
		HandRank cardsRank2 = PokerRulesHelper.checkForStraightFlush(hand2);
		
		assertEquals(HandRank.STRAIGHT_FLUSH, handRank);
		assertNull(cardsRank2);
	}
	
	/**
	 * This method test the checkForStraight method. 
	 * 
	 * All cards are consecutive values.
     *		
	 */
	@Test
	public void checkForFlush() {
		String[] hand1 = {"9C","JC","QC","KC","AC"};
		String[] hand2 = {"QC","KC","3S","JC","KD"};
		
		HandRank checkForFlush = PokerRulesHelper.checkForFlush(hand1);
		HandRank checkForFlush2 = PokerRulesHelper.checkForFlush(hand2);
		
		assertEquals(HandRank.FLUSH, checkForFlush);
		assertNull(checkForFlush2);
	}
	
	/**
	 * This test validate Straight: All cards are consecutive values.

	 */
	@Test
	public void checkForStraightTest() {
		String[] hand1 = {"3C","5C","6C","4C","7C"};
		String[] hand2 = {"QC","KC","3S","JC","KD"};
		
		HandRank handRank = PokerRulesHelper.checkForStraight(hand1);
		HandRank cardsRank2 = PokerRulesHelper.checkForStraight(hand2);
		
		assertEquals(HandRank.STRAIGHT, handRank);
		assertNull(cardsRank2);
	}
	
	@Test
	public void checkForFourOfKindTest() {
		String[] hand1 = {"3C","3S","3C","4D","3D"};
		String[] hand2 = {"QC","KC","KS","JC","QD"};
		
		HandRank checkForFourOfKind = PokerRulesHelper.checkForFourOfKind(hand1);
		HandRank checkForFourOfKind2 = PokerRulesHelper.checkForFourOfKind(hand2);
		
		assertEquals(HandRank.FOUR_OF_KIND, checkForFourOfKind);
		assertNull(checkForFourOfKind2);
	} 
	
	@Test
	public void checkThreeOfAKindTest() {
		String[] hand1 = {"3C","3S","3C","4D","3D"};
		String[] hand2 = {"QC","KC","KS","JC","KD"};
		
		HandRank checkForFourOfKind = PokerRulesHelper.checkThreeOfAKind(hand1);
		HandRank checkForFourOfKind2 = PokerRulesHelper.checkThreeOfAKind(hand2);
		
		assertEquals(HandRank.THREE_OF_A_KIND, checkForFourOfKind2);
		assertNull(checkForFourOfKind);
	}
	
	@Test
	public void checkPairsTest() {
		String[] hand1 = {"3C","3S","6C","4D","8D"};
		String[] hand2 = {"QC","KC","QS","JC","KD"};
		String[] hand3 = {"QC","3C","2S","JC","KD"};
		
		HandRank checkForFourOfKind = PokerRulesHelper.checkPairs(hand1);
		HandRank checkForFourOfKind2 = PokerRulesHelper.checkPairs(hand2);
		HandRank checkForFourOfKind3 = PokerRulesHelper.checkPairs(hand3);
		
		assertEquals(HandRank.ONE_PAIR, checkForFourOfKind);
		assertEquals(HandRank.TWO_PAIRS, checkForFourOfKind2);
		assertNull(checkForFourOfKind3);
	}
	
	@Test
	public void checkFullHouseTest() {
		String[] hand1 = {"3C","3S","3D","4H","4D"};
		String[] hand2 = {"QC","KC","QS","JC","KD"};
		
		HandRank checkForFourOfKind = PokerRulesHelper.checkFullHouse(hand1);
		HandRank checkForFourOfKind2 = PokerRulesHelper.checkFullHouse(hand2);
		
		assertEquals(HandRank.FULL_HOUSE, checkForFourOfKind);
		assertNull(checkForFourOfKind2);
	}
	
	@Test
	public void getHighCardTest() {
		String[] hand1 = {"9C","JC","QC","KC","AC"};
		String[] hand2 = {"QC","KC","QS","JC","KD"};
		
		int highCard = PokerRulesHelper.getHighCard(hand1);
		int highCard2 = PokerRulesHelper.getHighCard(hand2);
		
		assertEquals(1, highCard);
		assertEquals(13, highCard2);
	}
	
	/**
	 * This test help to validate that the getHandRank return the correct HandRank
	 */
	@Test
	public void getHandRankTest() {
		String[] hand1 = {"3C","3S","3D","4H","4D"};
		
		String[] hand2 = {"3C","3S","6C","4D","8D"};
		String[] hand3 = {"QC","KC","QS","JC","KD"};
		
		String[] hand4 = {"TC","JC","QC","KC","AC"};
		
		HandRank handRank1 = PokerRulesHelper.getHandRank(hand1);
		
		HandRank handRank2 = PokerRulesHelper.getHandRank(hand2);
		HandRank handRank3 = PokerRulesHelper.getHandRank(hand3);
		
		HandRank handRank4 = PokerRulesHelper.getHandRank(hand4);
		
		assertEquals(HandRank.FULL_HOUSE, handRank1);
		
		assertEquals(HandRank.ONE_PAIR, handRank2);
		assertEquals(HandRank.TWO_PAIRS, handRank3);
		
		assertEquals(HandRank.ROYAL_FLUSH, handRank4);
		
	}
	
	/**
	 * Test the validation of the rank of to Four of kind.
	 */
	@Test
	public void fourOfKindRankComparatorTest() {
		String[] hand1 = {"3C","3S","3C","4D","3D"};
		String[] hand2 = {"5C","5S","5C","4D","5D"};
		
		String[] hand3 = {"3C","3S","3C","4D","3D"};
		String[] hand4 = {"3C","3S","3C","4D","3D"};
		
		int winner = PokerRulesHelper.fourOfKindRankComparator(hand1,hand2);
		int winner2 = PokerRulesHelper.fourOfKindRankComparator(hand3,hand4);
		
		assertEquals(2, winner);
		assertEquals(0, winner2);
	}
	
	@Test
	public void threeOfKindRankComparatorTest() {
		String[] hand1 = {"3C","3S","3C","4D","5D"};
		String[] hand2 = {"5C","5S","5C","4D","7D"};
		
		String[] hand3 = {"3C","3S","3C","4D","9D"};
		String[] hand4 = {"3C","3S","3C","4D","8D"};
		
		int winner = PokerRulesHelper.threeOfKindRankComparator(hand1,hand2);
		int winner2 = PokerRulesHelper.threeOfKindRankComparator(hand3,hand4);
		
		assertEquals(2, winner);
		assertEquals(0, winner2);
	}
	
	@Test
	public void onePairComparatorTest() {
		String[] hand1 = {"3C","3S","8C","4D","5D"};
		String[] hand2 = {"5C","5S","2C","4D","7D"};
		
		String[] hand3 = {"3C","3S","9C","4D","9D"};
		String[] hand4 = {"3C","3S","7C","4D","8D"};
		
		int winner = PokerRulesHelper.onePairComparator(hand1,hand2);
		int winner2 = PokerRulesHelper.onePairComparator(hand3,hand4);
		
		assertEquals(2, winner);
		assertEquals(0, winner2);
	}
	
	@Test
	public void fullHouseComparatorTest() {
		String[] hand1 = {"5C","5S","2C","2D","5D"};
		String[] hand2 = {"3C","3S","4C","4D","3D"};
		
		String[] hand3 = {"3C","3S","9C","9D","3D"};
		String[] hand4 = {"3C","3S","4C","4D","3D"};
		
		int winner = PokerRulesHelper.onePairComparator(hand1,hand2);
		int winner2 = PokerRulesHelper.onePairComparator(hand3,hand4);
		
		assertEquals(2, winner);
		assertEquals(1, winner2);
	}
	
	@Test
	public void twoPairComparatorTest() {
		String[] hand1 = {"5C","5S","2C","2D","9D"};
		String[] hand2 = {"QC","KC","QS","JC","KD"};
		
		String[] hand3 = {"3C","3S","9C","9D","5D"};
		String[] hand4 = {"3C","3S","4C","4D","KD"};
		
		int winner = PokerRulesHelper.twoPairComparator(hand1,hand2);
		int winner2 = PokerRulesHelper.twoPairComparator(hand3,hand4);
		
		assertEquals(2, winner);
		assertEquals(1, winner2);
	}
	
	
	
	

}
