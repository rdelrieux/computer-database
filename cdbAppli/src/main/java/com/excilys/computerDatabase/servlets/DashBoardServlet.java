package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.service.ComputerService;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_SEARCH = "search";
	private static final String ATT_PAGE = "page";
	
	private static final String VUE_DASHBOARD = "/WEB-INF/jsp/dashboard.jsp";
	
	
	private ComputerService computerService;

	private HttpSession session;
    

    public DashBoardServlet() {
    	computerService  = ComputerService.getInstance(); 
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		this.session = request.getSession();
		
		if (session.getAttribute(ATT_PAGE) == null) {
			this.initialisationSession(session);
		}
		
		String paramNombreElementPage = request.getParameter("nombreElementPage");
		String numPage = request.getParameter("Page");
		
		this.updatePage(session , paramNombreElementPage ,  numPage );
		Page page = (Page)session.getAttribute(ATT_PAGE);
		
		String paramSearch = request.getParameter("search");
		this.updateSearch(session , paramSearch);
		
		String search =  ""+session.getAttribute(ATT_SEARCH);
		
		List<Computer> listcomputer = this.getListComputer(page , search);
		
		request.setAttribute("listcomputer", listcomputer);
		
        this.getServletContext().getRequestDispatcher(VUE_DASHBOARD).forward(request, response);

	}
	



	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
	
	private void initialisationSession(HttpSession session) {
		session.setAttribute( ATT_PAGE , new Page());
		session.setAttribute(ATT_SEARCH, "");
	}
	

	private void updatePage(HttpSession session, String paramNombreElementPage , String numPage) {
		Page page = (Page)session.getAttribute(ATT_PAGE);
		if (paramNombreElementPage != null) {
		
			page.setNumPage( 1+(page.getNumPage()-1)*page.getNombreElementPage()/Integer.valueOf(paramNombreElementPage));
			page.setNombreElementPage(Integer.valueOf(paramNombreElementPage) );
		}
		if  (numPage != null &&  Integer.valueOf(numPage) <= page.getNombrePageMax()) {
			page.setNumPage(Integer.valueOf(numPage));
		}
		session.setAttribute(ATT_PAGE, page);

	}

	private void updateSearch(HttpSession session, String paramSearch) {
		if  (paramSearch != null  ) {
			Page page = (Page)session.getAttribute(ATT_PAGE);
			session.setAttribute(ATT_SEARCH, paramSearch);
			page.setNumPage(1);
			session.setAttribute(ATT_PAGE, page);
		}
		
	}

	

	private List<Computer> getListComputer( Page page , String search) {
		List<Computer> listcomputer = new ArrayList<Computer>(); 
		
		if (search == "") {
			page.setNombreElementRequet(computerService.searchNombreElement());
			listcomputer =   computerService.getListComputer(page);
		}else {
			page.setNombreElementRequet(computerService.searchNombreElement(search));
			listcomputer =   computerService.searchComputer(search, page);
			
		}
		return listcomputer;
	
	}



}

	
