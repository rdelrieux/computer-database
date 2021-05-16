package com.excilys.vue;

import com.excilys.controleur.ComputerCtr;
import com.excilys.controleur.ConnectionCtr;

public class StartApplication extends CLI {
	
	private static StartApplication instance ;
	private ConnectionCLI connectionCli  ;
	private MenuCLI menuCli;

	private StartApplication() {
		this.connectionCli = ConnectionCLI.getInstance();
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

	public void connect() {
		while ( !this.connectionCli.isConnected()) {
			this.connectionCli.messageConnection();
		}
		this.connectionCli.messageConnectionReussi();
		
	}
	
	public void playMenu() {
		while (this.menuCli.isUsed()) {	
		}
	}
	
	public void stop() {
		System.out.println(CLI.BYE_MESSAGE);
		this.sc.close();
	}

	public void connectRapid() {
		ConnectionCtr.getInstance().connect("admincdb","qwerty1234");
		this.connectionCli.messageConnectionReussi();
	}

}
