package volz.vanessa.codingtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Defining here just for demonstration purposes, assuming only service owner should have access for deletion
	private static final String OWNER = "OWNER";
	private static final String OWNER_USER = "owner";
	private static final String OWNER_PASSWORD = "ownerpass";
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.DELETE, "/api/data/**").hasRole(OWNER)
		.anyRequest().permitAll().and().httpBasic();
		http.csrf().disable(); // Disabling as it's a non browser app
	}

	@Autowired
	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(OWNER_USER).password(encoder().encode(OWNER_PASSWORD)).roles(OWNER);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}

