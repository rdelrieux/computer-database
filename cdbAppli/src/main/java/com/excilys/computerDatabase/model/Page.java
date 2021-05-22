package com.excilys.computerDatabase.model;

public class Page {
	
	private int nombreElementRequet = 0;
	private int nombreElementPage = 10;
	private int numPage = 1;
	
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
	public int getNombrePageMax() {
		return nombreElementRequet /nombreElementPage + 
				(nombreElementRequet%nombreElementPage == 0? 0 : 1);
		
	}
	
	public void setPageAfter() {
		if (numPage != this.getNombrePageMax()) {
			this.numPage ++;
		}
	}
	
	
	
	public void setPageBefore() {
		if (numPage != 1) {
			this.numPage --;
		}
	}


	
	
	public void addNumPage(Integer numero) {
		if (numero < 0) {
			numero = -numero;
			for (int i =0 ; i < numero; i++ ) {
				this.setPageBefore();
			}
		}else {
			for (int i =0 ; i < numero; i++ ) {
				this.setPageAfter();
			}
		}
		
		
	}
	
	@Override
	public String toString() {
		return "Page : "+numPage+ "/"+(nombreElementRequet/nombreElementPage+1) ;
	}

	
	

	
	
	
}
