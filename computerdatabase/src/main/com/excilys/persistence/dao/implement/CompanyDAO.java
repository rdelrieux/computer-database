package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.binding.dto.CompanyDTOSQL;
import com.excilys.binding.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;

public class CompanyDAO {

	
	
	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT * FROM company WHERE id = ?";
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = ?";

	private static CompanyDAO instance ;	
	private CompanyMapper companyMapper;
	private Connection connection;
	
	private  CompanyDAO(Connection conn) {
		this.connection = conn;
		this.companyMapper = CompanyMapper.getInstance();
		}

	
	public static CompanyDAO getInstance(Connection conn)  {
		if (instance == null) {
			instance = new CompanyDAO(conn);
		}
		return instance;
	}
	
	
	public CompanyDTOSQL find(int id) {
		
		 try {
			//this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			return this.companyMapper.toCompanyDTOSQL(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return null;
	}
	
	
	
	
	public CompanyDTOSQL find(String name) {
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			statementFind.setString(1, name);
			ResultSet result = statementFind.executeQuery();
			return this.companyMapper.toCompanyDTOSQL(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    return null;
	}
	
	public  List<CompanyDTOSQL> findAll() {
		try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
				ResultSet result = statementFind.executeQuery();
				return  this.companyMapper.toListCompanyDTOSQL(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	
	

}
