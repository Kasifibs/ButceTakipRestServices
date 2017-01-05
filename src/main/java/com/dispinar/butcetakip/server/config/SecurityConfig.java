package com.dispinar.butcetakip.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dispinar.butcetakip.server.security.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM="BT_REST_REALM";
	
	@Autowired DataSource dataSource;
	
	 @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource) 
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select user_name, password, enabled from users where user_name = ?")
		.authoritiesByUsernameQuery("select u.user_name, ur.authority from users u, user_roles ur where u.user_id = ur.user_id and u.user_name = ?");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and().requiresChannel().anyRequest().requiresSecure();
    }
	
	 @Bean
	 public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	     return new CustomBasicAuthenticationEntryPoint();
	 }
	 
 	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
