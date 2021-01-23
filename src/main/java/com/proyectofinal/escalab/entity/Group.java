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

@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupId;
	
	@Column(name = "group_name", nullable = false, unique = true)
	@Size(min = 5, max= 20, message = "Group name must be present, greater than 5, less than 20")	
	private String groupName;

	@Column(name = "group_description", nullable = false)
	@Size(min = 15, max= 100, message = "Group description must be present, greater than 15, less than 100")	
	private String groupDescription;

	@Column(name = "group_photo", nullable = false)
	private String groupPhoto;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupPhoto() {
		return groupPhoto;
	}

	public void setGroupPhoto(String groupPhoto) {
		this.groupPhoto = groupPhoto;
	}
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "grupos_usuarios", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "groupId"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
//	private List<User> usersGroups;



}
