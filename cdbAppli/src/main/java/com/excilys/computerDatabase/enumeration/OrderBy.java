package com.excilys.computerDatabase.enumeration;

public enum OrderBy {

	COMPUTER_NAME("computer.name"),
	
	INTRODUCED_DATE("introduced"),
	
	DISCONTINUED_DATE("discontinued"),
	
	COMPANY("company.name");

	private String column;
	
	OrderBy(String column ) {
		this.column = column;
	}
	
	public String getColumn() {

		return this.column;
	}

	public static OrderBy getOrderBy(String column) {
		for (OrderBy order : OrderBy.values()) {
			if (column.equals(order.column) ) {
				return order;
			}
		}
		return COMPUTER_NAME;
	}
	
	

}
