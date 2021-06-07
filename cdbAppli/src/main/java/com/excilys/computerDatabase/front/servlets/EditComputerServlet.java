package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerDatabase.back.dataBase.exception.CompanyNotFoundException;
import com.excilys.computerDatabase.back.dataBase.exception.ComputerNotFoundException;
import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.exception.ValidateurDTOException;
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

	@Autowired
	private ComputerService computerService ;
	@Autowired
    private CompanyService companyService ;
	@Autowired
	private ComputerMapper computerMapper ;
	@Autowired
	private CompanyMapper companyMapper ;

	private HttpSession session;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.session =request.getSession();	
		
		this.showListCompany();
		
		
		try {
			ComputerDTOOutput computer = this.findEditComputer(request.getParameter("id"));
			request.setAttribute("computer", computer);
			request.setAttribute("companyId", ""+this.companyService.getCompany(computer.getCompanyName()).getId());
			
		}catch (DAOException  | ComputerNotFoundException e ){
			LoggerCdb.logInfo(EditComputerServlet.class.toString(), e);
			response.sendRedirect(VUE_DASHBOARD);
		}catch (CompanyNotFoundException e ){
			LoggerCdb.logInfo(EditComputerServlet.class.toString(), e);
		}
		
		try {
			this.getServletContext().getRequestDispatcher(VUE_EDIT_COMPUTER).forward(request, response);
		}catch (IllegalStateException e) {
			LoggerCdb.logInfo(EditComputerServlet.class.toString(), "Computer to edit Not Found");
		}

		
		
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
		
		}catch (DAOException | ValidateurDTOException e  ){
			LoggerCdb.logWarn(EditComputerServlet.class.getName(), e);
			response.sendRedirect(VUE_ERREUR+request.getParameter("id"));
		}
		
	}
	
	
	
	private ComputerDTOOutput findEditComputer (String idS ) {
		if (idS != null) {
			session.setAttribute("editId", idS);
		}
		try {
			int id = Integer.valueOf((String) session.getAttribute("editId"));
			return computerMapper.mapToComputerDTOOutput(computerService.getComputer(id)) ;
			
		} catch (NumberFormatException | DAOException | ComputerNotFoundException e) {
			LoggerCdb.logInfo(EditComputerServlet.class.getName(), e);
			throw new ComputerNotFoundException();
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
