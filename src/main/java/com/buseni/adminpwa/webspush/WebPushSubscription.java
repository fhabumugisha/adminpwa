package com.buseni.adminpwa.webspush;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="web_push_subscription")
public class WebPushSubscription implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name="notification_end_point")
	private String notificationEndPoint;
	@Column(name="public_key")
	private String publicKey;
	private String auth;
	
    public String getNotificationEndPoint() {
		return notificationEndPoint;
	}
	public void setNotificationEndPoint(String notificationEndPoint) {
		this.notificationEndPoint = notificationEndPoint;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}		
}