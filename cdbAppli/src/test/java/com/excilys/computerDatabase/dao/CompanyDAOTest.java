package com.excilys.computerDatabase.dao;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;




@ExtendWith({BeforTest.class})
public class CompanyDAOTest {
	
	

	
	@Test
	public void testGetAllCompaniesShouldReturnListOfCompanies() {
		System.out.println("test");
		try {
			List<Company> listCompany = CompanyDAO.getInstance().findAll();
			assertEquals(42, listCompany.size());
		} catch ( RuntimeException e) {
			fail("Should not return an exception");
		}
	}
	
	@Test
	public void testGetAllComputerShouldReturnListOfComputers() {
		System.out.println("test2");
		try {
			List<Computer> listCompany = ComputerDAO.getInstance().findAll(new Page(), null);
			assertEquals(42, listCompany.size());
		} catch ( RuntimeException e) {
			fail("Should not return an exception");
		}
	}

}
