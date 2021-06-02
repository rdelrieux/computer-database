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


import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;


public class ComputerMapperTest {
	
	@Test
	void testMapToCompanyShouldReturnListOfCompanies() {
		ComputerDAO mockComputerDAO = mock(ComputerDAO.class);
		
		List<Computer> listComputer = new ArrayList<>();
		listComputer.add( new Computer.ComputerBuilder(1,"Computer1")
				.withIntroduced(LocalDate.of(2000, 05, 01))
				.withDiscontinued(LocalDate.of(2010, 06, 02))
				.withCompany(new Company(4,""))
				.build()
				);
		listComputer.add( new Computer.ComputerBuilder(2,"Computer2")
				.build()
				);
		
		when(mockComputerDAO.findAll(new Page(),  null)).thenReturn(listComputer);
		
		try {
			
			
			List<ComputerDTOOutput> listComputer2 = ComputerMapper.getInstance().mapToListComputerDTOOutput( mockComputerDAO.findAll(new Page(), null));
			assertEquals(2, listComputer.size());
			
			assertEquals(1, listComputer2.get(0).getId());
			assertEquals("Computer1", listComputer2.get(0).getName());
			assertEquals(LocalDate.of(2000, 05, 01), listComputer2.get(0).getIntroduced());
			assertEquals(LocalDate.of(2010, 06, 02), listComputer2.get(0).getDiscontinued());
			assertEquals("4", listComputer.get(0).getCompany().getId());
			
			assertEquals(2, listComputer2.get(1).getId());
			assertEquals("Computer2", listComputer2.get(0).getName());
			assertEquals(null, listComputer2.get(0).getIntroduced());
			assertEquals(null, listComputer2.get(0).getDiscontinued());
			assertEquals(null, listComputer.get(0).getCompany());
			
			
		} catch ( RuntimeException e) {
			fail("Should not throw an exception");
		}
	}
	

}
