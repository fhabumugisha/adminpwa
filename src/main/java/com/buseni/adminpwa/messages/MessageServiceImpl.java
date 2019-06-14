package com.buseni.adminpwa.messages;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class MessageServiceImpl implements MessageService {
	
	private MessageRepo messageRepo;
	
	public MessageServiceImpl(MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}

	@Override
	public List<Message> findAll() {
		return messageRepo.findAll();
	}

	@Override
	@Transactional
	public Message createMessage(Message message) {
		message.setDateCreation(new Date());
		return messageRepo.save(message);
	}

	@Override
	public Message findById(Integer idMessage) {
		// TODO Auto-generated method stub
		return messageRepo.findById(idMessage).orElseThrow(	()  -> new MessageNotFoundException("Message non trouvé"));
	}

}
