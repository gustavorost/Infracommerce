package br.com.infracommerce.services.interfaces;

import java.util.List;

import br.com.infracommerce.model.User;

public interface UserService {
	User findById(Long id);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);

	User findByLogin(String login);
}
