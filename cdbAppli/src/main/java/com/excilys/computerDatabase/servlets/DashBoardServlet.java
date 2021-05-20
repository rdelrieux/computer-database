package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.service.ComputerService;

@WebServlet("/dashBoard")
public class DashBoardServlet extends HttpServlet {
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
		this.goToNextPervPage( request.getParameter("paginationNextPrev"));
		List<Computer> listcomputer = this.getListComputer(request.getParameter("search"));
		
		
		request.setAttribute( "nombreComputerTrouve", page.getNombreElementRequet() );
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
			listcomputer =   computerService.getListComputer( page);
		}else {
			page.setNombreElementRequet(computerService.searchNombreElement(search));
			listcomputer =   computerService.searchComputer(search, page);
			
		}
		return listcomputer;
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

	
	private void goToNextPervPage(String paginationNextPrev) {
		if (paginationNextPrev != null) {
			if (paginationNextPrev.equals("prev")) {
				this.page.setPageBefore();	
			}else if (paginationNextPrev.equals("next")) {
				this.page.setPageAfter();
				
			}
		}
	}


}

	
