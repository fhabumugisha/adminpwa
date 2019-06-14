package com.buseni.adminpwa.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buseni.adminpwa.messages.Message;
import com.buseni.adminpwa.messages.MessageCollection;
import com.buseni.adminpwa.messages.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Messages api")
@RequestMapping("/api/v1/messages")
public class MessageApi {
	
	public static final Logger LOGGER = LoggerFactory.getLogger( MessageApi.class );
	
	
	private MessageService messageService;
	 public MessageApi(MessageService messageService) {
		this.messageService = messageService;
	}
	
	 @GetMapping("")
	 @ApiOperation(value="List messages")
	public ResponseEntity<MessageCollection> listerMessage(){
		 LOGGER.debug("Call lister messages()");
		return ResponseEntity.ok().body(new MessageCollection(messageService.findAll()));
	}
	 
	 @GetMapping("/{idMessage}")
	 @ApiOperation(value="Consulter message")
	public ResponseEntity<Message> consulterMessage(@PathVariable Integer idMessage ){
		 LOGGER.debug("Call lister messages()");
		return ResponseEntity.ok().body(messageService.findById(idMessage));
	}


}
