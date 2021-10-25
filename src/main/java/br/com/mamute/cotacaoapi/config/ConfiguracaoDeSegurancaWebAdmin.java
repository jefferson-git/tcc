
package br.com.mamute.cotacaoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class ConfiguracaoDeSegurancaWebAdmin extends WebSecurityConfigurerAdapter{
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()	
				.antMatchers("/css/**","/img/**","/js/**","/vendor/**",
							 "/mamute/**","/app-assets/**",
							 "/dashboard-admin/user-register",
							 "/dashboard-admin/user-forgot-password",
							 "/dashboard-admin/user-lock-screen").permitAll()
				.antMatchers("/dashboard-admin/**").hasAnyRole("ADMINISTRATIVO","GERENTE")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/dashboard-admin/user-login")
				.permitAll()
			.and()
				.logout().invalidateHttpSession(true).clearAuthentication(true)				
				.logoutSuccessUrl("/dashboard-admin/user-login?logout").permitAll()
			.and()
				.rememberMe();				
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
			.withUser("jefin").password(bCryptPasswordEncoder().encode("123")).roles("GERENTE","ADMINISTRATIVO","USUARIO")
			.and()
			.withUser("maria").password(bCryptPasswordEncoder().encode("123")).roles("ADMINISTRATIVO")
			.and()
			.withUser("joao").password(bCryptPasswordEncoder().encode("123")).roles("USUARIO");;
	}
}


