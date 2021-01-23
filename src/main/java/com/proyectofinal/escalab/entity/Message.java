package com.proyectofinal.escalab.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;

	@Column(name = "message_title", nullable = false)
	@Size(min = 1, max= 200, message = "messageTitle must be present, min 1 max 200")	
	private String messageTitle;

	@Column(name = "message_text", nullable = false)
	@Size(min = 1, max= 200, message = "messageText must be present, min 1 max 200")	
	private String messageText;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "reply_Id")
	private Message replyToMessageId;

	@ManyToMany(fetch = FetchType.LAZY) 
	@JoinTable(name = "recipient", joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "messageId"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
	private List<AppUser> messageUsers;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Message getReplyToMessageId() {
		return replyToMessageId;
	}

	public void setReplyToMessageId(Message replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
	}

	public List<AppUser> getMessageUsers() {
		return messageUsers;
	}

	public void setMessageUsers(List<AppUser> messageUsers) {
		this.messageUsers = messageUsers;
	}


}
