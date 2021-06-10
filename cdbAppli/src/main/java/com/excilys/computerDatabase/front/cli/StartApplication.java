package com.excilys.computerDatabase.front.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartApplication extends CLI {
	
	@Autowired
	private MenuCLI menuCli;


	public void start() {
		System.out.println(CLI.BIENVENUE_MESSAGE);
	}
	
	public void playMenu() {
		while (this.menuCli.isUsed()) {	
		}
	}
	
	public void stop() {
		System.out.println(CLI.BYE_MESSAGE);
	}

	

}
