package com.excilys.computerDatabase.front.session;

public enum Order {
	
	ASCENDANT("ASC"),
	
	DESCENDANT("DESC");
	

	private String paramOrder;
	

	
	
	public String getParamOrder() {
		return paramOrder;
	}



	Order(String paramOrder) {
		this.paramOrder = paramOrder;
	}


	public static Order getOrder(String string) {
		for (Order order : Order.values()) {
			if (string.equals(order.paramOrder) ) {
				return order;
			}
		}
		return ASCENDANT;
	}
	
}
