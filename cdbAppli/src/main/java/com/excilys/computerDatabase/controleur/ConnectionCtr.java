package com.excilys.computerDatabase.controleur;

import java.sql.SQLException;

import com.excilys.computerDatabase.connection.CdbConnection;


public class ConnectionCtr {
	
	private static ConnectionCtr instance;
	private CdbConnection cdbConnection;
	
	private ConnectionCtr () {
		cdbConnection = CdbConnection.getInstance();
	}
	
	public static ConnectionCtr getInstance() {
		if (instance == null) {
			instance = new ConnectionCtr();
		}
		return instance;
		
	}

	
	public boolean connect(String identifaint, String password)  {

			try {
				cdbConnection.connection(identifaint , password);
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
			return true;
		
	}

	
}
