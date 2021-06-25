package com.excilys.computerDatabase.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.persistence.binding.dto.CompanyEntity;
import com.excilys.computerDatabase.persistence.binding.dto.ComputerEntity;
import com.excilys.computerDatabase.persistence.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.persistence.exceptiondao.CompanyNotFoundException;
import com.excilys.computerDatabase.persistence.loggertime.Timed;


@Repository
public class CompanyDAO {

	private CompanyMapper companyMapper;
	private CriteriaBuilder critriaBuilder;
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	
	public CompanyDAO(CompanyMapper companyMapper, EntityManagerFactory entityManagerFactory) {
		super();
		this.companyMapper = companyMapper;
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.critriaBuilder = this.entityManager.getCriteriaBuilder();

	}

	@Timed
	public long countAll() {
		CriteriaQuery<Long> criteriaQuery = critriaBuilder.createQuery(Long.class);
		Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);
		criteriaQuery.select(critriaBuilder.count(root));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

	@Timed
	public List<Company> findAll() {

		CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
		Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);
		criteriaQuery.select(root).orderBy( critriaBuilder.asc(root.get("name")));

		TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
		List<CompanyEntity> companyEntity = query.getResultList();
		return this.companyMapper.maptoCompany(companyEntity);
	}

	@Timed
	public Company find(int id) {
		CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
		Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

		Predicate withId = critriaBuilder.equal(root.get("id"), id);

		criteriaQuery.select(root).where(withId);

		TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
		List<CompanyEntity> companyEntity = query.getResultList();

		try {
			return this.companyMapper.maptoCompany(companyEntity).get(0);
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		}
	}

	@Timed
	public Company find(String name) {
		CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
		Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

		Predicate withId = critriaBuilder.equal(root.get("name"), name);

		criteriaQuery.select(root).where(withId);

		TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
		List<CompanyEntity> companyEntity = query.getResultList();

		try {
			return this.companyMapper.maptoCompany(companyEntity).get(0);
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		}
	}

	@Timed
	public void addCompany(String name) {
		CompanyEntity companyAdd= new CompanyEntity();
		companyAdd.setName(name);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(companyAdd);
		this.entityManager.getTransaction().commit();
	}

	
	@Timed
	@Transactional
	public void delete(int id) {
	
		CriteriaDelete<ComputerEntity> criteriaDeleteComputer = critriaBuilder.createCriteriaDelete(ComputerEntity.class);
		Root<ComputerEntity> rootComputer = criteriaDeleteComputer.from(ComputerEntity.class);
		criteriaDeleteComputer.where(critriaBuilder.equal(rootComputer.get("copany_id"), id));
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery(criteriaDeleteComputer).executeUpdate();
		this.entityManager.getTransaction().commit();
		
		
		CriteriaDelete<CompanyEntity> criteriaDeleteCompany = critriaBuilder.createCriteriaDelete(CompanyEntity.class);
		Root<CompanyEntity> rootCompany = criteriaDeleteCompany.from(CompanyEntity.class);
		criteriaDeleteCompany.where(critriaBuilder.equal(rootCompany.get("id"), id));
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery(criteriaDeleteCompany).executeUpdate();
		this.entityManager.getTransaction().commit();
		
		
		
	}

}
