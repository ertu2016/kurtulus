package com.emrkal.iyzico.service;

/**
 * User {@link com.emrkal.iyzico.model.User}login işlenlerinin yapıldığı
 * interface sınıfıdır.
 * <p>
 * 
 * @see SecurityService
 * @version 1.0
 * @author Emrullah KALKAN
 */
public interface SecurityService {

	/**
	 * Login olunan kullanıcının "username" bilgisini döndürür.
	 * <p>
	 * 
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	String findLoggedInUsername();

	/**
	 * Yeni bir kullanıcı oluşturulduktan sonra otomatik olarak login olmasını
	 * sağlayan metoddur.
	 * <p>
	 * 
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	void autologin(String username, String password);
}