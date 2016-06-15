package com.goeuro;

import java.io.File;

public class GoEuroTest {

	public static void main(String[] args) {

		testValidData();

		testWrongData();
	}


	private static void testValidData() {

		File file = new File("Berlin.csv");

		if(file.exists()){
			file.delete();
		}

		GoEuro.main(new String[]{"Berlin"});

		file = new File("Berlin.csv");

		assert(file.exists());

	}

	private static void testWrongData() {

		GoEuro.main(new String[]{"ver&..\\&"});

		File file = new File("ver&..\\&.csv");

		assert(!file.exists());
	}


}
