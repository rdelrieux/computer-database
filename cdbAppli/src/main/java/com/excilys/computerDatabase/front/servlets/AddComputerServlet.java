package com.excilys.computerDatabase.front.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.exception.ValidateurDTOException;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.binding.validateur.ComputerValidateur;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Controller
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_COMPANY_LIST = "listCompany";
	private static final String VUE_DASHBOARD = "redirect:/dashboard";
	private static final String VUE_ADD_COMPUTER = "addComputer";
	private static final String VUE_ADD_COMPUTER_REDIRECT = "redirect:/addComputer";

	private ComputerService computerService;
	private CompanyService companyService;
	private ComputerMapper computerMapper;
	private CompanyMapper companyMapper;
	private ComputerValidateur computerValidateur;

	private Session session;

	public AddComputerServlet(ComputerService computerService, CompanyService companyService,
			ComputerMapper computerMapper, CompanyMapper companyMapper, ComputerValidateur computerValidateur,
			Session session) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.companyMapper = companyMapper;
		this.computerValidateur = computerValidateur;
		this.session = session;
	}

	@GetMapping(value = "/addComputer")
	protected ModelAndView displayComputer() {

		ModelAndView mv = new ModelAndView(VUE_ADD_COMPUTER);
		List<CompanyDTO> listcompany = this.getListCompany();
		mv.addObject(ATT_COMPANY_LIST, listcompany);
		mv.addObject("computer", session.getComputerDTOAdd());
		if (! "".equals(session.getComputerDTOAdd().getCompanyId()) ) {
			mv.addObject("companyName", listcompany.stream()
					.filter(c -> c.getId().toString().equals(session.getComputerDTOAdd().getCompanyId()))
					.collect(Collectors.toList()).get(0).getName()
					);
		}
		return mv;

	}

	private List<CompanyDTO> getListCompany() {
		return this.companyService.getListCompany().stream().map(c -> this.companyMapper.mapToCompanyDTO(c))
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/addComputer")
	protected ModelAndView addComputer(@ModelAttribute("computer") @Validated ComputerDTOAdd computerDTOAdd,
			BindingResult bindingResult ) {
		ModelAndView mv = new ModelAndView(VUE_ADD_COMPUTER_REDIRECT);
		session.setComputerDTOAdd(computerDTOAdd);


		computerValidateur.validate(computerDTOAdd, bindingResult);
		if (! bindingResult.hasErrors() ) {
			try {
				this.computerService.addComputer(this.computerMapper.mapToComputer(computerDTOAdd));
				this.session.setComputerDTOAdd(new ComputerDTOAdd());
				return new ModelAndView(VUE_DASHBOARD);
				
		 }catch ( DAOException e){
			  LoggerCdb.logError(AddComputerServlet.class.getName(), e);
			  
		 }
		
		}
				
		return mv;
	}

}
