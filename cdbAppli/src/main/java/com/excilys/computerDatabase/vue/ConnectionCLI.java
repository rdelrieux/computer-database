package com.excilys.computerDatabase.vue;

import com.excilys.computerDatabase.controleur.ConnectionCtr;

public class ConnectionCLI extends CLI {

	private static ConnectionCLI instance;
	private ConnectionCtr connectionCtr;

	private ConnectionCLI() {
		this.connectionCtr = ConnectionCtr.getInstance();
	}

	public static ConnectionCLI getInstance() {
		if (instance == null) {
			instance = new ConnectionCLI();
		}
		return instance;

	}

	public boolean isConnected() {

		System.out.println(IDENTIFIANT);
		String user = this.sc.typeString();

		System.out.println(MOT_DE_PASSE);
		String password = this.sc.typeString();

		System.out.println(ATTENTE_CONNECTION_MESSAGE);

		return this.connectionCtr.connect(user, password);

	}

	public void messageConnection() {
		System.out.println(CONNECTION_ECHOUE_MESSAGE);

	}

	public void messageConnectionReussi() {
		System.out.println(CONNECTION_REUSSI_MESSAGE);
		
	}

}
