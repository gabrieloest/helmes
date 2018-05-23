package com.helmes.challenge.challenge;

public class SectorDTO {

	private int id;
	private String description;
	private SectorDTO father;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SectorDTO getFather() {
		return this.father;
	}

	public void setFather(SectorDTO father) {
		this.father = father;
	}

}
