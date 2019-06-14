package com.buseni.adminpwa.webspush;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.martijndwars.webpush.PushService;
@Configuration
public class WebPushConfig {	
	
	 @Bean
	 public PushService pushService() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		 PushService pushService = new PushService();
		 pushService.setPublicKey("BCX0pPjyzSmBbLVGYv9sSTxQ1Y7747Q9b5g9Vn9Xh_vFk5ZJaLwdhiLU1gU0U1mBUhLL7lZTNQjtPQ41KJzOobk=");
		 pushService.setPrivateKey("SdEJQ3o_TIZvf6sljFabLM7mk597FTQiH_y_AJmtWuE=");
		 return pushService;
	 }

}
