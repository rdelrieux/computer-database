package com.excilys.vue;

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
			res = sc.nextLine();
		}
		return Integer.parseInt(res);
	}

	public void close() {
		
		sc.close();
	}

	public boolean isIntegerPositif(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    if (Integer.parseInt(str)<1) {
	    	return false;
	    }
	    
	    return true;
	}

	public boolean isDate(String date) {
		if (date.length() != 10) {
			return true ;
		}
		return true;
	}

	




}
