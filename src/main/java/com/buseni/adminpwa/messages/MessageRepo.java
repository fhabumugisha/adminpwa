package com.buseni.adminpwa.messages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer>{

	Integer countByNotificationType(NotificationType push);

}
