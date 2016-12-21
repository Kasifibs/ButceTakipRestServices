package com.dispinar.butcetakip.server.config;

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

import com.dispinar.butcetakip.server.security.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM="BT_REST_REALM";
	
	 @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("appuser").password("takip.24.butce").roles("USER");
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
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
