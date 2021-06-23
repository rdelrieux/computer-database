package com.excilys.computerDatabase.web.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.web.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.web.binding.mapper.CompanyMapper;

@RestController
@RequestMapping("/api")
public class CompanyAPI {
	
	private CompanyService companyService;

	private CompanyMapper companyMapper;

	public CompanyAPI(CompanyService companyService, CompanyMapper companyMapper) {
		super();
		this.companyService = companyService;
		this.companyMapper = companyMapper;
	}
	
	@GetMapping(value = "/companies", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@GetMapping(produces = { "application/xml","application/json"})
	public List<CompanyDTO> getAll(){
		return companyMapper.mapToCompanyDTO(companyService.findAll());
	}
	
	@GetMapping(value = "/companies/count" )
	public long countAll(){
		return companyService.countAll();
	}
	
	@GetMapping(value = "/company/id/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public CompanyDTO find(@PathVariable int id){
		return companyMapper.mapToCompanyDTO(companyService.find(id));
	}
	
	@GetMapping(value = "/company/{name}" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public CompanyDTO find(@PathVariable String name){
		return companyMapper.mapToCompanyDTO(companyService.find(name));
	}
	
	@PostMapping(value = "/company" , consumes = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseStatus
	public void create( @RequestBody CompanyDTO companyDTO){
		companyService.create(companyDTO.getName()); 
	}
	
	@DeleteMapping(value = "/company"  )
	public void delete(@RequestBody CompanyDTO companyDTO){
		companyService.delete(Integer.valueOf(companyDTO.getId()));
	}
	
	
	
	
	
}
