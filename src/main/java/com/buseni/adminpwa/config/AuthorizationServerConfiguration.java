package com.buseni.adminpwa.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;



@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	 @Qualifier("authenticationManagerBean")
	private AuthenticationManager  authenticationManager;
	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private SecurityConfiguration securityConfiguration; 
	
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);
    }
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient(securityConfiguration.getClientId())
				.authorities("ROLE_API_CLIENT")
				.authorizedGrantTypes(securityConfiguration.getGrantType(),"authorization_code","client_credentials", "refresh_token")
				.scopes(securityConfiguration.getScopeRead(), securityConfiguration.getScopeWrite())
				.resourceIds(securityConfiguration.getResourceIds())
				.secret(passwordEncoder.encode(securityConfiguration.getClientSecret()))
				.redirectUris("http://localhost:8080/users/callback")
				.accessTokenValiditySeconds(securityConfiguration.getAccessTokenValiditySeconds())
				.refreshTokenValiditySeconds(securityConfiguration.getRefreshTokenValiditySeconds());
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				 .authenticationManager(authenticationManager)
				 .userDetailsService(userDetailsService);
	}
	
}
