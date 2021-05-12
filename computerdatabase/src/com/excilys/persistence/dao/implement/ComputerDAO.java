package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapping.ComputerMapping;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;

public class ComputerDAO extends DAO<Computer> {

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM computer";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.id = ? ";

	private static final String REQUET_ADD_COMPANIES = "INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (?,?,?,?);";

	private static final String REQUET_UPDATE_COMPANIES =" UPDATE computer \n"
			+ "SET name = ? , \n"
			+ "introduced = ? , \n"
			+ "discontinued = ?, \n"
			+ "company_id = ?  \n"
			+ "WHERE computer.id = ? ;";
	
	private static ComputerDAO instance ;	
	private ComputerMapping computerMapping;
	
	private  ComputerDAO(Connection conn) {
		super(conn);
		computerMapping = ComputerMapping.getInstance();
		}

	
	public static ComputerDAO getInstance(Connection conn)  {
		if (instance == null) {
			instance = new ComputerDAO(conn);
		}
		return instance;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();

		try {
			// this.connection.setAutoCommit(false);

			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			
			computer = this.computerMapping.toComputerFull(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> res = new ArrayList<Computer>();

		try {
			// this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
			ResultSet result = statementFind.executeQuery();
			res = this.computerMapping.toListComputer(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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
			
			
			if ( computer.getCompany().getId() == 0){
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
			
			if (computer.getIntroduced() == null){
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
			
			System.out.println(statementFind);
			//statementFind.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	
	
	
}
