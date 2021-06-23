package com.excilys.computerDatabase.persistence.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.model.Computer;
import com.excilys.computerDatabase.core.page.Order;
import com.excilys.computerDatabase.core.page.OrderBy;
import com.excilys.computerDatabase.core.page.Page;
import com.excilys.computerDatabase.persistence.binding.dto.CompanyEntity;
import com.excilys.computerDatabase.persistence.binding.dto.ComputerEntity;
import com.excilys.computerDatabase.persistence.binding.dto.ComputerEntityAdd;
import com.excilys.computerDatabase.persistence.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.persistence.exceptiondao.ComputerNotFoundException;
import com.excilys.computerDatabase.persistence.loggertime.Timed;



@Repository
public class ComputerDAO {

	
	private ComputerMapper computerMapper;
	private CriteriaBuilder critriaBuilder;
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	
	public ComputerDAO(ComputerMapper computerMapper, EntityManagerFactory entityManagerFactory) {
		super();
		this.computerMapper = computerMapper;
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.critriaBuilder = this.entityManager.getCriteriaBuilder();

	}


	@Timed
	public long count(String search) {
		
		CriteriaQuery<Long> criteriaQuery = critriaBuilder.createQuery(Long.class);
		Root<ComputerEntity> rootComputer = criteriaQuery.from(ComputerEntity.class);
		
		Join<ComputerEntity, CompanyEntity> rootCompany = rootComputer.join("company", JoinType.LEFT);
		Predicate withComputerNameContainSearch = critriaBuilder.like(rootComputer.get("name"), "%"+search+"%");
		Predicate withCompanyNameContainSearch = critriaBuilder.like(rootCompany.get("name"), "%"+search+"%");

		criteriaQuery.select(critriaBuilder.count( rootComputer))
		.where(critriaBuilder.or(withComputerNameContainSearch,withCompanyNameContainSearch,withCompanyNameContainSearch));
		
		return  entityManager.createQuery(criteriaQuery).getSingleResult();
		
	}


	@Timed
	public List<Computer> find(Page page) {
		CriteriaQuery<ComputerEntity> criteriaQuery = critriaBuilder.createQuery(ComputerEntity.class);	
		Root<ComputerEntity> rootComputer = criteriaQuery.from(ComputerEntity.class);
		Join<ComputerEntity, CompanyEntity> rootCompany = rootComputer.join("company", JoinType.LEFT);
		Predicate withComputerNameContainSearch = critriaBuilder.like(rootComputer.get("name"), "%"+page.getSearch()+"%");
		Predicate withCompanyNameContainSearch = critriaBuilder.like(rootCompany.get("name"), "%"+page.getSearch()+"%");
		
		//Predicate withIntroducedContainSearch = critriaBuilder.like(rootComputer.get("introduced"), "%"+session.getSearch()+"%");
		//Predicate withDiscontinuedContainSearch = critriaBuilder.like(root.get("discontinued"), "%"+session.getSearch()+"%");
		
		criteriaQuery.select( rootComputer)
		.where(critriaBuilder.or(withComputerNameContainSearch,withCompanyNameContainSearch));
		
		if (page.getOrder().equals(Order.ASCENDANT)) {
			if(page.getOrderBy().equals(OrderBy.COMPANY)) {
				criteriaQuery.orderBy( critriaBuilder.asc(rootCompany.get(page.getOrderBy().getColumnDataBase())));
			}else {
				criteriaQuery.orderBy( critriaBuilder.asc(rootComputer.get(page.getOrderBy().getColumnDataBase())));
			}
		}else {
			if(page.getOrderBy().equals(OrderBy.COMPANY)) {
				criteriaQuery.orderBy( critriaBuilder.desc(rootCompany.get(page.getOrderBy().getColumnDataBase())));
			}else {
				criteriaQuery.orderBy( critriaBuilder.desc(rootComputer.get(page.getOrderBy().getColumnDataBase())));

			}
		}
		
		
		TypedQuery<ComputerEntity> query  = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(page.getOffset());
		query.setMaxResults( page.getNombreElementPage());
		
		List<ComputerEntity> companyEntity = query.getResultList();
		return this.computerMapper.mapToComputer(companyEntity);
	}
	
	@Timed
	public Computer find(int id) {
		CriteriaQuery<ComputerEntity> criteriaQuery = critriaBuilder.createQuery(ComputerEntity.class);
		Root<ComputerEntity> root = criteriaQuery.from(ComputerEntity.class);

		Predicate withId = critriaBuilder.equal(root.get("id"), id);

		criteriaQuery.select(root).where(withId);

		TypedQuery<ComputerEntity> query = this.entityManager.createQuery(criteriaQuery);
		List<ComputerEntity> companyEntity = query.getResultList();

		try {
			return this.computerMapper.mapToComputer(companyEntity).get(0);
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new ComputerNotFoundException();
		}
	}
	
	@Timed
	public void create(Computer computer) {
		ComputerEntityAdd computerAdd = this.computerMapper.mapToComputerEntity(computer);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(computerAdd);
		this.entityManager.getTransaction().commit();
		// transaction commit vs flush
		// this.entityManager.flush();
		//flush allows rollback entity still in transaction 
	}
	
	
	@Timed
	@Transactional
	public void update(Computer computer) {
		
		CriteriaUpdate<ComputerEntityAdd> criteriaUpdate = critriaBuilder.createCriteriaUpdate(ComputerEntityAdd.class);
		Root<ComputerEntityAdd> root = criteriaUpdate.from(ComputerEntityAdd.class);
		
		criteriaUpdate.set("name", computer.getName());
		criteriaUpdate.set("introduced", computer.getIntroduced());
		criteriaUpdate.set("discontinued" , computer.getDiscontinued());
		
		if (computer.getCompany() != null) {
			criteriaUpdate.set("companyId", computer.getCompany().getId());
		}else {
			criteriaUpdate.set("companyId", null);
		}
		
		criteriaUpdate.where(critriaBuilder.equal(root.get("id"), computer.getId()));
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery(criteriaUpdate).executeUpdate();
		
		this.entityManager.getTransaction().commit();
	
		
		this.entityManager.clear();

	}
	
	@Timed
	public void delete(int id) {
		CriteriaDelete<ComputerEntity> criteriaDelete = critriaBuilder.createCriteriaDelete(ComputerEntity.class);
		Root<ComputerEntity> root = criteriaDelete.from(ComputerEntity.class);
		criteriaDelete.where(critriaBuilder.equal(root.get("id"), id));
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery(criteriaDelete).executeUpdate();
		this.entityManager.getTransaction().commit();
	}

	@Timed
	public void delete(String idSelected) {
		
		List<String> listId = Arrays.asList(idSelected.split(","));
		
		CriteriaDelete<ComputerEntity> criteriaDelete = critriaBuilder.createCriteriaDelete(ComputerEntity.class);
		Root<ComputerEntity> root = criteriaDelete.from(ComputerEntity.class);
		
		criteriaDelete.where(root.get("id").in(listId));
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery(criteriaDelete).executeUpdate();
		this.entityManager.getTransaction().commit();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
