package br.com.infracommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infracommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
}
