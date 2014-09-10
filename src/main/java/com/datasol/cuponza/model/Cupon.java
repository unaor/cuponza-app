package com.datasol.cuponza.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "cupons",schema="cuponza")
public class Cupon implements Serializable {


	private static final long serialVersionUID = 7670216491754097121L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cupon_id")
	private Integer cuponId;
	@Expose
	@Column(name="cupon_title")
	@NotNull
	@Size(min=5,max=30)
	private String  cuponTitle;
	@Expose
	@Column(name="cupon_description")
	@NotNull
	private String cuponDescription;
	@Expose
	@Column(name="cupon_value")
	private Double cuponValue;
	//TODO: think about units of measure like pesos/percentage etc
	@Column(name="creation_date")
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date creationDate;
	@Column(name="expiration_date")
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date expirationDate;
	@Expose
	@Column(name="picture_url")
	private String pictureURL;
	@Column(name="cupon_qty")
	private Integer cuponQuantity;
	@Column(name="cupon_active_state")
	private Boolean active;
	@ManyToOne()
    @JoinColumn(name = "business_id",updatable=true, insertable=true,nullable=false)
	private Business business;
	@OneToOne(mappedBy="cupon", cascade=CascadeType.ALL)
	private CuponStatistic cuponStatistics;
	@Expose
	@Column(name="longitude")
	@NotNull
	private Float longitude;
	@Expose
	@Column(name="latidude")
	@NotNull
	private Float latidude;
	public Integer getCuponId() {
		return cuponId;
	}
	public void setCuponId(Integer cuponId) {
		this.cuponId = cuponId;
	}
	public String getCuponTitle() {
		return cuponTitle;
	}
	public void setCuponTitle(String cuponTitle) {
		this.cuponTitle = cuponTitle;
	}
	public String getCuponDescription() {
		return cuponDescription;
	}
	public void setCuponDescription(String cuponDescription) {
		this.cuponDescription = cuponDescription;
	}
	public Double getCuponValue() {
		return cuponValue;
	}
	public void setCuponValue(Double cuponValue) {
		this.cuponValue = cuponValue;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public Integer getCuponQuantity() {
		return cuponQuantity;
	}
	public void setCuponQuantity(Integer cuponQuantity) {
		this.cuponQuantity = cuponQuantity;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public CuponStatistic getCuponStatistics() {
		return cuponStatistics;
	}
	public void setCuponStatistics(CuponStatistic cuponStatistics) {
		this.cuponStatistics = cuponStatistics;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatidude() {
		return latidude;
	}
	public void setLatidude(Float latidude) {
		this.latidude = latidude;
	}
	
}
