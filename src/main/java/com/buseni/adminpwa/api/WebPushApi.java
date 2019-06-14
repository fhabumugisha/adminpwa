package com.buseni.adminpwa.api;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buseni.adminpwa.webspush.SubscriptionDto;
import com.buseni.adminpwa.webspush.WebPushMessage;
import com.buseni.adminpwa.webspush.WebPushSubscription;
import com.buseni.adminpwa.webspush.WebPushSubscriptionRepo;
import com.buseni.adminpwa.webspush.WebPushSubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@RestController
@CrossOrigin()
@Api(tags="Webspush  api")
@RequestMapping("api/v1/webspush")
public class WebPushApi {
	@Autowired
	private PushService pushService = new PushService();
	
	@Autowired
	private ObjectMapper objectMapper;	
	
	@Autowired 
	private WebPushSubscriptionService webPushSubscriptionService;

	
	 @ApiOperation(value="Subscribe to web push notifications")
	@PostMapping("/subscribe")
	 @CrossOrigin
	public void subscribe(@RequestBody SubscriptionDto subscriptionDto) {

		 if(null != subscriptionDto) {
		 WebPushSubscription subscription = new WebPushSubscription();
		 subscription.setNotificationEndPoint(subscriptionDto.getEndpoint());
		 subscription.setAuth(subscriptionDto.getKeys().get("auth"));
		 subscription.setPublicKey(subscriptionDto.getKeys().get("p256dh"));
		 webPushSubscriptionService.subscribe(subscription);
		 }
	}
	 @ApiOperation(value="Unsubscribe to web push notifications")
	@PostMapping("/unsubscribe")
	public void unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
		 if(null != subscriptionDto) {
			 webPushSubscriptionService.unsubscribe(subscriptionDto.getEndpoint()); 
		 }
		
	}

	@PostMapping("/notify-all")
	public WebPushMessage notifyAll(@RequestBody WebPushMessage message)
			throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {

		for (WebPushSubscription subscription : webPushSubscriptionService.findAll()) {

			Notification notification = new Notification(subscription.getNotificationEndPoint(),
					subscription.getPublicKey(), subscription.getAuth(), objectMapper.writeValueAsBytes(message));

			pushService.send(notification);
		}

		return message;
	}

}
