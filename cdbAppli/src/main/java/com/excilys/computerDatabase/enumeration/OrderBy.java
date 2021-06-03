package com.excilys.computerDatabase.enumeration;

public enum OrderBy {

	COMPUTER_NAME("computer.name"),
	IntraoducedDate("introduced"),
	DiscontinuedDate("discontinued"),
	Comany("company.name");

	private String valeur;
	private boolean ascending ; 
	
	OrderBy(String valeur) {
		this.valeur = valeur;
		this.ascending = true;
	}

	public boolean isAscending() {
		return ascending;
	}
	
	public String getValeur() {

		return this.valeur;
	}

	public static OrderBy getOrderBy(String valeur) {
		for (OrderBy order : OrderBy.values()) {
			if (valeur.equals(order.valeur)) {
				return order;
			}
		}
		return COMPUTER_NAME;
	}
	
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

}
