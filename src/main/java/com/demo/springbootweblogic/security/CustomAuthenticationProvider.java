package com.demo.springbootweblogic.security;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        System.out.println("in authenticate");
        
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        System.out.println("username=>"+username+", password=>"+password);
        if ("user".equals(username) && "mypassword".equals(password)) {           
            // replace your custom code here for custom authentication
            return new UsernamePasswordAuthenticationToken
                    (username, password, Collections.emptyList());
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println(" check supports");
        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }
}