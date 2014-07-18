package com.datasol.cuponza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "coordinates",schema="cuponza")
public class Coordinate implements Serializable {


	private static final long serialVersionUID = -2772683084596351184L;
	
	@Id
    @GeneratedValue(generator="auto_increment")
    @GenericGenerator(
    		name="auto_increment", strategy="increment" ,
    			    parameters = {
    		        @Parameter(name="schema", value = "cuponza") } 
    )
	@Column(name="coordinate_id")
	private Integer coordinateId;
	@Column(name="longitude")
	@NotNull
	private Float longitude;
	@Column(name="latidude")
	@NotNull
	private Float latidude;
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

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
}
