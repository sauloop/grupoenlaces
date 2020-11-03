package com.pablogiraldo.grupoenlaces.repository;

import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablogiraldo.grupoenlaces.entity.User;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);

	boolean existsByName(String name);
}