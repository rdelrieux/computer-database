package dao.implement;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DAO;
import model.Company;

public class CompanyDAO extends DAO{

	public CompanyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Company find(int id) {
		Company company = new Company();      
/**
		    try {
		      ResultSet result = this.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE,
		        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM eleve WHERE elv_id = " + id);
		      if(result.first())
		    	  company = new Eleve(
		          id,
		          result.getString("elv_nom"),
		          result.getString("elv_prenom"
		        ));         
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    */
		    return company;
	}
	
	
	
	
	

}
