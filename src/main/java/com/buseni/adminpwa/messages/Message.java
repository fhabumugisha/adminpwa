package com.buseni.adminpwa.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	private String title;

	@Column(length = 255)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name="notification_type")
	private NotificationType notificationType;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation")
	private Date dateCreation;

	public Message(String title, String description, NotificationType notificationType, Date dateCreation) {
		super();
		this.title = title;
		this.description = description;
		this.notificationType = notificationType;
		this.dateCreation = dateCreation;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

}
