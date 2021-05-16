package com.excilys.test;

import com.excilys.vue.StartApplication;

public class Main {
	
	public static void main(String[] args) {
	
		StartApplication application = StartApplication.getInstance();
		application.start();
		//application.connect();
		application.connectRapid();
		application.playMenu();
		application.stop();
		
		
	}

}
