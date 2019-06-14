package com.buseni.adminpwa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)


public class SpringSecurityConfig   extends WebSecurityConfigurerAdapter{


	@Autowired
	private UserDetailsService userDetailsService;


	@Autowired	
	public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.cors()		
		.and()
			.formLogin()
		.and()
			.logout().permitAll()
		.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
			.antMatchers("/users/callback").permitAll()
			.anyRequest().authenticated()
		.and()
			.httpBasic()
			;
		http.headers().frameOptions().disable();
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/resources/**")
		.antMatchers("/static/**")
		.antMatchers("/css/**")
		.antMatchers("/images/**")
		.antMatchers("/v2/api-docs/**")
		.antMatchers("/swagger.json")
		.antMatchers("/swagger-ui.html")
		.antMatchers("/webjars/**")
		.antMatchers("favicon.ico")
		.antMatchers("/configuration/ui")
		.antMatchers("/configuration/security")
		.antMatchers("/swagger-resources")
		.antMatchers( HttpMethod.OPTIONS, "/api/**" );

	}



//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		final CorsConfiguration configuration = new CorsConfiguration();
//		//configuration.setAllowedOrigins(ImmutableList.of("http://localhost:8080","http://localhost:8084"));
//		configuration.setAllowedOrigins(ImmutableList.of("*"));
//		configuration.setAllowedMethods(ImmutableList.of("*"));
//		configuration.setAllowCredentials(true);
//		configuration.setAllowedHeaders(ImmutableList.of("*"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("123");
		return converter;
	}


	@Bean
	public TokenEnhancerChain tokenEnhancerChain() {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Lists.newArrayList(new CustomTokenEnhancer(), accessTokenConverter()));
		return tokenEnhancerChain;
	}
	//
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		// defaultTokenServices.setClientDetailsService(clientDetailsService);
		defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public TokenStore tokenStore() {
		//return new JwtTokenStore(accessTokenConverter());
		return new InMemoryTokenStore();

	}
}