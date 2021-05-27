package com.excilys.computerDatabase.mapper;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.binding.dto.ComputerDTOSQL;
import com.excilys.computerDatabase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.persistence.dao.implement.CompanyDAO;
import com.excilys.computerDatabase.persistence.dao.implement.ComputerDAO;

public class ComputerMapperTest {
	
	@Test
	void testMapToCompanyShouldReturnListOfCompanies() {
		ComputerDAO mockComputerDAO = mock(ComputerDAO.class);
		
		List<ComputerDTOSQL> listComputerDTO = new ArrayList<>();
		listComputerDTO.add( new ComputerDTOSQL.ComputerDTOSQLBuilder("1","Computer1")
				.withIntroduced(""+LocalDate.of(2000, 05, 01))
				.withDiscontinued(""+LocalDate.of(2010, 06, 02))
				.withCompanyId("4")
				.build()
				);
		listComputerDTO.add( new ComputerDTOSQL.ComputerDTOSQLBuilder("2","Computer2")
				.build()
				);
		
		when(mockComputerDAO.findAll(new Page())).thenReturn(listComputerDTO);
		
		try {
			
			
			List<Computer> listComputer = ComputerMapper.getInstance().toListComputer(mockComputerDAO.findAll(new Page()));
			assertEquals(2, listComputer.size());
			
			assertEquals(1, listComputer.get(0).getId());
			assertEquals("Computer1", listComputer.get(0).getName());
			assertEquals(LocalDate.of(2000, 05, 01), listComputer.get(0).getIntroduced());
			assertEquals(LocalDate.of(2010, 06, 02), listComputer.get(0).getDiscontinued());
			assertEquals("4", listComputer.get(0).getCompany().getId());
			
			assertEquals(2, listComputer.get(1).getId());
			assertEquals("Computer2", listComputer.get(0).getName());
			assertEquals(null, listComputer.get(0).getIntroduced());
			assertEquals(null, listComputer.get(0).getDiscontinued());
			assertEquals(null, listComputer.get(0).getCompany());
			
			
		} catch ( RuntimeException e) {
			fail("Should not throw an exception");
		}
	}
	

}
