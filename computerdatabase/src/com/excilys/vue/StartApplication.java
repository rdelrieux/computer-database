package com.excilys.vue;



public class StartApplication extends CLI {
	
	private static StartApplication instance ;
	private ConnectionCLI connectionCli  ;
	private MenuCLI menuCli;

	private StartApplication() {
		connectionCli = ConnectionCLI.getInstance();
		menuCli = MenuCLI.getInstance();
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

	public void connect() {
		while ( !connectionCli.isConnected()) {
			connectionCli.messageConnection();
		}
		connectionCli.messageConnectionReussi();
		
	}
	
	public void playMenu() {
		while (menuCli.isUsed()) {	
		}
	}
	
	
	
	public void stop() {
		System.out.println(CLI.BYE_MESSAGE);
		this.sc.close();
	}

}
