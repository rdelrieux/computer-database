package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.logger.LoggerCdb;




@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComputerService computerService = ComputerService.getInstance();

    private CompanyService companyService = CompanyService.getInstance();
    private ComputerMapper computerMapper = ComputerMapper.getInstance();
    

    private static final String ATT_COMPANY_LIST = "listCompany";

	
	private static final String VUE_DASHBOARD = "dashboard";
	private static final String VUE_ADD_COMPUTER = "/WEB-INF/jsp/addComputer.jsp";

	
   
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		List<Company> listCompany = companyService.getListCompany();
		session.setAttribute( ATT_COMPANY_LIST , listCompany );

		
       this.getServletContext().getRequestDispatcher(VUE_ADD_COMPUTER).forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<Company> listCompany = companyService.getListCompany();
		session.setAttribute( ATT_COMPANY_LIST , listCompany );
		
		
		ComputerDTOInput computerDTOInput = new ComputerDTOInput.ComputerDTOInputBuilder(request.getParameter("computerName"))
				.withIntroduced(request.getParameter("introduced"))
				.withDiscontinued(request.getParameter("discontinued"))
				.withCompanyId(request.getParameter("companyId"))
				.build();
		
		try {
			this.computerService.addComputer( this.computerMapper.mapToComputer(computerDTOInput));
			response.sendRedirect(VUE_DASHBOARD);
		}catch (RuntimeException e){
			LoggerCdb.logWarn(AddComputerServlet.class.getName(), e);
			 this.getServletContext().getRequestDispatcher(VUE_ADD_COMPUTER).forward(request, response);
		}
		
		
		
		   
	}


}
