package notes.service.test.securityTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableWebSecurity
public class WebAdapter extends WebSecurityConfigurerAdapter{

	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http
		 .authorizeRequests()
		 .anyRequest().authenticated()
		 .and()
		 .formLogin().and()
		 .httpBasic();
		 SecurityContext sc = SecurityContextHolder.getContext();
	 }
}
