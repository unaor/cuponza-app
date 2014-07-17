package com.datasol.cuponza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coordinates",schema="cuponza")
public class Coordinate implements Serializable {


	private static final long serialVersionUID = -2772683084596351184L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="coordinate_id")
	private Integer coordinateId;
	@Column(name="longitude")
	@NotNull
	private Long longitude;
	@Column(name="longitude")
	@NotNull
	private Long latidude;
	@ManyToOne
    @JoinColumn(name = "business_id",updatable=true, insertable=true,nullable=false)
    private Business business;
	
	public Coordinate(){}

	public Integer getCoordinateId() {
		return coordinateId;
	}

	public void setCoordinateId(Integer coordinateId) {
		this.coordinateId = coordinateId;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatidude() {
		return latidude;
	}

	public void setLatidude(Long latidude) {
		this.latidude = latidude;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
}
