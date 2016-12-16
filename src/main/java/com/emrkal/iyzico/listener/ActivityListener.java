package com.emrkal.iyzico.listener;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.emrkal.iyzico.model.Activity;
import com.emrkal.iyzico.repository.UserRepository;

/**
 * Persistence işlemlerinden önce user {@link com.emrkal.iyzico.model.User}
 * bilgisinin Activity {@link com.emrkal.iyzico.model.Activity} modelindeki
 * "user" alanına set edilmesi sağlanır.
 * <p>
 * 
 * @see ActivityListener
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Component
public class ActivityListener {

	@Autowired
	private static UserRepository userRepository;

	@Autowired
	public void init(UserRepository userRepository) {
		ActivityListener.userRepository = userRepository;
	}

	@PrePersist
	private void onInsert(Activity activity) {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			com.emrkal.iyzico.model.User userEnt = userRepository.findByUsername(user.getUsername());
			activity.setUser(userEnt);
		}
	}

}
