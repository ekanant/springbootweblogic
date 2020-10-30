package com.demo.springbootweblogic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override      // here is configuration related to spring boot basic authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
        
        auth.inMemoryAuthentication()
            .withUser("user").password("mypassword").roles("USER")
            .and()
            .withUser("admin").password("mypassword").roles("ADMIN");// those are user name and password
    }
    

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                    .antMatchers("/hello/testspringsecurity").authenticated()
                    .anyRequest().permitAll()
            .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }
    
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}