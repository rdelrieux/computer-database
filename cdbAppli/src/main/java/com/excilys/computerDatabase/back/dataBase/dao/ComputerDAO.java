package com.excilys.computerDatabase.back.dataBase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.back.dataBase.connection.CdbConnection;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.logger.LoggerCdb;



public class ComputerDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPUTERS = "SELECT *\n" + "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" + "LIMIT ? , ?";
	private static final String REQUET_NOMBRE_ELEMENT = "SELECT COUNT(computer.id) AS count\n" + "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n";

	private static final String REQUET_NOMBRE_ELEMENT_SEARCH = "SELECT COUNT(computer.id) AS count\n"
			+ "FROM computer \n" + "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name LIKE ? "
			+ "OR company.name LIKE ? ";
	
	private static final String REQUET_AFFICHER_SOME_COMPUTERS_SEARCH = "SELECT *\n" + "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" 
			+ "WHERE computer.name LIKE ? \n"
			+ "OR company.name LIKE ? "
			+ "LIMIT ? , ?";

	private static final String REQUET_TROUVER_COMPUTER_FROM_ID = "SELECT *\n" + "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" + "WHERE computer.id = ? ";

	private static final String REQUET_TROUVER_COMPUTER_FROM_NAME = "SELECT *\n" + "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" + "WHERE computer.name = ? ";

	private static final String REQUET_ADD_COMPUTER = "INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (?,?,?,?);";

	private static final String REQUET_UPDATE_COMPUTER = " UPDATE computer \n" + "SET name = ? , \n"
			+ "introduced = ? , \n" + "discontinued = ?, \n" + "company_id = ?  \n" + "WHERE computer.id = ? ;";

	private static final String REQUET_DELET_COMPUTER = "DELETE FROM computer\n" + "WHERE computer.id = ? ";

	private static ComputerDAO instance;
	private ComputerMapper computerMapping;
	private CdbConnection cdbConnection;

	private ComputerDAO() {
		this.cdbConnection = CdbConnection.getInstance();
		this.computerMapping = ComputerMapper.getInstance();
	}

	public static ComputerDAO getInstance() {
		if (instance == null) {
			instance = new ComputerDAO();
		}
		return instance;
	}

	public PreparedStatement creatStatementFind(Connection connection, int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPUTER_FROM_ID);
			preparedStatement.setInt(1, id);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			return null;

		}

	}

	public Computer find(int id) {

		try (Connection connection = cdbConnection.getConnection(); ) {
			PreparedStatement preparedStatement = this.creatStatementFind(connection, id);
			ResultSet result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				result.next();
				Optional <ComputerDTOOutput> cout = this.computerMapping.mapToComputerDTOOutput(result);
				return this.computerMapping.mapToComputer(cout.orElseThrow());
			}
			
		}catch (NullPointerException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}	
		catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public PreparedStatement creatStatementFind(Connection connection, String name) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPUTER_FROM_NAME);
			preparedStatement.setString(1, name);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public Computer find(String name) {
		
		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFind(connection, name);
			ResultSet result = preparedStatement.executeQuery();
			if (result.isBeforeFirst()) {
				Optional <ComputerDTOOutput> cout = this.computerMapping.mapToComputerDTOOutput(result);
				return this.computerMapping.mapToComputer(cout.orElseThrow());
			}
			
		}catch (NullPointerException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public PreparedStatement creatStatementSearchNombreElement(Connection connection) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_NOMBRE_ELEMENT);

			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public int searchNombreElement() {
		int count = 0;
		try (Connection connection = cdbConnection.getConnection();
				ResultSet result = this.creatStatementSearchNombreElement(connection).executeQuery();) {

			result.next();
			count = result.getInt("count");

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return count;
	}

	public PreparedStatement creatStatementFindAll(Connection connection, Page page) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPUTERS);
			preparedStatement.setInt(1, (page.getNumPage() - 1) * page.getNombreElementPage());
			preparedStatement.setInt(2, page.getNombreElementPage());
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public List<Computer> findAll(Page page) {
		List<Computer> res = new ArrayList<>();
	
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementFindAll(connection, page);
				ResultSet result = preparedStatement.executeQuery();) {
			if (result.isBeforeFirst()) {
				res = this.computerMapping.maptoListComputer(this.computerMapping.mapToListComputerDTOOutput(result));
				
			}
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return res;
	}

	public PreparedStatement creatStatementSearch(Connection connection, String search, Page page) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_AFFICHER_SOME_COMPUTERS_SEARCH);
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			preparedStatement.setInt(3, (page.getNumPage() - 1) * page.getNombreElementPage());
			preparedStatement.setInt(4, page.getNombreElementPage());
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public List<Computer> search(String search, Page page) {
		List<Computer> res = new ArrayList<Computer>();
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementSearch(connection, search, page);
				ResultSet result = preparedStatement.executeQuery();) {
			if (result.isBeforeFirst()) {
				res = this.computerMapping.maptoListComputer(this.computerMapping.mapToListComputerDTOOutput(result));
			}
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return res;
	}

	public PreparedStatement creatStatementSearchNombreElement(Connection connection, String search) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_NOMBRE_ELEMENT_SEARCH);
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");

			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public int searchNombreElement(String search) {
		int count = 0;
		try (Connection connection = cdbConnection.getConnection();
				ResultSet result = this.creatStatementSearchNombreElement(connection, search).executeQuery();) {

			result.next();
			count = result.getInt("count");

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return count;
	}

	public PreparedStatement creatStatementAddComputer(Connection connection, Computer computer) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_ADD_COMPUTER);

			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroduced() == null) {
				preparedStatement.setNull(2, Types.NULL);

			} else {
				preparedStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}

			if (computer.getDiscontinued() == null) {
				preparedStatement.setNull(3, Types.NULL);
			} else {
				preparedStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}

			if (computer.getCompany() == null) {
				preparedStatement.setNull(4, Types.NULL);

			} else {
				preparedStatement.setInt(4, computer.getCompany().getId());
			}

			return preparedStatement;
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public void addComputer(Computer computer) {
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementAddComputer(connection, computer)) {

			preparedStatement.execute();

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}

	}

	public PreparedStatement creatStatementUpdateComputer(Connection connection, Computer computer) {
		
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_UPDATE_COMPUTER);

			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroduced() == null) {
				preparedStatement.setNull(2, Types.NULL);

			} else {
				preparedStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}

			if (computer.getDiscontinued() == null) {
				preparedStatement.setNull(3, Types.NULL);
			} else {
				preparedStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}

			if (computer.getCompany() == null) {
				preparedStatement.setNull(4, Types.NULL);

			} else if (computer.getCompany().getId() != 0) {
				preparedStatement.setInt(4, computer.getCompany().getId());
			}
			preparedStatement.setInt(5, computer.getId());
			
			return preparedStatement;

		} catch (SQLException e) {
			
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public void updateComputer(Computer computer) {
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementUpdateComputer(connection, computer)) {
			preparedStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PreparedStatement creatStatementDeletComputer(Connection connection, int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_DELET_COMPUTER);
			preparedStatement.setInt(1, id);

			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return null;
	}

	public void deletComputer(int id) {

		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementDeletComputer(connection, id)

		) {

			preparedStatement.execute();

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}

	}

}
