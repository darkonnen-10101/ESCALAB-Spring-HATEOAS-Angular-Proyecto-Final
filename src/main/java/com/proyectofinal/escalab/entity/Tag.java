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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tags")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagId;
	
	@Column(name = "tag_name", nullable = false)
	@Size(min = 1, max= 15, message = "tagName must be present, min 1 max 15")	
	private String tagName;

	
	@ManyToMany(fetch = FetchType.LAZY) 
	@JsonIgnore
	@JoinTable(name = "posts_tags", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId"), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId"))
	private List<Post> tagPosts;


	public Integer getTagId() {
		return tagId;
	}


	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public List<Post> getTagPosts() {
		return tagPosts;
	}


	public void setTagPosts(List<Post> tagPosts) {
		this.tagPosts = tagPosts;
	}


}
