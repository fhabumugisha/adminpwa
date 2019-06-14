package com.buseni.adminpwa.webspush;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class WebPushSubscriptionServiceImpl implements WebPushSubscriptionService {

	
	 
	private WebPushSubscriptionRepo webPushSubscriptionRepo;
	
	public WebPushSubscriptionServiceImpl(WebPushSubscriptionRepo webPushSubscriptionRepo) {
		this.webPushSubscriptionRepo = webPushSubscriptionRepo;
	}

	@Override
	@Transactional
	public void subscribe(WebPushSubscription webPushSubscription) {
		webPushSubscriptionRepo.save(webPushSubscription);

	}

	@Override
	@Transactional
	public void unsubscribe(String subscriptionEndpoint) {
		if(!StringUtils.isEmpty(subscriptionEndpoint)) {
			webPushSubscriptionRepo.deleteByNotificationEndPoint(subscriptionEndpoint);
		}

	}

	@Override
	public List<WebPushSubscription> findAll() {
		return webPushSubscriptionRepo.findAll();
	}

}
