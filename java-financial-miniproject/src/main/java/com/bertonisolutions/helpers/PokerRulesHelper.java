package com.bertonisolutions.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * 
 * This class has the method needed to determine which hand is the winner.
 * 
 * @author gndelossantos
 *
 */
public class PokerRulesHelper {

	static final Logger logger = Logger.getLogger(PokerRulesHelper.class);

	private PokerRulesHelper() {
		//private constructor to avoid initialization. 
	}

	

	/**
	 * This method compare all not pair values ranks.
	 * 
	 * Each straight is ranked by the rank of its highest-ranking card.
	 * 
	 * Each flush is ranked first by the rank of its highest-ranking card, 
	 * then by the rank of its second highest-ranking card, then by the 
	 * rank of its third highest-ranking card, then by the rank of its 
	 * fourth highest-ranking card, and finally by the rank of its 
	 * lowest-ranking card.
	 * 
	 * 
	 * 
	 */
	public static int noPairRankComparator(String[] playerOne, String[] playerTwo) {
		Integer[] playerOneValues = getCardsValues(playerOne);
		Integer[] playerTwoValues = getCardsValues(playerTwo);

		Arrays.sort(playerOneValues, Collections.reverseOrder());
		Arrays.sort(playerTwoValues, Collections.reverseOrder());

		for(int i = 0; i < playerOne.length; i++) {
			if(!playerOneValues[i].equals(playerTwoValues[2]))
				return playerOneValues[i] > playerTwoValues[2] ? 1 : 2;
		}
		return 0;
	}

