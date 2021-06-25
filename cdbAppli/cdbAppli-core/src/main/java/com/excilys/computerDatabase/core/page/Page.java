package com.excilys.computerDatabase.core.page;

public class Page {

	private long nombreElementRequet;
	private int nombreElementPage = 10;
	private int numPage = 1;
	private String search = "";
	private OrderBy orderBy = OrderBy.COMPUTER_NAME;
	private Order order = Order.ASCENDANT;



	public Page(long nombreElementRequet, int nombreElementPage, int numPage, String search, OrderBy orderBy,
			Order order) {
		super();
		this.nombreElementRequet = nombreElementRequet;
		this.nombreElementPage = nombreElementPage;
		this.numPage = numPage;
		this.search = search;
		this.orderBy = orderBy;
		this.order = order;
	}

	public Page() {
	}

	

	public long getNombrePageMax() {
		return nombreElementRequet / nombreElementPage + (nombreElementRequet % nombreElementPage == 0 ? 0 : 1);

	}

	public void goToFirstPage() {
		this.numPage = 1;
	}

	public int getOffset() {

		return (numPage - 1) * nombreElementPage;
	}

	public void setNombreElementRequet(long nombreElementRequet) {
		this.nombreElementRequet = nombreElementRequet;
	}

	public void setNombreElementPage(int nombreElementPage) {
		this.nombreElementPage = nombreElementPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	public long getNombreElementRequet() {
		return nombreElementRequet;
	}

	public int getNombreElementPage() {
		return nombreElementPage;
	}

	public int getNumPage() {
		return numPage;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

	
	
	
}
