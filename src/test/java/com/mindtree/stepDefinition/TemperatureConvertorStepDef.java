package com.mindtree.stepDefinition;

import java.io.IOException;

import com.mindtree.pages.TemperatureConvertorPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TemperatureConvertorStepDef {

	TemperatureConvertorPage objCentToFahPage = new TemperatureConvertorPage();
	
	@Given("open convertor and give {double}")
	public void open_convertor_and_give(double centValue) {
		objCentToFahPage.openConvertor(centValue);
	}

	@When("convert the Centigrade value into Fahrenheit")
	public void convert_the_centigrade_value_into_fahrenheit() throws IOException {
		objCentToFahPage.convertTemperature();
	}

	@Then("check {double} in output")
	public void checkConvertedValue(double fahValue) {
		objCentToFahPage.checkOutput(fahValue);
	}
}
