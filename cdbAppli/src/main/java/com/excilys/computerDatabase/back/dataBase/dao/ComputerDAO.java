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
import com.excilys.computerDatabase.back.dataBase.exception.UnableCreatePreparedStatmentException;
import com.excilys.computerDatabase.back.dataBase.exception.UnableExecutQueryException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.logger.LoggerCdb;

public class ComputerDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPUTERS = 
			"SELECT *\n" 
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" 
			+ "ORDER BY columnname \n" + "LIMIT ? , ?\n";
	private static final String REQUET_NOMBRE_ELEMENT = 
			"SELECT COUNT(computer.id) AS count\n" 
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n";

	private static final String REQUET_NOMBRE_ELEMENT_SEARCH = 
			"SELECT COUNT(computer.id) AS count\n"
			+ "FROM computer \n" + "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name LIKE ? " 
			+ "OR company.name LIKE ? " 
			+ "OR introduced LIKE ? "
			+ "OR discontinued LIKE ? ";

	private static final String REQUET_AFFICHER_COMPUTERS_SEARCH = 
			"SELECT *\n" 
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" 
			+ "WHERE computer.name LIKE ? \n"
			+ "OR company.name LIKE ? " 
			+ "OR introduced LIKE ? " 
			+ "OR discontinued LIKE ? " 
			+ "ORDER BY columnname \n"
			+ "LIMIT ? , ?";

	private static final String REQUET_TROUVER_COMPUTER_FROM_ID = 
			"SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.id = ? ";

	private static final String REQUET_TROUVER_COMPUTER_FROM_NAME = 
			"SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name = ? ";

	private static final String REQUET_ADD_COMPUTER = 
			"INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (?,?,?,?);";

	private static final String REQUET_UPDATE_COMPUTER = 
			" UPDATE computer \n"
			+ "SET name = ? , \n"
			+ "introduced = ? , \n"
			+ "discontinued = ?, \n" 
			+ "company_id = ?  \n" 
			+ "WHERE computer.id = ? ";

	private static final String REQUET_DELET_COMPUTER = 
			"DELETE FROM computer\n"
			+ "WHERE computer.id = ? ";

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

	private PreparedStatement creatStatementFind(Connection connection, int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPUTER_FROM_ID);
			preparedStatement.setInt(1, id);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();

		}

	}

	public Computer find(int id) {

		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFind(connection, id);
			ResultSet result = preparedStatement.executeQuery();

			result.next();
			ComputerDTOOutput cout = this.computerMapping.mapToComputerDTOOutput(result);
			return this.computerMapping.mapToComputer(cout);

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}
	}

	private PreparedStatement creatStatementFind(Connection connection, String name) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPUTER_FROM_NAME);
			preparedStatement.setString(1, name);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public Computer find(String name) {

		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFind(connection, name);
			ResultSet result = preparedStatement.executeQuery();
			ComputerDTOOutput cout = this.computerMapping.mapToComputerDTOOutput(result);
			return this.computerMapping.mapToComputer(cout);

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}
	}

	private PreparedStatement creatStatementSearchNombreElement(Connection connection) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_NOMBRE_ELEMENT);
			return preparedStatement;
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public int searchNombreElement() {
		int count = 0;
		try (Connection connection = cdbConnection.getConnection();) {
			ResultSet result = this.creatStatementSearchNombreElement(connection).executeQuery();
			result.next();
			count = result.getInt("count");

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return count;
	}

	private PreparedStatement creatStatementFindAll(Connection connection, Page page, OrderBy orderBy) {
		try {
			String statement = REQUET_AFFICHER_TOUTE_COMPUTERS;
			if (orderBy.isUp()) {
				statement = statement.replace("columnname", orderBy.getValeur());
			} else {
				statement = statement.replace("columnname", orderBy.getValeur() + " DESC");
			}

			PreparedStatement preparedStatement = connection.prepareStatement(statement);

			preparedStatement.setInt(1, (page.getNumPage() - 1) * page.getNombreElementPage());
			preparedStatement.setInt(2, page.getNombreElementPage());

			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public List<Computer> findAll(Page page, OrderBy orderBy) {
		List<Computer> res = new ArrayList<>();

		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFindAll(connection, page, orderBy);
			ResultSet result = preparedStatement.executeQuery();
			if (result.isBeforeFirst()) {
				res = this.computerMapping.maptoListComputer(this.computerMapping.mapToListComputerDTOOutput(result));

			}
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
		}
		return res;
	}

	private PreparedStatement creatStatementSearch(Connection connection, String search, Page page, OrderBy orderBy) {
		try {

			String statement = REQUET_AFFICHER_COMPUTERS_SEARCH;
			if (orderBy.isUp()) {
				statement = statement.replace("columnname", orderBy.getValeur());
			} else {
				statement = statement.replace("columnname", orderBy.getValeur() + " DESC");
			}

			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			preparedStatement.setString(3, "%" + search + "%");
			preparedStatement.setString(4, "%" + search + "%");
			preparedStatement.setInt(5, (page.getNumPage() - 1) * page.getNombreElementPage());
			preparedStatement.setInt(6, page.getNombreElementPage());
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public List<Computer> search(String search, Page page, OrderBy orderBy) {
		List<Computer> res = new ArrayList<Computer>();
		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementSearch(connection, search, page, orderBy);
			ResultSet result = preparedStatement.executeQuery();
			res = this.computerMapping.maptoListComputer(this.computerMapping.mapToListComputerDTOOutput(result));
		} catch (SQLException e) {
			LoggerCdb.logInfo(ComputerDAO.class.getName(), e);
		}
		return res;
	}

	private PreparedStatement creatStatementSearchNombreElement(Connection connection, String search) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_NOMBRE_ELEMENT_SEARCH);
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			preparedStatement.setString(3, "%" + search + "%");
			preparedStatement.setString(4, "%" + search + "%");
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public int searchNombreElement(String search) {
		int count = 0;
		try (Connection connection = cdbConnection.getConnection();) {
			ResultSet result = this.creatStatementSearchNombreElement(connection, search).executeQuery();
			result.next();
			count = result.getInt("count");

		} catch (SQLException e) {
			LoggerCdb.logInfo(ComputerDAO.class.getName(), e);
		}
		return count;
	}

	private PreparedStatement creatStatementAddComputer(Connection connection, Computer computer) {
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
			throw new UnableCreatePreparedStatmentException();
		}
	}

	public void addComputer(Computer computer) {
		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementAddComputer(connection, computer);
			preparedStatement.execute();

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}

	}

	private PreparedStatement creatStatementUpdateComputer(Connection connection, Computer computer) {

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
			throw new UnableCreatePreparedStatmentException();
		}

	}

	public void updateComputer(Computer computer) {
		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementUpdateComputer(connection, computer);
			preparedStatement.execute();

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}

	}

	private PreparedStatement creatStatementDeletComputer(Connection connection, int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_DELET_COMPUTER);
			preparedStatement.setInt(1, id);

			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}

	}

	public void deletComputer(int id) {
		try (Connection connection = cdbConnection.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementDeletComputer(connection, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}
	}

}
