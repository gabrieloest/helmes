package com.helmes.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

public class GenerateDatabase {

	public static void main(String[] args) {
		generateDTOs();
	}

	public static void generateDTOs() {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("src/main/resources/templates/index.html");
			br = new BufferedReader(fr);
			String sCurrentLine;
			List<SectorDTO> sectors = new ArrayList<>();

			int lastLevel = 0;
			SectorDTO father = null;
			SectorDTO lastSector = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("option")) {
					int levelCount = StringUtils.countMatches(sCurrentLine, "&nbsp;");

					String id = GenerateDatabase.recoveryId(sCurrentLine);
					String description = GenerateDatabase.recoveryDescription(sCurrentLine);

					SectorDTO sectorDTO = new SectorDTO();
					sectorDTO.setId(Integer.valueOf(id));
					sectorDTO.setDescription(description);

					if (Objects.nonNull(father) && levelCount == 0) {
						father = null;
						lastSector = null;
						lastLevel = 0;
					}

					if (Objects.isNull(father)) {
						father = sectorDTO;
						lastSector = sectorDTO;
					}

					if (lastLevel < levelCount) {
						lastLevel = levelCount;
						father = lastSector;
					}

					if (lastLevel > levelCount) {
						lastLevel = levelCount;
						father = lastSector.getFather().getFather();
					}

					if (lastLevel == levelCount && sectorDTO.getId() != father.getId()) {
						sectorDTO.setFather(father);
					}

					lastLevel = levelCount;
					lastSector = sectorDTO;
					System.out.println(sectorDTO);
					sectors.add(sectorDTO);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static String recoveryDescription(String sCurrentLine) {
		String description = StringUtils.substringBetween(sCurrentLine, "\">", "</").replaceAll("&nbsp;", "");
		return description;
	}

	private static String recoveryId(String sCurrentLine) {
		String id = StringUtils.substringBetween(sCurrentLine, "\"");
		return id;
	}

}
