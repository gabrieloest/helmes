package com.helmes.challenge.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.helmes.challenge.model.Sector;

public class SectorUtil {

	private static List<Sector> sectors;

	public static Set<Sector> sortSectors(List<Sector> listOfSectors) {
		SectorUtil.sectors = listOfSectors;
		Set<Sector> sortedSectors = new LinkedHashSet<>();
		List<Sector> firstLevelSectors = SectorUtil.sectors.stream().filter(it -> Objects.isNull(it.getSectorFather()))
				.collect(Collectors.toList());

		SectorUtil.fillSortedSectors(firstLevelSectors, sortedSectors);

		return sortedSectors;
	}

	private static void fillSortedSectors(List<Sector> levelSectors, Set<Sector> sortedSectors) {
		levelSectors.forEach(it -> {
			List<Sector> nodes = SectorUtil.sectors.stream().filter(sector -> {
				if (Objects.nonNull(sector.getSectorFather())) {
					return sector.getSectorFather().equals(it);
				}
				return false;
			}).collect(Collectors.toList());

			sortedSectors.add(it);

			if (!nodes.isEmpty()) {
				SectorUtil.fillSortedSectors(nodes, sortedSectors);
			}
		});
	}

	public static int getSectorLevel(Sector sector, int level) {
		if (Objects.nonNull(sector.getSectorFather())) {
			level = 4;
			level += SectorUtil.getSectorLevel(sector.getSectorFather(), level);
		}
		return level;
	}

}
