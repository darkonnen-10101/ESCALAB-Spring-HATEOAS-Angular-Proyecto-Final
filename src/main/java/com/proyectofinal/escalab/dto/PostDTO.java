package com.proyectofinal.escalab.dto;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.proyectofinal.escalab.entity.AppUser;
import com.proyectofinal.escalab.entity.Tag;

public class PostDTO extends ResourceSupport{
	
	private Integer idPost;
	private AppUser user;
	private List<Tag> tags;
	
		
	public Integer getIdPost() {
		return idPost;
	}
	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
	

}
