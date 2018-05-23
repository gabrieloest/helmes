package com.helmes.challenge.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "father_id", nullable = true, insertable = true, updatable = true)
	private Sector sectorFather;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Sector getSectorFather() {
		return sectorFather;
	}

	public void setSectorFather(Sector sectorFather) {
		this.sectorFather = sectorFather;
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
		return Objects.equals(description, tag.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

}
