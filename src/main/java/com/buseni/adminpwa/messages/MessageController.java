package com.buseni.adminpwa.messages;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buseni.adminpwa.webspush.WebPushMessage;
import com.buseni.adminpwa.webspush.WebPushSubscription;
import com.buseni.adminpwa.webspush.WebPushSubscriptionRepo;
import com.buseni.adminpwa.webspush.WebPushSubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private PushService pushService = new PushService();
	
	@Autowired
	private ObjectMapper objectMapper;	
	
	@Autowired 
	private WebPushSubscriptionService webPushSubscriptionService;

	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("messages", messageService.findAll());
		return "home";
	}
	
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("message", new Message());
		return "edit";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute Message message, RedirectAttributes ra) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		messageService.createMessage(message);
		ra.addFlashAttribute("info", "Question created successifully");
		
		WebPushMessage messageNotif = new WebPushMessage();
		messageNotif.setTitle("Bonjour!");
		messageNotif.setMessage("Un nouveau message vient d'être publié");
		messageNotif.setOpenUrl("/"+ message.getId());
		messageNotif.setMessageId(String.valueOf(message.getId()));
		for (WebPushSubscription subscription : webPushSubscriptionService.findAll()) {
			Notification notification = new Notification(subscription.getNotificationEndPoint(),
					subscription.getPublicKey(), subscription.getAuth(), objectMapper.writeValueAsBytes(messageNotif));

			pushService.send(notification);
		}
		return "redirect:/";
	}
	
	
	@ModelAttribute("notificationTypes")
	public List<NotificationType>  notificationTypes(){
		return Arrays.asList(NotificationType.values());
	}
	
	

}
