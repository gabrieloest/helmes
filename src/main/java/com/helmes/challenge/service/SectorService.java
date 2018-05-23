package com.helmes.challenge.service;

import java.util.List;
import java.util.Optional;

import com.helmes.challenge.model.Sector;

public interface SectorService {
	Sector createSector(Sector sector);

	Optional<Sector> getSector(Long id);

	Sector editSector(Sector sector);

	void deleteSector(Sector sector);

	void deleteSector(Long id);

	List<Sector> getAllSectors(int pageNumber, int pageSiz);

	List<Sector> getAllSectors();
}