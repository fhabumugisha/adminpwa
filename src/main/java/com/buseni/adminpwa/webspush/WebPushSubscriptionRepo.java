package com.buseni.adminpwa.webspush;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPushSubscriptionRepo extends JpaRepository<WebPushSubscription, Integer> {
	
	void deleteByNotificationEndPoint(String notificationEndPoint);

}
