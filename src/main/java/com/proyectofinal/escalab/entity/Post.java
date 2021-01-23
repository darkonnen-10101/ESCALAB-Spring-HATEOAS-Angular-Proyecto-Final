package com.proyectofinal.escalab.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Posteos del usuario")
@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@ApiModelProperty(notes = "Debe estar presente, tener mínimo 1 y máximo 200 carácteres")
	@Column(name = "post_text", nullable = false)
	@Size(min = 1, max= 200, message = "text must be present, min 1 max 200")	
	private String postText;
	
	@JsonBackReference
	@ApiModelProperty(notes = "Relación n..1 de Post a AppUser")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser postCreator;

	@JsonBackReference
	@ApiModelProperty(notes = "Relación n..n de Post a Tag")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "posts_tags", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId"))
	private List<Tag> postTags;
	
	@JsonBackReference
	@ApiModelProperty(notes = "Relación n..n de Post a User a través de comments")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "comments", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
	private List<AppUser> userComments;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public AppUser getPostCreator() {
		return postCreator;
	}

	public void setPostCreator(AppUser postCreator) {
		this.postCreator = postCreator;
	}


	public List<Tag> getPostTags() {
		return postTags;
	}

	public void setPostTags(List<Tag> postTags) {
		this.postTags = postTags;
	}

	public List<AppUser> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<AppUser> userComments) {
		this.userComments = userComments;
	}
	
}
