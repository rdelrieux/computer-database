package com.excilys.computerDatabase.vue;


public class StartApplication extends CLI {
	
	private static StartApplication instance ;
	private MenuCLI menuCli;

	private StartApplication() {
		this.menuCli = MenuCLI.getInstance();
	}
	
	public static StartApplication getInstance()  {
		if (instance == null) {
			instance = new StartApplication();
		}
		return instance;
	}

	public void start() {
		System.out.println(CLI.BIENVENUE_MESSAGE);
	}
	
	public void playMenu() {
		while (this.menuCli.isUsed()) {	
		}
	}
	
	public void stop() {
		System.out.println(CLI.BYE_MESSAGE);
		this.sc.close();
	}

	

}
