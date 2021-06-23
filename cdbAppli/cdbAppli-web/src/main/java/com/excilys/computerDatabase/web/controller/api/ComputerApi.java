package com.excilys.computerDatabase.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerDatabase.core.page.Page;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTO;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.web.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.web.binding.validateur.ComputerValidateur;

@RestController
@RequestMapping("/api")
public class ComputerApi {
	
	private ComputerMapper computerMapper;
	private ComputerService computerService;
	private ComputerValidateur validateur;

		
	public ComputerApi(ComputerMapper computerMapper, ComputerService computerService, ComputerValidateur validateur) {
		super();
		this.computerMapper = computerMapper;
		this.computerService = computerService;
		this.validateur = validateur;
	}


	@GetMapping(value = "/computers/count/{search}" )
	public long count(@PathVariable String search){
		return computerService.count(search);
	}
	
	@GetMapping(value = "/computers/count" )
	public long count(){
		return computerService.count("");
	}
	
	@GetMapping(value = "/computers" )
	public List<ComputerDTO> count(@RequestBody Page page){
		return computerMapper.mapToListComputerDTO( computerService.find(page));
	}
	
	@GetMapping(value = "/computer/id/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ComputerDTO find(@PathVariable int id){
		return computerMapper.mapToComputerDTO(computerService.find(id));
	}
	
	@PostMapping(value = "/computer" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create( @RequestBody @Valid ComputerDTOAdd computerDTOAdd,
			BindingResult bindingResult) {
		
		validateur.validate(computerDTOAdd, bindingResult);
		if (!bindingResult.hasErrors()) {
			computerService.create(computerMapper.mapToComputer(computerDTOAdd)); 
		}
	}
	
	@PutMapping(value = "/computer" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update( @RequestBody @Valid ComputerDTOUpdate computerDTOUpdate,
			BindingResult bindingResult) {
		
		validateur.validate(computerDTOUpdate, bindingResult);
		if (!bindingResult.hasErrors()) {
			computerService.update(computerMapper.mapToComputer(computerDTOUpdate)); 
		}
	}
		
	@DeleteMapping(value = "/computer" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody ComputerDTO computerDTO){
		computerService.delete(Integer.valueOf(computerDTO.getId()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
