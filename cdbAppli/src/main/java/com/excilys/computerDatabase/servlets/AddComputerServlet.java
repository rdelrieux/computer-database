package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;




@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComputerService computerService = ComputerService.getInstance();

    private CompanyService companyService = CompanyService.getInstance();

   
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		List<Company> listCompany = companyService.getListCompany();
		request.setAttribute( "listCompany", listCompany );

		
       this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated 
		List<Company> listCompany = companyService.getListCompany();
		request.setAttribute( "listCompany", listCompany );
		
		
		ComputerDTOInput computerDTOInput = new ComputerDTOInput.ComputerDTOInputBuilder(request.getParameter("computerName"))
				.withIntroduced(request.getParameter("introduced"))
				.withDiscontinued(request.getParameter("discontinued"))
				.withCompanyId(request.getParameter("companyId"))
				.build();
		
		//System.out.println(computerDTOInput);
		this.computerService .addComputer(computerDTOInput);
		
		
		   
		 this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);
	}


}
