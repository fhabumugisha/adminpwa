package com.buseni.adminpwa;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AdminpwaApplication  {

	
	
	public static void main(String[] args) {
		 // Add BouncyCastle as an algorithm provider
		  if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
		      Security.addProvider(new BouncyCastleProvider());
		  }
		SpringApplication.run(AdminpwaApplication.class, args);
	}

	

}

