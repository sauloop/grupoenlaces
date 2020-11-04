package com.pablogiraldo.grupoenlaces.repository;

import java.util.Optional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablogiraldo.grupoenlaces.entity.Role;
import com.pablogiraldo.grupoenlaces.enu.RoleName;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(RoleName name);

	boolean existsByName(RoleName name);
}
