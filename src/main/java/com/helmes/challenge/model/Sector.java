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

import org.apache.commons.lang.StringUtils;

import com.helmes.challenge.util.SectorUtil;

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
	private Set<Subscription> subscriptions = new HashSet<>();

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

	public String getDescriptionLevel() {
		String whitespace = "&nbsp;";
		int sectorLevel = SectorUtil.getSectorLevel(this, 0);

		return StringUtils.repeat(whitespace, sectorLevel) + description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
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
		return Objects.equals(id, tag.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description) + (int) id;
	}

}
