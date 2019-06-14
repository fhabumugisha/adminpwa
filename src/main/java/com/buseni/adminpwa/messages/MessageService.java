package com.buseni.adminpwa.messages;

import java.util.List;

public interface MessageService {
	
	List<Message>  findAll();
	
	Message createMessage(Message message);

	Message findById(Integer idMessage);
	
	

}
