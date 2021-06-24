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

import com.excilys.computerDatabase.core.exception.dao.CompanyNotFoundException;
import com.excilys.computerDatabase.core.exception.dao.DAOException;
import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.persistence.binding.dto.CompanyEntity;
import com.excilys.computerDatabase.persistence.binding.dto.ComputerEntity;
import com.excilys.computerDatabase.persistence.binding.mapper.CompanyMapper;
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
		try {

			CriteriaQuery<Long> criteriaQuery = critriaBuilder.createQuery(Long.class);
			Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);
			criteriaQuery.select(critriaBuilder.count(root));

			return entityManager.createQuery(criteriaQuery).getSingleResult();

		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}
	}

	@Timed
	public List<Company> findAll() {
		try {

			CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
			Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);
			criteriaQuery.select(root).orderBy(critriaBuilder.asc(root.get("name")));

			TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
			List<CompanyEntity> companyEntity = query.getResultList();
			return this.companyMapper.maptoCompany(companyEntity);
		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}
	}

	@Timed
	public Company find(int id) {
		try {

			CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
			Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

			Predicate withId = critriaBuilder.equal(root.get("id"), id);

			criteriaQuery.select(root).where(withId);

			TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
			List<CompanyEntity> companyEntity = query.getResultList();

			return this.companyMapper.maptoCompany(companyEntity).get(0);
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}
	}

	@Timed
	public Company find(String name) {
		try {
			CriteriaQuery<CompanyEntity> criteriaQuery = critriaBuilder.createQuery(CompanyEntity.class);
			Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

			Predicate withId = critriaBuilder.equal(root.get("name"), name);

			criteriaQuery.select(root).where(withId);

			TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
			List<CompanyEntity> companyEntity = query.getResultList();

			return this.companyMapper.maptoCompany(companyEntity).get(0);
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}
	}

	@Timed
	public void create(String name) {
		try {

			CompanyEntity companyAdd = new CompanyEntity();
			companyAdd.setName(name);
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(companyAdd);
			this.entityManager.getTransaction().commit();

		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}
	}

	@Timed
	@Transactional
	public void delete(int id) {
		try {
			CriteriaDelete<ComputerEntity> criteriaDeleteComputer = critriaBuilder
					.createCriteriaDelete(ComputerEntity.class);
			Root<ComputerEntity> rootComputer = criteriaDeleteComputer.from(ComputerEntity.class);
			criteriaDeleteComputer.where(critriaBuilder.equal(rootComputer.get("company_id"), id));

			this.entityManager.getTransaction().begin();
			this.entityManager.createQuery(criteriaDeleteComputer).executeUpdate();
			this.entityManager.getTransaction().commit();

			CriteriaDelete<CompanyEntity> criteriaDeleteCompany = critriaBuilder
					.createCriteriaDelete(CompanyEntity.class);
			Root<CompanyEntity> rootCompany = criteriaDeleteCompany.from(CompanyEntity.class);
			criteriaDeleteCompany.where(critriaBuilder.equal(rootCompany.get("id"), id));

			this.entityManager.getTransaction().begin();
			this.entityManager.createQuery(criteriaDeleteCompany).executeUpdate();
			this.entityManager.getTransaction().commit();

		} catch (Exception e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException();
		}

	}

}
