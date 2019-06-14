package com.buseni.adminpwa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "springbootapi-security-jwt")
public class SecurityConfiguration {
	private String clientId;
	private String clientSecret;
	private String grantType;
	private String scopeRead;
	private String scopeWrite;
	private String resourceIds;
	private int accessTokenValiditySeconds;
	private int refreshTokenValiditySeconds;
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String mobileAppClientId) {
		this.clientId = mobileAppClientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String mobileAppClientSecret) {
		this.clientSecret = mobileAppClientSecret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getScopeRead() {
		return scopeRead;
	}

	public void setScopeRead(String scopeRead) {
		this.scopeRead = scopeRead;
	}

	public String getScopeWrite() {
		return scopeWrite;
	}

	public void setScopeWrite(String scopeWrite) {
		this.scopeWrite = scopeWrite;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public int getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}
}