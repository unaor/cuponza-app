package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "businesses",schema="cuponza")
public class Business implements Serializable {


	private static final long serialVersionUID = 8591440770344740103L;
	@Id
	@GeneratedValue(generator="auto_increment")
    @GenericGenerator(
    		name="auto_increment", strategy="increment" ,
    			    parameters = {
    		        @Parameter(name="schema", value = "cuponza") } 
    )
	@Column(name="business_id")
	private Integer businessId;
	@ManyToOne
    @JoinColumn(name="category_id")
	private BusinessCategory businessCategory;
	@Column(name="business_name")
	@NotNull
	@Size(min=1,max=20)
	private String businessName;
	@Column(name="land_phone")
	@Size(min=1,max=20)
	private String landPhone;
	@Column(name="mobile_phone")
	@Size(min=1,max=20)
	private String mobilePhone;
	@Column(name="virtual_business")
	private Boolean virtualBusiness;
	@Column(name="business_url")
	@Size(min=5,max=30)
	private String businessUrl;
	@Column(name="business_email")
	@Email
	private String businessEmail;
	@ManyToOne
    @JoinColumn(name = "customer_id",updatable=true, insertable=true,nullable=false)
	private Customer customer;
	@OneToMany(mappedBy="business",targetEntity=Cupon.class,
            fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Cupon> cupons;
	
	public Business(){}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public Boolean getVirtualBusiness() {
		return virtualBusiness;
	}

	public void setVirtualBusiness(Boolean virtualBusiness) {
		this.virtualBusiness = virtualBusiness;
	}

	public String getBusinessUrl() {
		return businessUrl;
	}

	public void setBusinessUrl(String businessUrl) {
		this.businessUrl = businessUrl;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Cupon> getCupons() {
		return cupons;
	}

	public void setCupons(Set<Cupon> cupons) {
		this.cupons = cupons;
	}
}
