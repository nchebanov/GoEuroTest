package com.goeuro;

import com.goeuro.model.City;
import com.goeuro.utils.CsvUtils;
import com.goeuro.utils.HttpHelper;
import com.goeuro.utils.JsonUtils;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

public class GoEuro {

	public static void main(String[] args) {

		// Validate input
		if (args.length != 1) {
			System.out.println("Usage: java -jar GoEuroTest.jar \"CITY_NAME\"");
			return;
		}

		new GoEuro().execute(args[0]);
	}

	private void execute(String cityName){

		try {
			String citiesJSON = HttpHelper.queryCities(cityName);

			List<City> cityList = JsonUtils.parseCitiesFromJson(citiesJSON);

			CsvUtils.writeToCsvFile(cityName + ".csv", cityList);

		}catch (IOException | ScriptException ex){
			ex.printStackTrace();
		}

	}
}
