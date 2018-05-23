package com.helmes.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.helmes.challenge.model.Sector;
import com.helmes.challenge.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService {
	@Autowired
	private SectorRepository sectorRepository;

	@Override
	public Sector createSector(Sector sector) {
		return this.sectorRepository.save(sector);
	}

	@Override
	public Optional<Sector> getSector(Long id) {
		return this.sectorRepository.findById(id);
	}

	@Override
	public Sector editSector(Sector sector) {
		return this.sectorRepository.save(sector);
	}

	@Override
	public void deleteSector(Sector sector) {
		this.sectorRepository.delete(sector);
	}

	@Override
	public void deleteSector(Long id) {
		this.sectorRepository.deleteById(id);
	}

	@Override
	public List<Sector> getAllSectors(int pageNumber, int pageSize) {
		return this.sectorRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Sector> getAllSectors() {
		return this.sectorRepository.findAll();
	}
}