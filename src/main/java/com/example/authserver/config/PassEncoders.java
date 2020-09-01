package com.example.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PassEncoders {
    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4) {
        	@Override
        	public boolean matches(CharSequence rawPassword, String encodedPassword) {
        		return rawPassword.equals(encodedPassword);
        	}
        };
    }
    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
    
    public static void main(String args[]) {
    	System.out.println((new BCryptPasswordEncoder(4)).encode("mytestpassword"));
    }
}
