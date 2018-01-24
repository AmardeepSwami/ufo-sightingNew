package ufo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ufo.builder.UfoSightingBuilder;
import ufo.dto.UfoSighting;

public class UfoSightingUtil {
	/**
	 * get file oject that represent to ufo_awesome.tsv
	 * 
	 * @return File - file object that represent to ufo_awesome.tsv
	 */
	public static File getUfoSightingFile() {
		// Get file from resources folder
		ClassLoader classLoader = UfoSightingUtil.class.getClassLoader();
		File ufoSightingFile = new File(classLoader.getResource("ufo_awesome.tsv").getFile());
		return ufoSightingFile;
	}

	/**
	 * get list of Ufo Sightings from ufo_awesome.tsv file
	 * 
	 * @return File - file object that represent to ufo_awesome.tsv
	 */
	public static List<String> getUfoSightingsData() {
		List<String> sightingSTRList = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(getUfoSightingFile());
			bufferedReader = new BufferedReader(fileReader);
			String sightingstr;
			while ((sightingstr = bufferedReader.readLine()) != null) {
				sightingSTRList.add(sightingstr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (bufferedReader != null)
					bufferedReader.close();

				if (fileReader != null)
					fileReader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

		// LineIterator lineIterator = null;
		//
		// try {
		// lineIterator =
		// FileUtils.lineIterator(UfoSightingUtil.getUfoSightingFile(),
		// "UTF-8");
		// while (lineIterator.hasNext()) { // read TSV file line by line
		// String ufoSightingStr = lineIterator.nextLine();
		// sightingSTRList.add(ufoSightingStr);
		//
		// }
		// } catch (IOException e) {
		// e.printStackTrace(); // print IO exception
		// } finally {
		// LineIterator.closeQuietly(lineIterator); // close LineIterator
		// }
		return sightingSTRList;
	}

	/**
	 * process ufoSighting string and populate UfoSighting object
	 * 
	 * @param ufoSightingStr
	 * @return UfoSighting
	 */
	public static UfoSighting processUfoSightingInfo(String ufoSightingStr) {
		UfoSighting ufoSighting = null;
		UfoSightingBuilder builder = new UfoSightingBuilder();
		String[] strToken = ufoSightingStr.split("\t"); // split ufoSightingStr
														// with tab char
		if (strToken.length >= 6) {
			builder.setDateSeen(strToken[0]);
			builder.setDateReported(strToken[1]);
			builder.setPlaceSeen(strToken[2]);
			builder.setShape(strToken[3]);
			builder.setDuration(strToken[4]);
			String description = String.join("\t", Arrays.copyOfRange(strToken, 5, strToken.length));
			builder.setDescription(description);
			ufoSighting = builder.build();
		} else {
			System.out.println("Error in ufoSighting record: " + ufoSightingStr); // print
																					// errornous
																					// record
		}

		return ufoSighting;
	}

	/**
	 * Process list of ufoSighting string and populate UfoSighting objects
	 * 
	 * @param ufoSightingStrList
	 * @return List<UfoSighting>
	 */
	public static List<UfoSighting> processUfoSightingInfo(List<String> ufoSightingStrList) {
		UfoSighting ufoSighting = null;
		List<UfoSighting> ufoSightingList = new ArrayList<UfoSighting>();
		for (String ufoSightingStr : ufoSightingStrList) {
			String[] strToken = ufoSightingStr.split("\t"); // split
															// ufoSightingStr
			// with tab char
			if (strToken.length >= 6) {
				String dateSeen = strToken[0];
				String dateReported = strToken[1];
				String placeSeen = strToken[2];
				String shape = strToken[3];
				String duration = strToken[4];
				String description = String.join("\t", Arrays.copyOfRange(strToken, 5, strToken.length));
				
				ufoSighting = new UfoSighting(dateSeen, dateReported, placeSeen, shape, duration, description);
				ufoSightingList.add(ufoSighting);
			} else {
				System.out.println("Error in ufoSighting record: " + ufoSightingStr); // print
				// errornous
				// record
			}
		}

		return ufoSightingList;
	}
}
