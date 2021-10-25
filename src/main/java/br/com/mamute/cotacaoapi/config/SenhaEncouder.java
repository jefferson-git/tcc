
package br.com.mamute.cotacaoapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SenhaEncouder {

	public static BCryptPasswordEncoder passwordEncoder(String password) {
		return passwordEncoder(password);
	}
	
}