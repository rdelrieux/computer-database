package com.excilys.computerDatabase.model;

public class Page {
	
	private int nombreElementRequet = 0;
	private int nombreElementPage = 0;
	private int numPage = 0;
	
	public Page(int nombreElementRequet, int nombreElementPage, int numPage) {
		this.nombreElementRequet = nombreElementRequet;
		this.nombreElementPage = nombreElementPage;
		this.numPage = numPage;
	}

	public Page() {
	}

	
	
	
	public void setNombreElementRequet(int nombreElementRequet) {
		this.nombreElementRequet = nombreElementRequet;
	}

	public void setNombreElementPage(int nombreElementPage) {
		this.nombreElementPage = nombreElementPage;
	}

	public void setNumPage(int numPage) {
			this.numPage = numPage;
	}

	public int getNombreElementRequet() {
		return nombreElementRequet;
	}

	public int getNombreElementPage() {
		return nombreElementPage;
	}

	public int getNumPage() {
		return numPage;
	}
	
	public void setPageAfter() {
		int nombrePageTotal =nombreElementRequet /nombreElementPage+1;
		if (numPage != nombrePageTotal) {
			this.numPage ++;
		}
	}
	
	public void setPageBefore() {
		if (numPage != 1) {
			this.numPage --;
		}
	}

	@Override
	public String toString() {
		return "Page : "+numPage+ "/"+(nombreElementRequet/nombreElementPage+1) ;
	}
	

	
	
	
}
