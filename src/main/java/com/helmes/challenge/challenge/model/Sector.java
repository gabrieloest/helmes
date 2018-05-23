package com.helmes.challenge.challenge.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sectors")
public class Sector {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Column(name = "description", nullable = false)
	private String description;

	@ManyToMany(mappedBy = "sectors")
	private Set<User> users = new HashSet<>();

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Sector tag = (Sector) o;
		return Objects.equals(this.description, tag.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.description);
	}

}
