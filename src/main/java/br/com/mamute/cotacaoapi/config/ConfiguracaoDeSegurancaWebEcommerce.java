
package br.com.mamute.cotacaoapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class ConfiguracaoDeSegurancaWebEcommerce extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()	
				.antMatchers("/css/**","/img/**","/js/**","/vendor/**","/mamute/**").permitAll()
				.antMatchers("/usuario/**").hasAnyRole("USUARIO")
				.anyRequest().authenticated() 
			.and()
				.formLogin().loginPage("/usuario/login").permitAll()
			.and()
				.logout().invalidateHttpSession(true).clearAuthentication(true)				
				.logoutSuccessUrl("/usuario/login?logout").permitAll()
			.and()
				.rememberMe();				
	}	
}

