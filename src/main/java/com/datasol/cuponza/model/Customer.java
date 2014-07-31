package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "customers",schema="cuponza")
public class Customer implements Serializable {

	private static final long serialVersionUID = -5398700824491036678L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private Integer customerId;
	@Expose
	@Column(name="first_name")
	@NotNull(message="{form.customer.firstName.empty}")
	@Size(min=2,max=50,message="{user.validation.first.name.not.valid}")
	private String firstName;
	@Expose
	@Column(name="last_name")
	@NotNull
	@Size(min=2,max=50)
	private String lastName;
	@Expose
	@Column(name="join_date")
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date joinDate;
	@Expose
	@Column(name="contact_email",unique=true)
	@NotNull
	@Email
	private String contactEmail;
	@Expose
	@Column(name="land_phone")
	private String landPhone;
	@Expose
	@Column(name="mobile_phone")
	@NotNull
	private String mobilePhone;
	@Expose
	@OneToMany(mappedBy="customer",targetEntity=Business.class,
            fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Business> businesses;
	//TODO: change fetch type to lazy
	@Column(name = "password")
	@Size(min = 6, max = 20)
	private String password;
	@Transient
	private String passwordConfirmation;
	
	public Customer(){}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getLandPhone() {
		return landPhone;
	}

	public void setLandPhone(String landPhone) {
		this.landPhone = landPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Set<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(Set<Business> businesses) {
		this.businesses = businesses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
