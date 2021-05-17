package com.excilys.vue;

import java.time.LocalDate;
import java.util.Scanner;

public class ScannerVerification {

	private static ScannerVerification instance ;
	private Scanner sc = new Scanner(System.in);


	private ScannerVerification() {
		
	}
	
	public static ScannerVerification getInstance()  {
		if (instance == null) {
			instance = new ScannerVerification();
		}
		return instance;
	}

	
	public String typeString() {
		String res = sc.nextLine();
		return res;
	}

	
	public int typeIdValid() {
		String res = sc.nextLine();
		while ( ! this.isIntegerPositif(res)  ) {
			System.out.println(CLI.INPUT_ERREUR);
			res = sc.nextLine();
		}
		return Integer.parseInt(res);
	}

	public void close() {	
		sc.close();
	}

	public boolean isIntegerPositif(String str) {
		try {
			if (Integer.parseInt(str)<1) {
		    	return false;
		    }
		}catch (Exception e) {
			System.out.println("Logg : Not an Interger");
			return false;
		}
	    
	    
	    return true;
	}

	public boolean isDate(String date) {
		if (date.equals("")) {
			return true;
		}
		try {
			LocalDate.parse(date);
		}catch (Exception e){
			return false;
		}
		
		return true;
	}

	




}
