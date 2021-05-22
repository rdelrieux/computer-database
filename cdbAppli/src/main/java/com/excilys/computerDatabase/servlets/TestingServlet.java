package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.connection.CdbConnection;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.vue.CLI;

/**
 * Servlet implementation class TestingServlet
 */
@WebServlet("/exo")
public class TestingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String parametreAuteur = request.getParameter("auteur");
		
		String message = "Transmission de variables : OK ! " + parametreAuteur;
		
		ComputerService computerService = ComputerService.getInstance();
		//System.out.println(CdbConnection.getInstance().getConnection() == null);
		
		Page page = new Page();
		page.setNombreElementRequet(computerService.searchNombreElement());
		List<Computer> listcomputer = new ArrayList<Computer>(); 
		listcomputer = computerService.getListComputer( page);
		
	
		
			
		request.setAttribute( "listcomputer", listcomputer );
			
		/* Transmission de la paire d'objets request/response à notre JSP */
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        
        request.setAttribute("nom", nom);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/bonjour.jsp").forward(request, response);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
