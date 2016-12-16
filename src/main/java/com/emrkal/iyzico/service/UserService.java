package com.emrkal.iyzico.service;

import com.emrkal.iyzico.model.User;

/**
 * User {@link com.emrkal.iyzico.model.User} modeline ait interface sınıfıdır.
 * <p>
 * 
 * @see UserService
 * @version 1.0
 * @author Emrullah KALKAN
 */
public interface UserService {

	/**
	 * Id bilgisine göre user {@link com.emrkal.iyzico.model.User} bilgisinin
	 * döndürülüğü metoddur.
	 * <p>
	 * 
	 * @param id
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.User}
	 * @author Emrullah KALKAN
	 */
	User getUserById(long id);

	/**
	 * Kullanıcı adına göre user {@link com.emrkal.iyzico.model.User} bilgisinin
	 * döndürülüğü metoddur.
	 * <p>
	 * 
	 * @param username
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.User}
	 * @author Emrullah KALKAN
	 */
	User getUserByUsername(String username);

	/**
	 * Yeni bir kullanıcının {@link com.emrkal.iyzico.model.User} kaydedildiği
	 * metoddur.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Comment}
	 * @author Emrullah KALKAN
	 */
	User addNewUser(User user);

}
