package com.buseni.adminpwa.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.buseni.adminpwa.user.User;

public class CustomTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<>();
		User user = (User) authentication.getPrincipal();
		additionalInfo.put("organization", authentication.getName() + RandomStringUtils.randomAlphabetic(4));
		additionalInfo.put("id", user.getId());
        additionalInfo.put("authorities", user.getRoles());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}