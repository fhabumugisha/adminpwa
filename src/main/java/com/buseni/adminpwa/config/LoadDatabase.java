package com.buseni.adminpwa.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.buseni.adminpwa.books.Book;
import com.buseni.adminpwa.messages.Message;
import com.buseni.adminpwa.messages.MessageRepo;
import com.buseni.adminpwa.messages.NotificationType;
import com.buseni.adminpwa.user.Role;
import com.buseni.adminpwa.user.RoleRepo;
import com.buseni.adminpwa.user.User;
import com.buseni.adminpwa.user.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(MessageRepo messageRepo, 
			UserRepo userRepo, 
			RoleRepo roleRepo,
			PasswordEncoder passwordEncoder) {
		return args -> {
			log.info("Preloading Message" + messageRepo.save(new Message("Title 1", "Description 1", NotificationType.PUSH, new Date())));
			log.info("Preloading Message " + messageRepo.save(new Message("Title 2", "Description 2",NotificationType.EMAIL, new Date())));
			log.info("Preloading Message " + messageRepo.save(new Message("Title 3", "Description 4", NotificationType.SMS, new Date())));
			log.info("Preloading Message " + messageRepo.save(new Message("Title 4", "Description 5", NotificationType.PUSH, new Date())));
			log.info("Preloading Message " + messageRepo.save(new Message("Title 6", "Description 7", NotificationType.NONE, new Date())));
			
			Role  admin =  Role.builder().name("ADMIN").description("Administration").build();			
			Role  api =  Role.builder().name("API_CLIENT").description("Api client").build();
			
			log.info("Preloading Role " + roleRepo.save(admin));
			log.info("Preloading Role " + roleRepo.save(api));
			User userAdmin = User.builder().username("admin@edd").password(passwordEncoder.encode("admin")).roles(Arrays.asList(admin, api)).build();
			User userApi = User.builder().username("api@edd").password(passwordEncoder.encode("apiclient")).roles(Arrays.asList(api)).build();

			log.info("Preloading user admin " + userRepo.save(userAdmin));
			log.info("Preloading user api " + userRepo.save(userApi));
			
			
		};
	}
}