package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.excilys.binding.dto.ComputerDTOSQL;
import com.excilys.binding.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.model.Page;



public class ComputerDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT *\n"
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n";
	
	private static final String REQUET_NOMBRE_ELEMENT_SEARCH  = "SELECT COUNT(computer.id) AS count\n"
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+"WHERE computer.name LIKE ? "
			;
	
	private static final String REQUET_AFFICHER_SOME_COMPANIES_SEARCH = "SELECT *\n"
			+"FROM computer \n"
			+"LEFT JOIN company ON company.id = computer.company_id\n"
			+"WHERE computer.name LIKE ? \n"
			+"LIMIT ? , ?"
			;
	
	
	
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.id = ? ";
	
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name = ? ";

	private static final String REQUET_ADD_COMPANIES = "INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (?,?,?,?);";

	private static final String REQUET_UPDATE_COMPANIES =" UPDATE computer \n"
			+ "SET name = ? , \n"
			+ "introduced = ? , \n"
			+ "discontinued = ?, \n"
			+ "company_id = ?  \n"
			+ "WHERE computer.id = ? ;";
	
	
	private static final String REQUET_DELET_COMPANIES = "DELETE FROM computer\n"
			+ "WHERE computer.id = ? ";

	
	private static ComputerDAO instance ;	
	private ComputerMapper computerMapping;
	private Connection connection;
	
	private  ComputerDAO(Connection conn) {
		this.connection = conn;
		computerMapping = ComputerMapper.getInstance();
		}

	
	public static ComputerDAO getInstance(Connection conn)  {
		if (instance == null) {
			instance = new ComputerDAO(conn);
		}
		return instance;
	}
	
	
	public ComputerDTOSQL find(int id) {
		
		 try {
			//this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			return this.computerMapping.toComputerDTOSQL(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return null;
	}
	
	public ComputerDTOSQL find(String name) {
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			statementFind.setString(1, name);
			ResultSet result = statementFind.executeQuery();
			return this.computerMapping.toComputerDTOSQL(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    return null;
	}
	
	public  List<ComputerDTOSQL> findAll() {
		try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
				ResultSet result = statementFind.executeQuery();
				return  this.computerMapping.toListComputerDTOSQL(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	public  List<ComputerDTOSQL> search(String search, Page page) {
		try {
				PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_SOME_COMPANIES_SEARCH);
				
				
				statementFind.setString(1, "%"+search+"%");
				statementFind.setInt(2,(page.getNumPage()-1)*page.getNombreElementPage());
				statementFind.setInt(3,page.getNombreElementPage());

				//System.out.println(statementFind);

				ResultSet result = statementFind.executeQuery();
				return  this.computerMapping.toListComputerDTOSQL(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	public int searchNombreElement(String search) {
		try {
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_NOMBRE_ELEMENT_SEARCH);
			statementFind.setString(1, "%"+search+"%");
			ResultSet result = statementFind.executeQuery();
			result.next();

			return  result.getInt("count");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return 0;
	}
	
	
	
	public void addComputer(Computer computer) {
		try {
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_ADD_COMPANIES);
			
			statementFind.setString(1, computer.getName());
			
			if (computer.getIntroduced() == null){
				statementFind.setNull(2, Types.NULL);
				
			}else {
				statementFind.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getIntroduced() == null){
				statementFind.setNull(3, Types.NULL);
			}else {
				statementFind.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}
			
			if ( computer.getCompany() == null){
				statementFind.setNull(4, Types.NULL);
				
			}else {
				statementFind.setInt(4, computer.getCompany().getId());
			}
				
			statementFind.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


	public void updateComputer(Computer computer) {
		try {
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_UPDATE_COMPANIES);
			
			statementFind.setString(1, computer.getName());
			
			if (computer.getIntroduced() == null){
				statementFind.setNull(2, Types.NULL);
				
			}else {
				statementFind.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getDiscontinued() == null){
				statementFind.setNull(3, Types.NULL);
			}else {
				statementFind.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}
			
			if (computer.getCompany() == null ){
				statementFind.setNull(4, Types.NULL);
				
			}else if(computer.getCompany().getId() != 0) {
				statementFind.setInt(4, computer.getCompany().getId());
			}
			statementFind.setInt(5, computer.getId());
			
			//System.out.println(statementFind);
			statementFind.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void deletComputer(int id) {
		PreparedStatement statementFind;
		try {
			statementFind = this.connection.prepareStatement(REQUET_DELET_COMPANIES);
			statementFind.setInt(1, id);
			statementFind.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}


	



	
	
	
}
