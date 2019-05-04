package com.mindtree.stepDefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mindtree.pages.TemperatureConvertorApplicationPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TemperatureConvertorApplicationStepDef {

	WebDriver driver;
	TemperatureConvertorApplicationPage objTemperatureConvertorApplicationPage;
	
	/**
	 * To setup the driver instance
	 * Used taged hooks
	 */
	@Before("@ConverterApplication")
	public void setUp() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "/src/test/resources/" + "Drivers/" + "geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		objTemperatureConvertorApplicationPage = new TemperatureConvertorApplicationPage(driver);
	}
	
	
	@Given("lanuch {string} Application")
	public void lanuch_Application(String url) {
		objTemperatureConvertorApplicationPage.lanuch_Application(url);
	}

	@Then("page should contains title")
	public void page_should_contains_title(String docString) throws IOException {
		objTemperatureConvertorApplicationPage.page_should_contains_title(docString);
	}

	@When("provide {double} value")
	public void provide_value(Double celsiusValue) throws IOException {
		objTemperatureConvertorApplicationPage.enterCelsiusValue(celsiusValue);
	}

	@When("click on convert button")
	public void click_on_convert_button() {
		objTemperatureConvertorApplicationPage.click_on_convert_button();
	}

	@Then("check {double} in output box")
	public void check_in_output_box(Double fah) throws IOException {
		objTemperatureConvertorApplicationPage.check_in_output_box(fah);
	}

	/**
	 * closing the driver instance
	 */
	@After("@ConverterApplication")
	public void tearDown() {
		driver.close();
	}
}
