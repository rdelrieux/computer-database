package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton
public class CdbConnection {

	// URL de connexion
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	

	// Nom du user
	private String user = "admincdb";
	// Mot de passe de l'utilisateur
	private String password = "qwerty1234";

	// Objet Connection
	private static Connection connect;

	// Constructeur privé
	private CdbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance() {
		if (connect == null) {
			new CdbConnection();
		}
		return connect;
	}
	
	
	
	
	
	
}
