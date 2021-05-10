package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;

public class ComputerDAO extends DAO {

	public ComputerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) {
		Computer computer = new Computer();

		try {
			// this.connection.setAutoCommit(false);

			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM computer WHERE id = ?");
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();

			if (result.first()) {

				int idRes = result.getInt("id");
				String name = result.getString("name");
				LocalDate introduced = null;
				LocalDate discontinued = null;
				int companyId = 0 ;
				
				if (result.getDate("introduced") != null) {
					introduced = result.getDate("introduced").toLocalDate();
				}
				if (result.getDate("discontinued") != null) {
					discontinued = result.getDate("discontinued").toLocalDate();
				}
				if (result.getInt("company_id") != 0) {
					companyId = result.getInt("company_id");
				} 
				
				computer = new Computer(idRes , name, introduced, discontinued , companyId );
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
			// this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM computer");
			ResultSet result = statementFind.executeQuery();

			while (result.next()) {
				Computer computer = new Computer(result.getInt("id"), result.getString("name"));
				res.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
