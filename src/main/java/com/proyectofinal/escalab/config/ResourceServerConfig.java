package com.proyectofinal.escalab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.proyectofinal.escalab.exception.AuthException;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private ResourceServerTokenServices tokenServices;
	
	@Value("${security.jwt.resource-ids}")
	private String resourceIds;
	

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.exceptionHandling().authenticationEntryPoint(new AuthException())
		.and()
		.requestMatchers()
		.and()
		.authorizeRequests()
				.antMatchers("/swagger.ui.html/**").authenticated()
				.antMatchers("/posts/**").permitAll()
				.antMatchers("/comments/**").authenticated()
				.antMatchers("/groups/**").authenticated()
				.antMatchers("/messages/**").authenticated()
				.antMatchers("/recipients/**").authenticated()
				.antMatchers("/tags/**").authenticated()
				.antMatchers("/tokens/**").permitAll()
				.antMatchers("/usuarios/").permitAll()
				.antMatchers("/users/getAll/**").hasRole("USER");
	}

}
