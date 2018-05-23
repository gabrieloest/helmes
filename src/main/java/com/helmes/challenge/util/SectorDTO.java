package com.helmes.challenge.util;

import java.util.Objects;

public class SectorDTO {

	private int id;
	private String description;
	private SectorDTO father;
	private int level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SectorDTO getFather() {
		return father;
	}

	public void setFather(SectorDTO father) {
		this.father = father;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		String id = Objects.nonNull(getFather()) ? String.valueOf(getFather().getId()) : null;
		String sql = String.format("INSERT INTO sectors (id, description, father_id) VALUES (%s, '%s', %s);", getId(),
				getDescription(), id);
		return sql;
	}
}
