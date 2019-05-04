package com.mindtree.pages;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Logger;

public class TemperatureConvertorPage {

	static double centValue;
	static double fahValue;
	String formattedVal;
	static Logger log = Logger.getLogger(TemperatureConvertorPage.class.getName());
	
	public void openConvertor(double centValue) {
		TemperatureConvertorPage.centValue = centValue;
		log.info("Convertor open");
	}

	/**
	 * To convert the cel into fah
	 */
	public void convertTemperature() {
		fahValue = (TemperatureConvertorPage.centValue * 1.8 + 32);

		NumberFormat formatter = new DecimalFormat("#0.0");
		formattedVal = formatter.format(fahValue);
		fahValue = Double.parseDouble(formattedVal);
		log.info("Temperature converted");
	}

	/**
	 * To check the output
	 * @param fahValue
	 */
	public void checkOutput(double fahValue) {
		if(fahValue == TemperatureConvertorPage.fahValue) {
			System.err.println("Centigrade to Fahrenheit converted successfully");
			log.info("Expected output found after converting the temperatur");
		}else {
			System.err.println("Centigrade to Fahrenheit not converted");
			log.info("Expected output not found after converting the temperatur");
		}
	}
}
