package com.goeuro.utils;

import com.goeuro.model.City;
import com.goeuro.model.GeoPosition;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nickc on 6/15/16.
 */
public class JsonUtils {


	public static List<City> parseCitiesFromJson(String json) throws ScriptException {
		List<City> cityList = new ArrayList<>();


		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");

		String script = "Java.asJSONCompatible(" + json + ")";
		List<Map> locations = (List) engine.eval(script);

		if (locations != null && locations.size() > 0) {
			cityList = extractCities(locations);
		}


		return cityList;
	}

	private static List<City> extractCities(List<Map> locations) {

		List<City> result = new ArrayList();
		City city;
		GeoPosition geoPosition;
		Map gp;

		for (Map location : locations) {
			city = new City();
			geoPosition = new GeoPosition();
			city.setId((Integer) location.get("_id"));
			city.setName((String) location.get("name"));
			city.setType((String) location.get("type"));

			gp = (Map) location.get("geo_position");
			geoPosition.setLatitude((Double) gp.get("latitude"));
			geoPosition.setLongitude((Double) gp.get("longitude"));

			city.setGeoPosition(geoPosition);

			result.add(city);
		}

		return result;
	}
}
