package com.excilys.computerDatabase.front.servlets;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.binding.validateur.ComputerValidateur;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Controller
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_COMPANY_LIST = "listCompany";
	private static final String VUE_DASHBOARD_REDIRECT = "redirect:/dashboard";
	private static final String VUE_EDIT_COMPUTER_REDIRECT = "redirect:/editComputer?id=";
	private static final String VUE_EDIT_COMPUTER = "editComputer";

	private ComputerService computerService ;
    private CompanyService companyService ;
	private ComputerMapper computerMapper ;
	private CompanyMapper companyMapper ;
	private ComputerValidateur computerValidateur;
	
	private Session session;
	
	
	public EditComputerServlet(ComputerService computerService, CompanyService companyService,
			ComputerMapper computerMapper, CompanyMapper companyMapper, ComputerValidateur computerValidateur
			,Session session) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.companyMapper = companyMapper;
		this.computerValidateur = computerValidateur;
		this.session = session;
	}

	
	@GetMapping(value = "/editComputer" , params = {"id"})
	protected ModelAndView displayNewComputerUpdate(@RequestParam("id") int id) {
		session.setComputerDTOUpdate( computerMapper.mapToComputerDTOUpdate(computerService.getComputer(id)) );
		return this.displayComputer();
	}
	
	@GetMapping(value = "/editComputer" )
	private ModelAndView displayComputerUpdate() {
		return this.displayComputer();
	}
	
	
	private ModelAndView displayComputer() {	
		ModelAndView mv = new ModelAndView(VUE_EDIT_COMPUTER);
		List<CompanyDTO> listcompany = this.getListCompany();
		mv.addObject(ATT_COMPANY_LIST, listcompany);
		mv.addObject("computer", session.getComputerDTOUpdate());
		
		if (! "".equals(session.getComputerDTOUpdate().getCompanyId()) ) {
			mv.addObject("companyName", listcompany.stream()
					.filter(c -> c.getId().toString().equals(session.getComputerDTOUpdate().getCompanyId()))
					.collect(Collectors.toList()).get(0).getName()
					);
		}
		return mv;

	}

	private List<CompanyDTO> getListCompany() {
		return this.companyService.getListCompany().stream().map(c -> this.companyMapper.mapToCompanyDTO(c))
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/editComputer" )
	protected ModelAndView addComputer(
			@ModelAttribute("computer") @Validated ComputerDTOUpdate computerDTOUpdate,
			BindingResult bindingResult ) {

		ModelAndView mv = new ModelAndView(VUE_EDIT_COMPUTER_REDIRECT+session.getComputerDTOUpdate().getId());

		computerValidateur.validate(computerDTOUpdate, bindingResult);
		if (! bindingResult.hasErrors() ) {
			session.setComputerDTOUpdate(computerDTOUpdate);
			try {
				this.computerService.updateComputer(this.computerMapper.mapToComputer(computerDTOUpdate));
				return new ModelAndView(VUE_DASHBOARD_REDIRECT);
				
		 }catch ( DAOException e){
			  LoggerCdb.logError(AddComputerServlet.class.getName(), e);
		 }
		
		}
				
		return mv;
	}
	
	@GetMapping("/editComputer/cancel")
	public String cancel () {
		this.session.setComputerDTOUpdate(new ComputerDTOUpdate());
		return VUE_DASHBOARD_REDIRECT;
	}
	
	
	
}