	/**
	 * Each four of a kind is ranked first by the rank of its quadruplet, and then by the rank of its kicker.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int fourOfKindRankComparator(String[] playerOne, String[] playerTwo) {
		Map<String, Integer> playerOneHowManyTimesAppears = howManyTimesAppears(playerOne);
		Optional<String> optional = playerOneHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 4).map(Map.Entry::getKey).findFirst();

		Map<String, Integer> playerTwoHowManyTimesAppears = howManyTimesAppears(playerTwo);
		Optional<String> optional2 = playerTwoHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 4).map(Map.Entry::getKey).findFirst();

		Cards playOneHighCard = null;
		Cards playTwoHighCard = null;
		
		if(optional.isPresent()) {
			playOneHighCard = Cards.getCardByKey(optional.get());
		}

		if(optional2.isPresent()) {
			playTwoHighCard = Cards.getCardByKey(optional2.get());
		}

		if((playOneHighCard != null && playTwoHighCard != null) && !playOneHighCard.getRank().equals(playTwoHighCard.getRank()))
			return playOneHighCard.getRank() > playTwoHighCard.getRank() ? 1 : 2;

			return 0;
	}
	
	/**
	 * Each three of a kind is ranked first by the rank of its triplet, 
	 * then by the rank of its highest-ranking kicker, and finally by 
	 * the rank of its lowest-ranking kicker.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int threeOfKindRankComparator(String[] playerOne, String[] playerTwo) {
		Map<String, Integer> playerOneHowManyTimesAppears = howManyTimesAppears(playerOne);
		Optional<String> optional = playerOneHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 3).map(Map.Entry::getKey).findFirst();

		Map<String, Integer> playerTwoHowManyTimesAppears = howManyTimesAppears(playerTwo);
		Optional<String> optional2 = playerTwoHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 3).map(Map.Entry::getKey).findFirst();

		Cards playOneHighCard = null;
		Cards playTwoHighCard = null;
		
		if(optional.isPresent()) {
			playOneHighCard = Cards.getCardByKey(optional.get());
		}

		if(optional2.isPresent()) {
			playTwoHighCard = Cards.getCardByKey(optional2.get());
		}

		if((playOneHighCard != null && playTwoHighCard != null) && !playOneHighCard.getRank().equals(playTwoHighCard.getRank()))
			return playOneHighCard.getRank() > playTwoHighCard.getRank() ? 1 : 2;

			return 0;
	}
	
	/**
	 * Each two pair is ranked first by the rank of its highest-ranking pair, 
	 * then by the rank of its lowest-ranking pair, and finally by the rank of 
	 * its kicker.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int twoPairComparator(String[] playerOne, String[] playerTwo) {
		Map<String, Integer> playerOneHowManyTimesAppears = howManyTimesAppears(playerOne);
		Stream<String> stream = playerOneHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 2).map(Map.Entry::getKey).sorted();

		Map<String, Integer> playerTwoHowManyTimesAppears = howManyTimesAppears(playerTwo);
		Stream<String> stream2 = playerTwoHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 2).map(Map.Entry::getKey).sorted();
		
		int playerOneRank;
		int playerTwoRank;
		
		playerOneRank = stream.map(Cards::getCardByKey).map(Cards::getRank).mapToInt(x -> x).max().getAsInt();
		playerTwoRank = stream2.map(Cards::getCardByKey).map(Cards::getRank).mapToInt(x -> x).max().getAsInt();
		
		if(playerOneRank == playerTwoRank) {
			playerOneRank = stream.map(Cards::getCardByKey).map(Cards::getRank).mapToInt(x -> x).max().getAsInt();
			playerTwoRank = stream2.map(Cards::getCardByKey).map(Cards::getRank).mapToInt(x -> x).max().getAsInt();
		}
		return playerOneRank > playerTwoRank ? 1 : 2;
	}
	
	/**
	 * Each one pair is ranked first by the rank of its pair, then by the rank of its 
	 * highest-ranking kicker, then by the rank of its second highest-ranking kicker, 
	 * and finally by the rank of its lowest-ranking kicker.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int onePairComparator(String[] playerOne, String[] playerTwo) {
		Map<String, Integer> playerOneHowManyTimesAppears = howManyTimesAppears(playerOne);
		Optional<String> optional = playerOneHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 2).map(Map.Entry::getKey).findFirst();

		Map<String, Integer> playerTwoHowManyTimesAppears = howManyTimesAppears(playerTwo);
		Optional<String> optional2 = playerTwoHowManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 2).map(Map.Entry::getKey).findFirst();

		Cards playOneHighCard = null;
		Cards playTwoHighCard = null;
		
		if(optional.isPresent()) {
			playOneHighCard = Cards.getCardByKey(optional.get());
		}

		if(optional2.isPresent()) {
			playTwoHighCard = Cards.getCardByKey(optional2.get());
		}

		if((playOneHighCard != null && playTwoHighCard != null) && !playOneHighCard.getRank().equals(playTwoHighCard.getRank()))
			return playOneHighCard.getRank() > playTwoHighCard.getRank() ? 1 : 2;

			return 0;
	}
	
	/**
	 * Each full house is ranked first by the rank of its triplet, and then by the rank of its pair. 
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public static int fullHouseComparator(String[] playerOne, String[] playerTwo) {
		
		int winner = threeOfKindRankComparator(playerOne, playerTwo);
		
		return winner == 0 ? onePairComparator(playerOne, playerTwo) : winner;
	} 

	/**
	 * This method determine the HandRank
	 * 
	 * @param cardsArray
	 * @return
	 */
	public static HandRank getHandRank(String[] cardsArray) {
		HandRank cardsRankTemp = null;

		cardsRankTemp = checkForRoyalFlush(cardsArray);		
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkForStraightFlush(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkForFourOfKind(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkFullHouse(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkForFlush(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkForStraight(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkThreeOfAKind(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		cardsRankTemp = checkPairs(cardsArray);
		if(cardsRankTemp != null) {return cardsRankTemp;}

		return HandRank.HIGH_CARD;
	}

	/**
	 * This method validate is the hand is flush.
	 * 
	 * All cards of the same suit.
	 * 
	 * @param hand {@link String[]}
	 * 
	 */
	public static HandRank checkForFlush(String[] hand) {
		boolean isFlush = true;

		if(hand != null) {
			//Look for the last char to know it familly.
			char suit = getTheSuit(hand);	

			for(String card: hand) {
				if(card.charAt(card.length() - 1) != suit) {
					isFlush = false;
					break;
				} 	
			}
		}
		return isFlush ? HandRank.FLUSH : null;
	}

	/**
	 * To get a Royal Flush the hand needs: Ten, Jack, Queen, King, Ace, in same suit.
	 * 
	 * @param hand
	 */
	public static HandRank checkForRoyalFlush(String[] hand) {
		boolean isRoyalFlush = false;

		if(hand != null && checkForFlush(hand) != null) {
			List<String> asList = Arrays.asList(hand);

			char suit = getTheSuit(hand);

			isRoyalFlush = asList.contains(Cards.TEN.getName()+suit);
			isRoyalFlush = asList.contains(Cards.JACK.getName()+suit) && isRoyalFlush;
			isRoyalFlush = asList.contains(Cards.QUEEN.getName()+suit) && isRoyalFlush;
			isRoyalFlush = asList.contains(Cards.KING.getName()+suit) && isRoyalFlush;
			isRoyalFlush = asList.contains(Cards.ACE.getName()+suit) && isRoyalFlush;
		}
		return isRoyalFlush ? HandRank.ROYAL_FLUSH : null; 
	}

	/**
	 * This method validate is the hand is straight 
	 * 
	 * @param hand
	 * @return
	 */
	public static HandRank checkForStraightFlush(String[] hand) {
		HandRank handRank = null;

		if(hand != null && checkForFlush(hand) != null && checkForStraight(hand) != null) {
			handRank = HandRank.STRAIGHT_FLUSH;
		}
		return handRank;
	}

	/**
	 * This method validate is a hand of card is Straight
	 * 
	 * @param handArray
	 * @return
	 */
	public static HandRank checkForStraight(String[] handArray) {
		HandRank handRank = null;

		if(handArray != null && isStraight(getCardsValues(handArray))) {
			handRank = HandRank.STRAIGHT;
		}

		return handRank; 
	}

	/**
	 * Helper method the get the value of a hand of cards.
	 * 
	 * @param object
	 * @return
	 */
	private static Integer[] getCardsValues(String... object) {
		String value = null;
		var length = object.length;	
		Integer[] handOrder = new Integer[length];

		for(int i = 0; i < length; i++) {
			value = getValueFromCard(object[i]);
			handOrder[i] = Cards.getCardByKey(String.valueOf(value)).getRank();
		}
		return handOrder;
	}

	/**
	 * High Card: Highest value card.
	 * 
	 * @param handArray
	 * @return
	 */
	public static int getHighCard(String[] handArray) {
		Integer[] cardsValues = getCardsValues(handArray);

		return cardsValues[0] == 1 ? 1 : cardsValues[cardsValues.length -1];  
	}

	/**
	 * Sort and validate that the array element are a straight.
	 * 
	 * @param handOrder
	 * @return
	 */
	private static boolean isStraight(Integer[] handOrder) {
		var isStraight = true;
		Arrays.sort(handOrder);

		if(handOrder[0] == 1 && handOrder[1] == 10 && handOrder[2] == 11 && handOrder[3] == 12 && handOrder[4] == 13) {
			isStraight = true;
		}else {
			isStraight = true;
			for(int i = 0; i < handOrder.length -1; i++) {
				//validate that the next value follow the index.
				if(handOrder[i+1] != (handOrder[i] + 1)) {
					isStraight = false;
					break;
				}
			}
		}
		return isStraight;
	}

	/**
	 *This method validate if the hand is a  FourOfKind
	 * 
	 * Four of a Kind: Four cards of the same value.
	 * 
	 * @param handArray
	 * @return
	 */
	public static HandRank checkForFourOfKind(String[] handArray) {
		Map<String, Integer> howManyTimesAppears = howManyTimesAppears(handArray);

		if(!howManyTimesAppears.containsValue(4)) {return null;}

		return HandRank.FOUR_OF_KIND;
	} 

	/**
	 * Three of a Kind: Three cards of the same value.
	 * 
	 * @param handArray
	 * @return
	 */
	public static HandRank checkPairs(String[] handArray) {
		Map<String, Integer> howManyTimesAppears = howManyTimesAppears(handArray);

		long count = howManyTimesAppears.entrySet().stream().filter(entry -> entry.getValue() == 2).map(Map.Entry::getKey).count();

		HandRank handRank = null;
		if(count == 1) {
			handRank =  HandRank.ONE_PAIR;
		} else if(count == 2) {
			handRank = HandRank.TWO_PAIRS;
		}
		return handRank;
	}

	/**
	 * Full House: Three of a kind and a pair.	 * 
	 * 
	 * @param handArray
	 * @return
	 */
	public static HandRank checkFullHouse(String[] handArray) {
		HandRank handRank = null;
		if (checkThreeOfAKind(handArray) == HandRank.THREE_OF_A_KIND && 
				checkPairs(handArray) == HandRank.ONE_PAIR) {
			handRank = HandRank.FULL_HOUSE;
		}

		return handRank;
	}

	/**
	 * Three of a Kind: Three cards of the same value.
	 * 
	 * @param handArray
	 * @return
	 */
	public static HandRank checkThreeOfAKind(String[] handArray) {
		Map<String, Integer> howManyTimesAppears = howManyTimesAppears(handArray);

		if(!howManyTimesAppears.containsValue(3)) {return null;}

		return HandRank.THREE_OF_A_KIND;
	}



	/**
	 * This method help to determine the numbers of times a value appears in the given array.
	 * 
	 * @param handArray
	 * @return
	 */
	private static Map<String,Integer> howManyTimesAppears(String[] handArray) {
		Map<String,Integer> appearancesMap = new HashMap<>();
		String cardValue;

		for(String card: handArray) {
			cardValue = getValueFromCard(card);

			if(appearancesMap.containsKey(cardValue)) {
				appearancesMap.put(cardValue, appearancesMap.get(cardValue) + 1);
			}else {
				appearancesMap.put(cardValue, 1);
			} 
		}

		return appearancesMap;
	}

	/**
	 * This method return the first suit from a hand
	 * 
	 * @param handArray
	 * @return
	 */
	private static char getTheSuit(String[] handArray) {
		return handArray[0].charAt(handArray[0].length() - 1);
	}

	/**
	 * This method return the value of a card without the suit.
	 * 
	 * @param card
	 * @return
	 */
	private static String getValueFromCard(String card) {
		return card.length() > 2 ? card.substring(0, 2) : card.substring(0, 1);
	}


}
