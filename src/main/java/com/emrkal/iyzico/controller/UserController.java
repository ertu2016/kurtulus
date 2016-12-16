package com.emrkal.iyzico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emrkal.iyzico.model.User;
import com.emrkal.iyzico.service.SecurityService;
import com.emrkal.iyzico.service.UserService;

/**
 * User {@link com.emrkal.iyzico.model.User} modeline ait controller sınıfıdır..
 * <p>
 * 
 * @see UserController
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	/**
	 * Yeni bir kullanıcı oluşturulması için "registration" sayfasına ait User
	 * {@link com.emrkal.iyzico.model.User} modelinin oluşturulması sağlanır.
	 * <p>
	 * 
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		return new ModelAndView("registration", "user", new User());
	}

	/**
	 * "Registration" sayfasında form doldurulup submit edildikten sonra
	 * karşılanan metoddur.
	 * <p>
	 * User {@link com.emrkal.iyzico.model.User} modeli üzerinde zorunlu
	 * fieldlar kontrol edilir. Validasyona takılmaması durumunda yeni kullanıcı
	 * için save işlemi yapılır ve ardından otomatik olarak login yapılması
	 * sağlanır.
	 * <p>
	 * Yeni oluşturulan kullanıcının yeniden login sayfasına yönlendirilmesi
	 * sağlanmaz
	 * <p>
	 * 
	 * @param User
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.addNewUser(user);
		securityService.autologin(user.getUsername(), user.getConfirmPassword());
		return "redirect:/home";
	}

}
