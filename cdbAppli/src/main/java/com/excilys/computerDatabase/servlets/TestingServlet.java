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

import com.excilys.computerDatabase.bean.Coyote;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

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
		
	
		
			
		/* Création du bean */
		List<Computer> premierBean = new ArrayList<Computer>();
		/* Initialisation de ses propriétés */
		premierBean.add( new Computer(1,"computer1",LocalDate.of(2010, 02, 16), null , new Company (13,"IBM")) );
		premierBean.add( new Computer(2,"computer2",LocalDate.of(2011, 02, 16), null , new Company (13,"IBM")) );
			
		/* Stockage du message et du bean dans l'objet request */
		request.setAttribute( "message", message );
		request.setAttribute( "listCoyote", premierBean );
			
		/* Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
	}

	
}
