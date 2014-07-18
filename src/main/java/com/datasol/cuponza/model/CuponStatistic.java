package com.datasol.cuponza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cupons_stats",schema="cuponza")
public class CuponStatistic  implements Serializable {

	private static final long serialVersionUID = 37231413394380976L;
	
	@Id
	private Integer statisticsId;
	@OneToOne
    @PrimaryKeyJoinColumn
    private Cupon cupon;
	@Column(name="number_of_clicks")
	private Integer numberOfClicks;
	@Column(name="number_of_activations")
	private Integer numberOfActivations;
	@Column(name="number_of_emails")
	private Integer numberOfEmailReferels;
	@Column(name="number_of_tweets")
	private Integer numberOfTweets;
	@Column(name="number_of_likes")
	private Integer numberOfLikes;
	
	public CuponStatistic(){}

	public Cupon getCupon() {
		return cupon;
	}

	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}

	public Integer getNumberOfClicks() {
		return numberOfClicks;
	}

	public void setNumberOfClicks(Integer numberOfClicks) {
		this.numberOfClicks = numberOfClicks;
	}

	public Integer getNumberOfActivations() {
		return numberOfActivations;
	}

	public void setNumberOfActivations(Integer numberOfActivations) {
		this.numberOfActivations = numberOfActivations;
	}

	public Integer getNumberOfEmailReferels() {
		return numberOfEmailReferels;
	}

	public void setNumberOfEmailReferels(Integer numberOfEmailReferels) {
		this.numberOfEmailReferels = numberOfEmailReferels;
	}

	public Integer getNumberOfTweets() {
		return numberOfTweets;
	}

	public void setNumberOfTweets(Integer numberOfTweets) {
		this.numberOfTweets = numberOfTweets;
	}

	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
	public Integer getStatisticsId() {
		return statisticsId;
	}

	public void setStatisticsId(Integer statisticsId) {
		this.statisticsId = statisticsId;
	}
}
