package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authorities", schema = "cuponza")
public class Authority implements Serializable {


	private static final long serialVersionUID = -726743464770443705L;
	//the @Secured annotation works only with ROLE_ by default
	public static final String NORMAL_USER_ROLE="ROLE_USER";
	public static final String PAYING_CUSTOMER="ROLE_CUSTOMER";
	public static final String SYS_ADMIN="ROLE_ADMIN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authority_id")
	private Long authorityId;

	@OneToMany(mappedBy = "authority", targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;
	
	@Column(name="authority")
	private String authorityName;
	
	public Authority(){}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
