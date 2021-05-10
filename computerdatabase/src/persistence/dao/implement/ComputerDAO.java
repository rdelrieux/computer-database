package persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Computer;
import persistence.dao.DAO;

public class ComputerDAO extends DAO {

	public ComputerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) {
		Computer computer = new Computer(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM computer WHERE id = ?");
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			
			if(result.first() ) {
				computer = new Computer(result.getInt("id"), result.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return computer;
	}
	
	@Override
	public List<Computer> findAll() {
		ArrayList<Computer> res = new ArrayList<Computer>();
		
		 try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM computer");
				ResultSet result = statementFind.executeQuery();
				
				while(result.next()) {
					Computer computer = new Computer(result.getInt("id"), result.getString("name") );
					res.add(computer);		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return  res;
	}
	
	

}
