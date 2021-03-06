package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users",schema="cuponza")
public class User implements Serializable,UserDetails {


	private static final long serialVersionUID = -6523950973933274851L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "first_name")
	@NotNull
	@Size(min = 2, max = 20, message = "{user.validation.first.name.not.valid}")
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	@Size(min = 2, max = 20)
	private String lastName;
	@Column(name = "username", unique = true)
	@NotNull
	@Email
	private String email;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "password")
	@Size(min = 6)
	private String password;
	@DateTimeFormat(pattern = "dd/MM/YY")
	private Date registrationDate;
	@ManyToOne
	@JoinColumn(name = "authority_id", updatable = true, insertable = true, nullable = true)
	private Authority authority;
	@Transient
	private String passwordConfirmation;
	@Column(name="uuid")
	private String uuid;
	@Column(name="last_login")
	@DateTimeFormat(pattern = "dd/MM/YY")
	private Date lastLoginDate;
	@Column(name="auth_provider")
	private String authProvider;
	public User(){}
	
	public User(String email){
		this.email=email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
