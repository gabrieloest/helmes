package com.helmes.challenge.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;

@Entity
@Table(name = "subscriptions")
public class Subscription {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne(mappedBy = "subscription")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "subscription_sector", joinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sector_id", referencedColumnName = "id"))
	private Set<Sector> sectors;

	@AssertTrue
	private boolean termAgreement;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(Set<Sector> sectors) {
		this.sectors = sectors;
	}

	public boolean isTermAgreement() {
		return termAgreement;
	}

	public void setTermAgreement(boolean termAgreement) {
		this.termAgreement = termAgreement;
	}

}
