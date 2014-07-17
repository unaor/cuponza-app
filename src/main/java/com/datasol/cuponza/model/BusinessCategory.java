package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "business_categories",schema="cuponza")
public class BusinessCategory implements Serializable {


	private static final long serialVersionUID = -227546765483593290L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private Byte categoryId;
	@Column(name="description")
	private String description;
	@OneToMany(mappedBy="businessCategory",fetch=FetchType.LAZY)
	private Set<Business> businesses;
	
	public BusinessCategory(){}

	public Byte getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Byte categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(Set<Business> businesses) {
		this.businesses = businesses;
	}
}
