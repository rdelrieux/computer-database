package com.excilys.computerDatabase;

import com.excilys.computerDatabase.vue.StartApplication;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	//System.out.println("hello");
    	//  enum , optionnal , builder dans la class
    	StartApplication application = StartApplication.getInstance();
		application.start();
		application.playMenu();
		application.stop();
		
    }
}
