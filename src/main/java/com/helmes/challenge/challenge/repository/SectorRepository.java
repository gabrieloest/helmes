package com.helmes.challenge.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helmes.challenge.challenge.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}
