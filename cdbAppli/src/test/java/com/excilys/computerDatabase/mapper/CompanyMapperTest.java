package com.excilys.computerDatabase.mapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.model.Company;

public class CompanyMapperTest {
	
	@Test
	void testMapToCompanyShouldReturnListOfCompanies() {
		ResultSet mockResultSet = mock(ResultSet.class);
		try {
			when(mockResultSet.next()).thenReturn(true).thenReturn(false);
			when(mockResultSet.getInt("id")).thenReturn(1);
			when(mockResultSet.getString("name")).thenReturn("WR7");
			List<CompanyDTOSQL> listCompanyDTOSQL = CompanyMapper.getInstance().toListCompanyDTOSQL(mockResultSet);
			List<Company> listCompany = CompanyMapper.getInstance().toListCompany(listCompanyDTOSQL);
			assertEquals(1, listCompany.size());
			assertEquals(1, listCompany.get(0).getId());
			assertEquals("WR7", listCompany.get(0).getName());
			verify(mockResultSet, times(2)).next();
			verify(mockResultSet, times(1)).getInt("id");
			verify(mockResultSet, times(1)).getString("name");
		} catch (SQLException | RuntimeException e) {
			fail("Should not throw an exception");
		}
	}
	

}
