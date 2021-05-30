package com.bertonisolutions.helpers;

/**
 * 
 * This emun contain that rank for the card hands.
 * 
 * @author gndelossantos
 *
 */
public enum HandRank {
	/*
	 *  High Card: Highest value card.
		One Pair: Two cards of the same value.
		Two Pairs: Two different pairs.
		Three of a Kind: Three cards of the same value.
		Straight: All cards are consecutive values.
		Flush: All cards of the same suit.
		Full House: Three of a kind and a pair.
		Four of a Kind: Four cards of the same value.
		Straight Flush: All cards are consecutive values of same suit.
		Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	 * */
	
	ROYAL_FLUSH(4),
	STRAIGHT_FLUSH(36),
	FOUR_OF_KIND(624),
	FULL_HOUSE(3744),
	FLUSH(5108),
	STRAIGHT(10200),
	THREE_OF_A_KIND(54912),
	TWO_PAIRS(123552),
	ONE_PAIR(1098240),
	HIGH_CARD(1302540);
	
	public final int value;
	
	HandRank(int value){
		this.value = value;
	}
	
	

}
