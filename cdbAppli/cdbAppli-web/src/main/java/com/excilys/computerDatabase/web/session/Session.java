package com.excilys.computerDatabase.web.session;

import java.util.HashMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.core.page.Page;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOUpdate;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {

	private Page page;

	private HashMap<String, String> error;

	private ComputerDTOAdd computerDTOAdd;

	private ComputerDTOUpdate computerDTOUpdate;

	public Session() {
		this.page = new Page();
		this.computerDTOAdd = new ComputerDTOAdd();
		this.error = new HashMap<>();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ComputerDTOAdd getComputerDTOAdd() {
		return computerDTOAdd;
	}

	public void setComputerDTOAdd(ComputerDTOAdd computerDTOAdd) {
		this.computerDTOAdd = computerDTOAdd;
	}

	public ComputerDTOUpdate getComputerDTOUpdate() {
		return computerDTOUpdate;
	}

	public void setComputerDTOUpdate(ComputerDTOUpdate computerDTOUpdate) {
		this.computerDTOUpdate = computerDTOUpdate;
	}

	public HashMap<String, String> getError() {
		return this.error;
	}

}
