package br.com.mamute.cotacaoapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracaoSecurityJdbc {

	public static final String USUARIO_POR_USERNAME = "SELECT username, password, status FROM usuario"
			+ " WHERE username = ?";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT usuario, papel FROM permissao"
			+ " JOIN usuario ON usuario.id = permissao.usuario_id"
			+ " JOIN papel ON papel.id = permissao.papel_id"
			+ " WHERE usuario.username = ?";
	
	public static final String PERMISSOES_POR_GRUPO = "SELECT g.id, g.nome, p.nome  as nome_permissao FROM grupo_permissao gp"
			+ " JOIN grupo g ON g.id = gp.grupo_id"
			+ " JOIN permissao p ON p.id = gp.permissao_id"
			+ " JOIN usuario_grupo ug ON ug.grupo_id = g.id"
			+ " JOIN usuario u ON u.id = ug.usuario_id"
			+ " WHERE u.login = ?";
		
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder, 
		PasswordEncoder passwordEncoder, DataSource dataSource) throws Exception {
		builder
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery(USUARIO_POR_USERNAME)
			.authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO);
			//.groupAuthoritiesByUsername(PERMISSOES_POR_GRUPO);
	}
}
