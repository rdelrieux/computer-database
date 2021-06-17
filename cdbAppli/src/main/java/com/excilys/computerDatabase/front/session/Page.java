package com.excilys.computerDatabase.front.session;

public class Page {

	private long nombreElementRequet;
	private int nombreElementPage = 10;
	private int numPage = 1;

	public Page(long nombreElementRequet, int nombreElementPage, int numPage) {
		this.nombreElementRequet = nombreElementRequet;
		this.nombreElementPage = nombreElementPage;
		this.numPage = numPage;
	}

	public Page() {
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

	public long getNombrePageMax() {
		return nombreElementRequet / nombreElementPage + (nombreElementRequet % nombreElementPage == 0 ? 0 : 1);

	}

	public void goToFirstPage() {
		this.numPage = 1;
	}

	public void setPageAfter() {
		if (numPage != this.getNombrePageMax()) {
			this.numPage++;
		}
	}

	public void setPageBefore() {
		if (numPage != 1) {
			this.numPage--;
		}
	}

	public int getOffset() {

		return (numPage - 1) * nombreElementPage;
	}

	@Override
	public String toString() {
		return "Page : " + numPage + "/" + (nombreElementRequet / nombreElementPage + 1);
	}

}
