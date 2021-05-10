package dao;

import java.sql.Connection;

import connection.CdbConnection;
import dao.implement.CompanyDAO;

public class DAOFactory {
	
	protected static final Connection conn = CdbConnection.getInstance();   

	  /**
	  * Retourne un objet Company interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO getCompanyDAO(){
	    return new CompanyDAO(conn);
	  }

	  
}
