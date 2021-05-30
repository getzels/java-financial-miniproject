package com.bertonisolutions.types;

import com.bertonisolutions.helpers.Cards;
import com.bertonisolutions.helpers.HandRank;

public class Hand {
	
	private HandRank handRank;
	private Cards highCard;
	
	public Hand() {	}

	public Hand(HandRank handRank, Cards highCard) {
		this.handRank = handRank;
		this.setHighCard(highCard);
	}

	public HandRank getHandRank() {
		return handRank;
	}

	public void setHandRank(HandRank handRank) {
		this.handRank = handRank;
	}

	public Cards getHighCard() {
		return highCard;
	}

	public void setHighCard(Cards highCard) {
		this.highCard = highCard;
	}


	
	
}
