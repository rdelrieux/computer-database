package com.excilys.vue;

import com.excilys.controleur.ConnectionCtr;

public class ConnectionCLI extends CLI {

	private static final String ATTENTE_CONNECTION_MESSAGE = "Connection ...\n";
	private static final String CONNECTION_REUSSI_MESSAGE = "Vous etes connecte\n";
	private static final String CONNECTION_ECHOUE_MESSAGE = "Erreur de connection\n";
	private static final String IDENTIFIANT = "Entez votre identifant";
	private static final String MOT_DE_PASSE = "Entez votre password";

	private static ConnectionCLI instance;
	private ConnectionCtr connectionCtr;

	private ConnectionCLI() {
		connectionCtr = ConnectionCtr.getInstance();
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

		 user = "admincdb";
		 password = "qwerty1234";

		System.out.println(ATTENTE_CONNECTION_MESSAGE);

		return connectionCtr.connect(user, password);

	}

	public void messageConnection() {
		System.out.println(CONNECTION_ECHOUE_MESSAGE);

	}

	public void messageConnectionReussi() {
		System.out.println(CONNECTION_REUSSI_MESSAGE);
		
	}

}
