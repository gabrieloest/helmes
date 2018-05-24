package com.helmes.challenge.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

/**
 * This is about <code>GenerateDatabase</code>.
 * {@link com.helmes.challenge.util.GenerateDatabase}
 *
 * @author Gabriel Oest
 *
 *         This class is responsable to read the index.html and, for each
 *         <option> tag, generate the insert query in the table sectors.
 */
public class GenerateDatabase {

	public static void main(String[] args) {
		GenerateDatabase.generateDTOs();
	}

	public static void generateDTOs() {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("index.html");
			br = new BufferedReader(fr);
			String sCurrentLine;
			List<SectorDTO> sectors = new ArrayList<>();

			int lastLevel = 0;
			SectorDTO father = null;
			SectorDTO lastSector = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("option")) {
					int levelCount = GenerateDatabase.countLevel(sCurrentLine);

					int id = GenerateDatabase.recoveryId(sCurrentLine);
					String description = GenerateDatabase.recoveryDescription(sCurrentLine);

					SectorDTO sectorDTO = new SectorDTO();
					sectorDTO.setId(id);
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

	public static int countLevel(String sCurrentLine) {
		return StringUtils.countMatches(sCurrentLine, "&nbsp;");
	}

	private static String recoveryDescription(String sCurrentLine) {
		String description = StringUtils.substringBetween(sCurrentLine, "\">", "</").replaceAll("&nbsp;", "");
		return description;
	}

	private static int recoveryId(String sCurrentLine) {
		String id = StringUtils.substringBetween(sCurrentLine, "\"");
		return Integer.valueOf(id);
	}

}
