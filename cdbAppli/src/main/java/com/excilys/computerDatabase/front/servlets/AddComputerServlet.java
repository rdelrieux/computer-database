package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.exception.ValidateurDTOException;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.binding.validateur.ComputerValidateur;
import com.excilys.computerDatabase.logger.LoggerCdb;



@Component
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService ;
	@Autowired
    private CompanyService companyService ;
	@Autowired
    private ComputerMapper computerMapper;
	@Autowired
    private CompanyMapper companyMapper ;
	@Autowired
	private ComputerValidateur computerValidateur;
    

    private static final String ATT_COMPANY_LIST = "listCompany";
	private static final String VUE_DASHBOARD = "dashboard";
	private static final String VUE_ADD_COMPUTER = "/WEB-INF/jsp/addComputer.jsp";

	private HttpSession session;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		this.showListCompany();
		
       this.getServletContext().getRequestDispatcher(VUE_ADD_COMPUTER).forward(request, response);

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		this.showListCompany();
		
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder(request.getParameter("computerName"))
				.withIntroduced(request.getParameter("introduced"))
				.withDiscontinued(request.getParameter("discontinued"))
				.withCompanyId( request.getParameter("companyId"))
				.build();
		
		
		try {
			Map<String, ValidateurDTOException> errors = computerValidateur.validate(computerDTOAdd);
			if (errors.isEmpty()) {
				this.computerService.addComputer(this.computerMapper.mapToComputer(computerDTOAdd) );
				response.sendRedirect(VUE_DASHBOARD);
			}else {
				session.setAttribute( "errors" , errors  );
			}
			
			
			
			
			
		}catch ( DAOException e){
			LoggerCdb.logWarn(AddComputerServlet.class.getName(), e);
			this.getServletContext().getRequestDispatcher(VUE_ADD_COMPUTER).forward(request, response);
		}
	}
	
	private void showListCompany() {
		List<Company> listCompany = companyService.getListCompany();
		
		List<CompanyDTO> listCompanyDTO =  listCompany.stream()
				.map(c -> this.companyMapper.mapToCompanyDTO(c) )
				.collect(Collectors.toList());
		
		session.setAttribute( ATT_COMPANY_LIST ,  listCompanyDTO );
	}



}
