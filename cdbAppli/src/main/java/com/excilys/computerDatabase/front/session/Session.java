package com.excilys.computerDatabase.front.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.Order;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {
	
	
	private Page page ;
	
	private String search;
	
	private OrderBy orderBy;
	
	private Order order;
	
	private ComputerDTOAdd computerDTOAdd;
	
	private ComputerDTOUpdate computerDTOUpdate;

	public Session() {
		 this.page  = new Page();
		 this.search = "";
		 this.orderBy = OrderBy.COMPUTER_NAME;
		 this.order = Order.ASCENDANT;
		 this.computerDTOAdd = new ComputerDTOAdd();
	}
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderby) {
		this.orderBy = orderby;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	

}
