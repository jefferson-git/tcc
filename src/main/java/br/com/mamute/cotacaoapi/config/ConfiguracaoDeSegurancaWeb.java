
package br.com.mamute.cotacaoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.mamute.cotacaoapi.model.implementaUsuario;

@Configuration @EnableWebSecurity
public class ConfiguracaoDeSegurancaWeb extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new implementaUsuario();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider autenticacao() {
		DaoAuthenticationProvider autentica = new DaoAuthenticationProvider();
		autentica.setPasswordEncoder(passwordEncoder());
		autentica.setUserDetailsService(userDetailsService());
		
		return autentica;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(autenticacao());
	}	
	
	@Configuration @EnableWebSecurity @Order(2)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/app-assets/**","/dashboard-admin/user-register",
							 "/dashboard-admin/user-forgot-password",
							 "/dashboard-admin/user-lock-screen").permitAll()
				.antMatchers("/","/dashboard-admin/**")
				.hasAnyAuthority("ADMINISTRADOR")
				.and()
					.formLogin()
					.loginPage("/dashboard-admin/user-login").permitAll()
					.defaultSuccessUrl("/dashboard-admin",true)
				.and()
					.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)				
					.logoutSuccessUrl("/dashboard-admin/user-login?logout").permitAll();
        }
    }
	
    @Configuration
    @Order(1)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http.requestMatcher(new AntPathRequestMatcher("/usuario/**"))
            	.authorizeRequests()	
    				.antMatchers("/css/**","/img/**","/js/**","/vendor/**","/mamute/**").permitAll()
    				.antMatchers("/usuario/**").hasAnyAuthority("USUARIO")
    				.anyRequest().authenticated() 
    			.and()
    				.formLogin()
    				.loginPage("/usuario/login").permitAll()
    				.defaultSuccessUrl("/usuario/perfil", true)
    			.and()
    				.logout()
    				.invalidateHttpSession(true)
    				.clearAuthentication(true)				
    				.logoutSuccessUrl("/usuario/login?logout").permitAll()
    			.and()
    				.rememberMe();				
    	}
    }
}

