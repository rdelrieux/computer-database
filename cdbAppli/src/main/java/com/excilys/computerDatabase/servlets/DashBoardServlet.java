package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.service.ComputerService;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {
	 final static Logger LOGGER= LogManager.getLogger(DashBoardServlet.class);
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
    private String search = "";
    private Page page = new Page();
    

    public DashBoardServlet() {
    	computerService  = ComputerService.getInstance(); 
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		String paramNombreElementPage = request.getParameter("nombreElementPage");
		this.addNumPage( request.getParameter("addNumPage"));
		
		this.page.setNombreElementPage( paramNombreElementPage == null ?   this.page.getNombreElementPage() : Integer.valueOf(paramNombreElementPage)   ) ;
		this.goToFisrtLastPage( request.getParameter("paginationFisrtLast"));
		List<Computer> listcomputer = this.getListComputer(request.getParameter("search"));
		
		
		//request.setAttribute( "nombrePageMax", page.getNombrePageMax() );
		request.setAttribute( "listcomputer", listcomputer );
		request.setAttribute( "page", page);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);

	}
	
	
	private void addNumPage(String parameter) {
		if (parameter != null) {
			this.page.addNumPage(Integer.valueOf(parameter));
		}
		
	}


	private List<Computer> getListComputer(String paramSearch) {
		List<Computer> listcomputer = new ArrayList<Computer>(); 
		if (paramSearch != null) {
			page.setNumPage(1);
			search = paramSearch;
		}
		
		
		if (this.search == "") {
			page.setNombreElementRequet(computerService.searchNombreElement());
			listcomputer =   computerService.getListComputer(page);
		}else {
			page.setNombreElementRequet(computerService.searchNombreElement(search));
			listcomputer =   computerService.searchComputer(search, page);
			
		}
		return listcomputer;
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

	
	private void goToFisrtLastPage(String paginationFisrtLast) {
		if (paginationFisrtLast != null) {
			if (paginationFisrtLast.equals("fisrt")) {
				this.page.setNumPage(1);	
			}else if (paginationFisrtLast.equals("last")) {
				this.page.setNumPage(this.page.getNombrePageMax());
				
			}
		}
	}


}

	
