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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "state", nullable = false)
	private boolean enabled;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grupos_usuarios", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "groupId"))
	private List<Group> groupsUsers;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
	private List<Role> roles;
	
//	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "comments", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId"))
	private List<Post> userposts;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipient", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "message_id", referencedColumnName = "messageId"))
	private List<Message> userMessages;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getGroupsUsers() {
		return groupsUsers;
	}

	public void setGroupsUsers(List<Group> groupsUsers) {
		this.groupsUsers = groupsUsers;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public List<Post> getUserposts() {
		return userposts;
	}

	public void setUserposts(List<Post> userposts) {
		this.userposts = userposts;
	}

	public List<Message> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<Message> userMessages) {
		this.userMessages = userMessages;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	



}
