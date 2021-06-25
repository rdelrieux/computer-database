package com.excilys.computerDatabase.web.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.core.exception.dao.DAOException;
import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.page.Order;
import com.excilys.computerDatabase.core.page.OrderBy;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTO;
import com.excilys.computerDatabase.web.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.web.session.Session;




@Controller
public class DashBoardServlet  {


	private static final String VUE_DASHBOARD_REDIRECT = "redirect:/dashboard";
	private static final String VUE_DASHBOARD = "dashboard";

	private ComputerService computerService;

	// @Qualifier("computerMapperCtr")
	private ComputerMapper computerMapper;

	private Session session;

	public DashBoardServlet(ComputerService computerService, ComputerMapper computerMapper, Session session) {
		super();
		this.computerService = computerService;
		this.computerMapper = computerMapper;
		this.session = session;
	}
	
	
	
	@GetMapping(value = {"/","/login"})
	public String getIndex() {
		return "login"; 
	}
	/*
	@PostMapping("/login")
	public String login() {
		return VUE_DASHBOARD_REDIRECT ; 
	}
	*/
	@GetMapping("/403")
	public String accessDenied() {
	    return "403";
	}


	@GetMapping(value = "/search", params = "page")
	public String updateNumPage(@RequestParam("page") int numPage) {
		this.session.getPage().setNumPage(numPage);
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/search", params = "nombreElementPage")
	public String updateNombreElementPage(@RequestParam("nombreElementPage") int nombreElementPage) {
		if (nombreElementPage > 1 && nombreElementPage <= 100) {
			this.session.getPage().setNumPage(1 + (this.session.getPage().getNumPage() - 1) * this.session.getPage().getNombreElementPage() / nombreElementPage);
			this.session.getPage().setNombreElementPage(nombreElementPage);
		}
		
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/search", params = "search")
	public String updateSearch(@RequestParam("search") String search) {
		this.session.getPage().setSearch(search);
		this.session.getPage().goToFirstPage();
		this.session.getPage().setOrderBy(OrderBy.COMPUTER_NAME);
		this.session.getPage().setOrder(Order.ASCENDANT);
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/search", params = {"orderBy", "order"} )
	public String updateOrder(@RequestParam("orderBy") String orderBy, @RequestParam("order") String order) {
		this.session.getPage().goToFirstPage();
		this.session.getPage().setOrderBy(OrderBy.getOrderBy(orderBy));
		this.session.getPage().setOrder(Order.getOrder(order));
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/reset")
	public String reset() {
		this.session.getPage().setSearch("");
		this.session.getPage().goToFirstPage();
		this.session.getPage().setOrderBy(OrderBy.COMPUTER_NAME);
		this.session.getPage().setOrder(Order.ASCENDANT);
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("selection") String selection) {
		try {
			this.computerService.delete(selection);
		} catch ( DAOException e) {
			LoggerCdb.logWarn(DashBoardServlet.class.toString(), e);
			this.session.getError().put("DAOException", e.getMessage());
		}
		return VUE_DASHBOARD_REDIRECT;
	}
	

	@GetMapping(value = "/dashboard")
	protected ModelAndView displayDashboard()
			throws ServletException, IOException {
		
		ModelAndView mv = new ModelAndView(VUE_DASHBOARD);
		mv.addObject("session", this.session );
		
		try {
		mv.addObject("listcomputer", this.getListComputer()	);
		} catch ( DAOException e) {
			LoggerCdb.logWarn(DashBoardServlet.class.toString(), e);
		}
		
		
		return mv;
	}

	private List<ComputerDTO> getListComputer() {
		this.session.getPage().setNombreElementRequet(this.computerService.count(session.getPage().getSearch()));
		return computerService.find(session.getPage()).stream().map(c -> this.computerMapper.mapToComputerDTO(c))
				.collect(Collectors.toList());
	}


}
