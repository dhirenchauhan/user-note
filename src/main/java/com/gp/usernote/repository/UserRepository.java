package com.gp.usernote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gp.usernote.model.User;

/**
 * Interface : UserRepository
 * Purpose : This interface will serve all DB request related to User Entity
 * It extends JpaRepository to support crud operations
 * @author dhiren
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findUserByEmail(@Param("email") String email);

}
