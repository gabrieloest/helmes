package com.helmes.challenge.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

public class GenerateDatabase {

	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("src/main/resources/templates/index.html");
			br = new BufferedReader(fr);
			String sCurrentLine;
			List<SectorDTO> sectors = new ArrayList<>();

			int countMatches = 0;
			SectorDTO father = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("option")) {
					int auxCount = StringUtils.countMatches(sCurrentLine, "&nbsp;");

					System.out.println(countMatches);
					System.out.println(sCurrentLine);

					String id = GenerateDatabase.recoveryId(sCurrentLine);
					String description = GenerateDatabase.recoveryDescription(sCurrentLine);

					SectorDTO sectorDTO = new SectorDTO();
					sectorDTO.setId(Integer.valueOf(id));
					sectorDTO.setDescription(description);

					if (Objects.isNull(father)) {
						father = sectorDTO;
					}

					if (auxCount > countMatches) {
						sectorDTO.setFather(sectorDTO);
						countMatches = auxCount;
					}

					if (auxCount < countMatches) {
						father = sectorDTO;
					}

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
		System.out.println(description);
		return description;
	}

	private static String recoveryId(String sCurrentLine) {
		String id = StringUtils.substringBetween(sCurrentLine, "\"");
		System.out.println(id);
		return id;
	}

}
