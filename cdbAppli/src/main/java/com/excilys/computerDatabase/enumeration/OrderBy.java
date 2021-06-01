package com.excilys.computerDatabase.enumeration;

public enum OrderBy {

	COMPUTER_NAME("computer.name"),
	IntraoducedDate("introduced"),
	DiscontinuedDate("discontinued"),
	Comany("company.name");

	private String valeur;
	private boolean up ; 
	
	OrderBy(String valeur) {
		this.valeur = valeur;
		this.up = false;
	}

	public boolean isUp() {
		return up;
	}
	
	public String getValeur() {

		return this.valeur;
	}

	public static OrderBy getOrderBy(String valeur) {
		for (OrderBy order : OrderBy.values()) {
			if (valeur.equals(order.valeur)) {
				order.up = !order.up;
				return order;
			}
		}
		return COMPUTER_NAME;
	}

}
