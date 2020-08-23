package com.example.authserver.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder oauthClientPasswordEncoder;

	@Value("${config.oauth2.privatekey}")
	private String privateKey;

	@Value("${config.oauth2.publickey}")
	private String publicKey;


	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
		return new OAuth2AccessDeniedHandler();
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.passwordEncoder(oauthClientPasswordEncoder);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).passwordEncoder(oauthClientPasswordEncoder);
		
		/*clients.inMemory().withClient("spring-security-oauth2-read-write-client").secret(oauthClientPasswordEncoder.encode("spring-security-oauth2-read-write-client-password-1234"))
		.scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(18000);*/
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore()).reuseRefreshTokens(false).accessTokenConverter(tokenEnhancer()).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new MyTokenEnhancer();
		jwtAccessTokenConverter.setSigningKey("-----BEGIN RSA PRIVATE KEY-----\r\n" + 
				"MIIEpAIBAAKCAQEAtYvuyOe5WH0v2KlgnxzeAQ8CAaQF71Bgzil7CHgzPWA5dzmV\r\n" + 
				"L6hP3tWePaQg0rZ6WHR90EnFsB4R8OqsD7JU3IN83hIWVnjQz/fo1ygQzWP5h8Hj\r\n" + 
				"ssTWVDMi8VdbZeeqdRT2/YTs3l3Hm1adWqhKCV0OnWLD93DvjAvSfu0VBCPzIdyJ\r\n" + 
				"D66T0YePT/Ud9+/dkMm2GqW/hh/8Y1AuQxhjLM2SApVhbJo5GEbe1apzJnFn1FMF\r\n" + 
				"SarWWxAZ6bG/CXtXdn9rM0T299aW6NXwjwiSfedCutkqPJAc9isc6kb7JNl6lHm/\r\n" + 
				"rYbpE0Ao/2jYrsyvz2J1R12TEtyvrloqg6DoKwIDAQABAoIBAQCUmfHeqS5hbDcZ\r\n" + 
				"1aqtEuf1Xd96kFP7S4OxvOpkSF9IwdbH7G5dEBwwfhKEyi/DynHirZe+QmR6gGKX\r\n" + 
				"HL+yKXqk9UvkSlHWfhYN5RLF6pm2RTsfEGndDpmRWjoj6il6BGLWa06wvXlVmtWk\r\n" + 
				"JQWwC0hu2aj40rstc1fg/lxSYKVvOMwBWeQjZqtbaxCmT4/jPxeuTKbByibFv4QV\r\n" + 
				"UHyP62LktyW2LOjGPL0UPPe6SfuE/7N2PaakxcmVeXhsNTR3zQqWS/wxPu3RwMmh\r\n" + 
				"P500JwYUBJ7mow6feYT17rd91YO7BRUGuYRpAvvFudsvrKOfY5v+YjFLLpVmzWOQ\r\n" + 
				"7wAFAonBAoGBAOhvqK4nqHDF8OZFjAy+lUyXYeW8en4xnbVz7YmO3/CJITPijwQQ\r\n" + 
				"xxNOiQVl4/9hk4U+KeZi6V8y8c0QCIOwSqC2icuOi67P20ZycR3YP1F39Bmyyk7I\r\n" + 
				"5FWTVI2pxCJSld4HXur/9VUv+qtGiHOtPj9Lauq1JPuvmH0/jlDFa+uzAoGBAMfz\r\n" + 
				"jbfVg3X55pW3WqAqrenEy5EnNTxn2qFkm2KsgkUXzMTV7b7A/P5YjeYAJ5/tIPYC\r\n" + 
				"e/si8NBETEatMgnAOXbX+uFXXGtVZo7kanFOEFlimBLalENNTUShX+WRpyCXu+PP\r\n" + 
				"hY94EM0EZt+Bv7fGZ2MCnFsIkn8yFI8i1GXV8PWpAoGAdSGfvXOavInjZgdyFhIM\r\n" + 
				"prY6zUz35ZQXR5OOBp4LV+mi48SuEXxq9d3DywaXqOCBOMokz0bVF/ZSR+ZmhAZY\r\n" + 
				"9ThHpF7HIWb5tzyAqGuLLQDn7/NbY+x0tHM0TtdYUXuQrnzEZmRqO22cQqeep7yL\r\n" + 
				"+9WDoZd7daH9lFBs5WM3iWUCgYB/Ga99Kuj8xlSv2LRv2ZFhZuoro6uwcSfFrckk\r\n" + 
				"KRpJxz4v5PjSZ6SqHSpeCL0naLmqB5w94FEJYlDGIHQNRLaCd/oO4dRqmgeuTz3m\r\n" + 
				"NkQoPYcDfRP/0BSMyrPFnxeInUsfHZv9vcmYOPMXokd3D1RK2K8BIurUlcYB0pcH\r\n" + 
				"AL7BuQKBgQCKFxG+fWj0zSFwFd7DygJH2C2vTSYU7qoEzXH6B41BSqi3XRTRcqOj\r\n" + 
				"qyCoGrez02Sta3ZrC1BAIipytgl8zXrk4pTaahICyJKW8HhaO97iO9PH4pZhp+a0\r\n" + 
				"bSjdoK/kSx8YtrAFUpK9JNAhK69l8sV0PRhSYwQoQD4bBHXRiA2OWA==\r\n" + 
				"-----END RSA PRIVATE KEY-----");
		jwtAccessTokenConverter.setVerifierKey("-----BEGIN PUBLIC KEY-----\r\n" + 
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtYvuyOe5WH0v2Klgnxze\r\n" + 
				"AQ8CAaQF71Bgzil7CHgzPWA5dzmVL6hP3tWePaQg0rZ6WHR90EnFsB4R8OqsD7JU\r\n" + 
				"3IN83hIWVnjQz/fo1ygQzWP5h8HjssTWVDMi8VdbZeeqdRT2/YTs3l3Hm1adWqhK\r\n" + 
				"CV0OnWLD93DvjAvSfu0VBCPzIdyJD66T0YePT/Ud9+/dkMm2GqW/hh/8Y1AuQxhj\r\n" + 
				"LM2SApVhbJo5GEbe1apzJnFn1FMFSarWWxAZ6bG/CXtXdn9rM0T299aW6NXwjwiS\r\n" + 
				"fedCutkqPJAc9isc6kb7JNl6lHm/rYbpE0Ao/2jYrsyvz2J1R12TEtyvrloqg6Do\r\n" + 
				"KwIDAQAB\r\n" + 
				"-----END PUBLIC KEY-----");

		return jwtAccessTokenConverter;

	}
}
