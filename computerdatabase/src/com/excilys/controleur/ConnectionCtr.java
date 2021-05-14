package com.excilys.controleur;

import java.sql.SQLException;

import com.excilys.connection.CdbConnection;
import com.excilys.vue.ConnectionCLI;
import com.excilys.vue.MenuCLI;
import com.excilys.vue.StartApplication;


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
