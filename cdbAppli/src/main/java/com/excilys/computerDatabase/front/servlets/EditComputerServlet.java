package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.logger.LoggerCdb;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_COMPANY_LIST = "listCompany";
	private static final String VUE_DASHBOARD = "dashboard";
	private static final String VUE_ERREUR = "editComputer?id=";
	private static final String VUE_EDIT_COMPUTER = "/WEB-INF/jsp/editComputer.jsp";

	private ComputerService computerService = ComputerService.getInstance();

    private CompanyService companyService = CompanyService.getInstance();
    private ComputerMapper computerMapper = ComputerMapper.getInstance();
    private CompanyMapper companyMapper = CompanyMapper.getInstance();

	private HttpSession session;

	public EditComputerServlet() {
		computerService = ComputerService.getInstance();
		computerMapper = ComputerMapper.getInstance();
		companyService = CompanyService.getInstance();
		companyMapper = CompanyMapper.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.session =request.getSession();	
		
		this.showListCompany();
		
		
		try {
			ComputerDTOOutput computer = this.findEditComputer(request.getParameter("id")).orElseThrow();
			request.setAttribute("computer", computer);
			request.setAttribute("companyId", ""+this.companyService.getCompany(computer.getCompanyName()).getId());
			
		}catch (NullPointerException e){
			LoggerCdb.logInfo(EditComputerServlet.class.toString(), e);
		}
		
		

		
		this.getServletContext().getRequestDispatcher(VUE_EDIT_COMPUTER).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		this.showListCompany();
		

		

	
		
		ComputerDTOUpdate computerDTOUpdate = new ComputerDTOUpdate.ComputerDTOUpdateBuilder(request.getParameter("id"), request.getParameter("computerName"))
				.withIntroduced(request.getParameter("introduced"))
				.withDiscontinued(request.getParameter("discontinued"))
				.withCompanyId( request.getParameter("companyId") )
				.build();
		
		
		
		try {
			
			this.computerService.updateComputer( this.computerMapper.mapToComputer(computerDTOUpdate));
			
			response.sendRedirect(VUE_DASHBOARD);
		
		}catch (RuntimeException e){
			LoggerCdb.logWarn(AddComputerServlet.class.getName(), e);
			
			response.sendRedirect(VUE_ERREUR+request.getParameter("id"));
		}
		
	}
	
	
	
	private Optional<ComputerDTOOutput> findEditComputer (String idS ) {
		if (idS != null) {
			session.setAttribute("editId", idS);
		}
		try {
			int id = Integer.valueOf((String) session.getAttribute("editId"));
			return Optional.of( computerMapper.mapToComputerDTOOutput(computerService.getComputer(id)) );
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	private void showListCompany() {
		List<Company> listCompany = companyService.getListCompany();
		List<CompanyDTO> listCompanyDTO =  listCompany.stream()
		.map(c -> this.companyMapper.mapToCompanyDTO(c) )
		.collect(Collectors.toList());
		session.setAttribute( ATT_COMPANY_LIST ,  listCompanyDTO );
		
	}

}
