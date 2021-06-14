package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;

import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.session.Order;
import com.excilys.computerDatabase.front.session.OrderBy;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


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
		this.session.setSearch(search);
		this.session.getPage().goToFirstPage();
		this.session.setOrderBy(OrderBy.COMPUTER_NAME);
		this.session.setOrder(Order.ASCENDANT);
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/search", params = {"orderBy", "order"} )
	public String updateOrder(@RequestParam("orderBy") String orderBy, @RequestParam("order") String order) {
		this.session.getPage().goToFirstPage();
		this.session.setOrderBy(OrderBy.getOrderBy(orderBy));
		this.session.setOrder(Order.getOrder(order));
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@GetMapping(value = "/reset")
	public String reset() {
		this.session.setSearch("");
		this.session.getPage().goToFirstPage();
		this.session.setOrderBy(OrderBy.COMPUTER_NAME);
		this.session.setOrder(Order.ASCENDANT);
		return VUE_DASHBOARD_REDIRECT;
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("selection") String selection) {
		try {
			this.computerService.delete(selection);
		} catch ( DAOException e) {
			LoggerCdb.logWarn(DashBoardServlet.class.toString(), e);
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

	private List<ComputerDTOOutput> getListComputer() {
		this.session.getPage().setNombreElementRequet(this.computerService.searchNombreElementRequet(session.getSearch()));
		return computerService.searchComputer(session).stream().map(c -> this.computerMapper.mapToComputerDTOOutput(c))
				.collect(Collectors.toList());
	}


}
