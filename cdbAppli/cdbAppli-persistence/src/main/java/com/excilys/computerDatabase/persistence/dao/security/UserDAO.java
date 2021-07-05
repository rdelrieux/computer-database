package com.excilys.computerDatabase.persistence.dao.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.persistence.binding.dto.security.UserEntity;

@Repository
public class UserDAO {
	
	private CriteriaBuilder critriaBuilder;
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;

	public UserDAO( EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.critriaBuilder = this.entityManager.getCriteriaBuilder();

	}

	

	public UserDetails find(String username) {
		
		CriteriaQuery<UserEntity> criteriaQuery = critriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);


		Predicate withName = critriaBuilder.equal(root.get("name"), username);

		criteriaQuery.select(root).where(withName);

		
		TypedQuery<UserEntity> query = this.entityManager.createQuery(criteriaQuery);
		
		UserEntity userEntity = query.getResultList().get(0);
		
		List< GrantedAuthority> role = new ArrayList<>();
		role.add( new SimpleGrantedAuthority(userEntity.getRole().getRole().name()) );
			
		
		return new User( userEntity.getName(),userEntity.getPassword(),role );
	}
	
	
	

}
