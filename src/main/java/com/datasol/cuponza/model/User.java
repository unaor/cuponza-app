package com.datasol.cuponza.model;

import java.io.Serializable;
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

@Entity
@Table(name="users",schema="cuponza")
public class User implements Serializable {


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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
}
