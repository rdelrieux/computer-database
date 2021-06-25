package com.excilys.computerDatabase.web.servlets;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.persistence.exceptiondao.DAOException;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.web.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.web.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.web.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.web.binding.validateur.ComputerValidateur;
import com.excilys.computerDatabase.web.session.Session;



@Controller
public class AddComputerServlet {

	private static final String ATT_COMPANY_LIST = "listCompany";
	private static final String VUE_DASHBOARD_REDIRECT = "redirect:/addComputer/cancel";
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
		mv.addObject("errors", session.getError());

		
		if (!"".equals(session.getComputerDTOAdd().getCompanyId())) {
			mv.addObject("companyName",
					listcompany.stream()
							.filter(c -> c.getId().toString().equals(session.getComputerDTOAdd().getCompanyId()))
							.collect(Collectors.toList()).get(0).getName());
		}
		
		return mv;
	}

	private List<CompanyDTO> getListCompany() {
		return this.companyService.getListCompany().stream().map(c -> this.companyMapper.mapToCompanyDTO(c))
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/addComputer")
	public ModelAndView addComputer(@ModelAttribute("computer") @Valid ComputerDTOAdd computerDTOAdd,
			BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView(VUE_ADD_COMPUTER_REDIRECT);
		session.setComputerDTOAdd(computerDTOAdd);
		session.getError().clear();
		
		computerValidateur.validate(computerDTOAdd, bindingResult);
		if (!bindingResult.hasErrors()) {
			try {
				this.computerService.addComputer(this.computerMapper.mapToComputer(computerDTOAdd));
				this.session.setComputerDTOAdd(new ComputerDTOAdd());
				return new ModelAndView(VUE_DASHBOARD_REDIRECT);

			} catch (DAOException e) {
				LoggerCdb.logError(AddComputerServlet.class.getName(), e);
				this.session.getError().put("DAOException", e.getMessage());
			}

		}else {
			bindingResult.getFieldErrors().stream().forEach(e -> this.session.getError().put(e.getField(), e.getDefaultMessage()) );
		}

		return mv;
	}
	
	@GetMapping("/addComputer/cancel")
	public String cancel() {
		this.session.setComputerDTOAdd(new ComputerDTOAdd());
		this.session.getError().clear();
		return VUE_DASHBOARD;
	}

}
