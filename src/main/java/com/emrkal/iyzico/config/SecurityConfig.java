package com.emrkal.iyzico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Security konfigürasyonun yapıldığı sınıftır.
 * <p>
 * 
 * @see HttpSecurity
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Static reosurces ignore ediliyor :
	 * <ul>
	 * <li>css
	 * <li>js
	 * <li>font
	 * <li>img
	 * <li>resources
	 * <li>templates
	 * <li>registration
	 * <li>login
	 * </ul>
	 * <p>
	 * 
	 * @param httpSecurity
	 * @see HttpSecurity
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests().antMatchers(
				"/css/**", 
				"/js/**", 
				"/font/**", 
				"/img/**", 
				"/resources/**", 
				"/templates/**", 
				"/registration", 
				"/login")
		.permitAll();

		httpSecurity
			.formLogin()
			.failureUrl("/login?error")
			.defaultSuccessUrl("/home")
			.loginPage("/login").
			permitAll()
		.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").permitAll();

		httpSecurity.authorizeRequests().anyRequest().authenticated();
		
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
	     filter.setEncoding("UTF-8");
	     filter.setForceEncoding(true);
	     httpSecurity.addFilterBefore(filter,CsrfFilter.class);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
