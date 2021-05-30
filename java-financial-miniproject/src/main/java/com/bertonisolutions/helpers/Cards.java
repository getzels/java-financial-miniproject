package com.bertonisolutions.helpers;

import java.util.HashMap;
import java.util.Map;

public enum Cards {

	ACE("A",1),
	TWO("2", 2),
	THREE("3",3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("T", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K",13);

	private Integer rank;
	private String name;
	private Map<String, Integer> value = new HashMap<>();

	private Cards(String name, Integer rank) {
		this.rank = rank;
		this.name = name;		
		this.value.put(name, rank);
	}


	public Map<String, Integer> getValue() {
		return this.value;
	}

	public Integer getRank() {
		return rank;
	}

	public String getName() {
		return name;
	}
	
	public static Cards getCardByKey(String name) {
		for(Cards card: values()) {
			if(card.name.equals(name)) return card;
		}
		throw new IllegalArgumentException();
	}
	
	public static Cards getCardByValue(Integer rank) {
		for(Cards card: values()) {
			if(card.rank.equals(rank)) return card;
		}
		throw new IllegalArgumentException();
	}


}
