package com.excilys.computerDatabase.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.connection.CdbConnection;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.dao.implement.CompanyDAO;

public class CompanyDAOTest {
	
	private static final String PROP_FILE_NAME = "test-db.sql";
	
	@BeforeEach
	public void setUp() throws Exception {
		CdbConnection cdbConnection = CdbConnection.getInstance();
		try (Connection con = cdbConnection.getConnection()) {
			ScriptRunner sr = new ScriptRunner(con);
			Reader reader = new BufferedReader(
					new FileReader(PROP_FILE_NAME));
			sr.runScript(reader);
		}
	}
	
	
	@Test
	public void testGetAllCompaniesShouldReturnListOfCompanies() {
		System.out.println("test");
		try {
			List<CompanyDTOSQL> listCompany = CompanyDAO.getInstance().findAll();
			assertEquals(42, listCompany.size());
		} catch ( RuntimeException e) {
			fail("Should not return an exception");
		}
	}

}
