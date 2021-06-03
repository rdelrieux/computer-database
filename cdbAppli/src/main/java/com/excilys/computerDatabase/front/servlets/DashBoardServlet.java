package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.logger.LoggerCdb;


@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_SEARCH = "search";
	private static final String ATT_ORDER_BY = "orderBy";
	private static final String ATT_PAGE = "page";
	
	
	private static final String VUE_DASHBOARD_REDIRECT = "dashboard";
	private static final String VUE_DASHBOARD = "/WEB-INF/jsp/dashboard.jsp";
	
	
	private ComputerService computerService;
	private ComputerMapper computerMapper;
	private HttpSession session;
    

    public DashBoardServlet() {
    	computerService  = ComputerService.getInstance(); 
    	computerMapper = ComputerMapper.getInstance();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.session = request.getSession();
		
		if (session.getAttribute(ATT_PAGE) == null) {
			this.initialisationSession(session);
		}
		
		
		String paramNombreElementPage = request.getParameter("nombreElementPage");
		String numPage = request.getParameter("page");
		this.updatePage( paramNombreElementPage ,  numPage );		
		
		String paramSearch = request.getParameter("search");
		this.updateSearch( paramSearch);
		
		String paramOrderBy = request.getParameter("orderBy");
		this.updateOrderBy( paramOrderBy);
				
		List<ComputerDTOOutput> listComputer = this.getListComputer(session);
		request.setAttribute("listcomputer", listComputer);
		
        this.getServletContext().getRequestDispatcher(VUE_DASHBOARD).forward(request, response);

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session = request.getSession();
		
		List<String> selection = Arrays.asList(request.getParameter("selection").split(","));
		
		try {
			selection.stream()
			.map(s -> Integer.valueOf(s) )
			.forEach(id -> this.computerService.deletComputer(id) );
		}catch (NumberFormatException | DAOException e) {
			
			LoggerCdb.logWarn(DashBoardServlet.class.toString(), e);
		}
		
		this.updateSearch(""+session.getAttribute(ATT_SEARCH));
		response.sendRedirect(VUE_DASHBOARD_REDIRECT);
		
    }
	
	private void initialisationSession(HttpSession session) {
		session.setAttribute( ATT_PAGE , new Page());
		session.setAttribute(ATT_SEARCH, "");
		session.setAttribute(ATT_ORDER_BY,  OrderBy.COMPUTER_NAME );
	}
	

	private void updatePage( String paramNombreElementPage , String paramNumPage) {
		Page page = (Page)session.getAttribute(ATT_PAGE);
		try {
			int nombreElementPage = Integer.valueOf(paramNombreElementPage);
			int numPage =  Integer.valueOf(paramNumPage); 
			if (nombreElementPage>1 && nombreElementPage<100) {
				page.setNumPage( 1+(page.getNumPage()-1)*page.getNombreElementPage()/nombreElementPage);
				page.setNombreElementPage(nombreElementPage );
			}
			if  (numPage <= page.getNombrePageMax()) {
				page.setNumPage(numPage);
			}
			
		}catch (NullPointerException | NumberFormatException e) {
			LoggerCdb.logInfo(DashBoardServlet.class.toString(), e);
			
		}
		session.setAttribute(ATT_PAGE, page);

	}

	private void updateSearch( String paramSearch) {
		if  (paramSearch != null  ) {
			Page page = (Page)session.getAttribute(ATT_PAGE);
			session.setAttribute(ATT_SEARCH, paramSearch);
			session.setAttribute(ATT_ORDER_BY, OrderBy.COMPUTER_NAME);
			page.setNumPage(1);
			session.setAttribute(ATT_PAGE, page);
		}
		
	}


	private void updateOrderBy(String paramOrderBy) {
		if  (paramOrderBy != null  ) {
			Page page = (Page)session.getAttribute(ATT_PAGE);
			String search = ""+session.getAttribute(ATT_SEARCH);
			session.setAttribute(ATT_SEARCH, search);
			
			session.setAttribute(ATT_ORDER_BY, OrderBy.getOrderBy(paramOrderBy));
			page.setNumPage(1);
			session.setAttribute(ATT_PAGE, page);
		}
		
	}


	private List<ComputerDTOOutput> getListComputer( HttpSession session) {
		Page page = (Page)session.getAttribute(ATT_PAGE);
		String search = ""+session.getAttribute(ATT_SEARCH);
		OrderBy orderBy =  (OrderBy) session.getAttribute(ATT_ORDER_BY);
		
		
		List<Computer> listcomputer = new ArrayList<Computer>(); 
		
		if ("".equals(search)) {
			page.setNombreElementRequet(computerService.searchNombreElement());
			listcomputer =   computerService.getListComputer(page,orderBy);
		}else {
			page.setNombreElementRequet(computerService.searchNombreElement(search));
			listcomputer =   computerService.searchComputer(search, page, orderBy);
		}
		
		return listcomputer.stream()
				.map(c -> this.computerMapper.mapToComputerDTOOutput(c) )
				.collect(Collectors.toList());	
	}

	
}

	
