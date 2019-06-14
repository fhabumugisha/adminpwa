package com.buseni.adminpwa.webspush;

import java.util.List;

public interface WebPushSubscriptionService {
	
	void subscribe(WebPushSubscription webPushSubscription);
	
	void unsubscribe(String subscriptionEndpoint);
	
	List<WebPushSubscription>  findAll();

}
