package com.helmes.challenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}
		return id == ((User) o).getId();
	}

	@Override
	public int hashCode() {
		return 31;
	}

}
