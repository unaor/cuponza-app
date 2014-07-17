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

@Entity
@Table(name = "cupons",schema="cuponza")
public class Cupon implements Serializable {


	private static final long serialVersionUID = 7670216491754097121L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cupon_id")
	private Integer cuponId;
	@Column(name="cupon_title")
	@NotNull
	@Size(min=5,max=30)
	private String  cuponTitle;
	@Column(name="cupon_description")
	@NotNull
	private String cuponDescription;
	@Column(name="cupon_value")
	private Double cuponValue;
	//TODO: think about units of measure like pesos/percentage etc
	@Column(name="creation_date")
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date creationDate;
	@Column(name="expiration_date")
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date expirationDate;
	@Column(name="picture_url")
	private String pictureURL;
	@Column(name="cupon_qty")
	private Integer cuponQuantity;
	@Column(name="cupon_active_state")
	private Boolean active;
	@ManyToOne
    @JoinColumn(name = "business_id",updatable=true, insertable=true,nullable=false)
	private Business business;
	@OneToOne(mappedBy="cupon", cascade=CascadeType.ALL)
	private CuponStatistic cuponStatistics;
	

}
