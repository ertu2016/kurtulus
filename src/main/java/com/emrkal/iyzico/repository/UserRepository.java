package com.emrkal.iyzico.repository;

import org.springframework.data.repository.CrudRepository;

import com.emrkal.iyzico.model.User;

/**
 * User {@link com.emrkal.iyzico.model.User} modeline ait "Persistence"
 * işlemlerinin yapıldığı Repository sınıftır.
 * <p>
 * 
 * @see UserRepository
 * @version 1.0
 * @author Emrullah KALKAN
 */

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * "username" alanına göre User User {@link com.emrkal.iyzico.model.User}
	 * modelinin döndürülmesi sağlanır.
	 * <p>
	 * 
	 * @param username
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.User}
	 * @author Emrullah KALKAN
	 */
	User findByUsername(String username);

}
