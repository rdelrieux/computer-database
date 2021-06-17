package com.excilys.computerDatabase.front.session;

public enum OrderBy {

	COMPUTER_NAME("computer.name" , "name"),
	
	INTRODUCED_DATE("introduced" , "introduced"),
	
	DISCONTINUED_DATE("discontinued" ,"discontinued"),
	
	COMPANY("company.name" , "name");

	private String column;
	private String columnDataBase;
	
	OrderBy(String column,String columnDataBase ) {
		this.column = column;
		this.columnDataBase = columnDataBase;
	}
	
	public String getColumnDataBase() {

		return this.columnDataBase;
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
