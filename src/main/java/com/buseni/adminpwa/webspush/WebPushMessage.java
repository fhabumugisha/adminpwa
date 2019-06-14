package com.buseni.adminpwa.webspush;

public class WebPushMessage {
	
	public String title;
	public String openUrl;
	public String message;
	public String messageId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOpenUrl() {
		return openUrl;
	}
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}