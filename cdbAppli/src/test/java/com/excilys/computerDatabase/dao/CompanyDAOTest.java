package com.excilys.computerDatabase.dao;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.persistence.dao.implement.CompanyDAO;

@ExtendWith({BeforTest.class})
public class CompanyDAOTest {
	
	

	
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
