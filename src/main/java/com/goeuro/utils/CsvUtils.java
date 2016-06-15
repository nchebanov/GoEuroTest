package com.goeuro.utils;

import com.goeuro.model.City;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by nickc on 6/15/16.
 */
public class CsvUtils {

	private static final String CSV_HEADER = "_id,name,type,latitude,longitude";


	public static void writeToCsvFile(String fileName, List<City> cityList) throws IOException {

		try (PrintWriter writer = new PrintWriter(new File(fileName))) {
			writer.println(CSV_HEADER);

			for (City city : cityList) {
				writer.println(cityToCsv(city));
			}
		}


	}


	private static String cityToCsv(City city) {
		return city.getId() + "," + city.getName() + "," + city.getType() + ","
				+ city.getGeoPosition().getLatitude() + "," + city.getGeoPosition().getLongitude();
	}

	;
}
