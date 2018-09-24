package com.helmes.challenge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.ObjectError;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private long id;

	@NotBlank(message = "Name could not be blank!")
	@Column(name = "name", nullable = false)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_id")
	@NotNull(message = "Subscription can not be null!")
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

	public List<ObjectError> validate() {
		List<ObjectError> erros = new ArrayList<>();

		if (subscription.getSectors().isEmpty()) {
			erros.add(new ObjectError("subscription.sectors", "You need to select at least one sector!"));
		}

		if (!subscription.isTermAgreement()) {
			erros.add(new ObjectError("subscription.termAgreement", "You need to accept the agreement term!"));
		}

		return erros;
	}

}
