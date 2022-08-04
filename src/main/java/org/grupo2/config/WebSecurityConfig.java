package org.grupo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserDetailsService UsuarioService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //Configura as permições de acesso a URL
		http
		.authorizeRequests()
			.antMatchers("/", "/page/*", "/cadastrarUsuario", "/formularioDeNovoUsuario").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.invalidateHttpSession(true)
    		.clearAuthentication(true)
    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    		.logoutSuccessUrl("/?logout")
			.permitAll();
		
		return http.build();
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() { //Lida com as credênciais
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(UsuarioService);	
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}
	
	
	@Bean
	static public PasswordEncoder passwordEncoder() { //Configura um Codificador de Senha
	    return new BCryptPasswordEncoder();
	}
}
