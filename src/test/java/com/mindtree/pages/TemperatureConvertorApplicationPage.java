package com.mindtree.pages;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.commonUtils.CommonUtils;

import junit.framework.Assert;

public class TemperatureConvertorApplicationPage {

	WebDriver webDriver = null;
	CommonUtils objCommonUtils = new CommonUtils();
	static Logger log = Logger.getLogger(TemperatureConvertorApplicationPage.class.getName());
	
	// Creating webelement and getters
	@FindBy(how = How.XPATH, using = "//h1[text()='Celsius to Fahrenheit conversion']")
	private WebElement pageTitle;

	@FindBy(how = How.XPATH, using = "(//a[contains(text(),'RapidTables')])[1]")
	private WebElement rapidTablesLink;

	@FindBy(how = How.CSS, using = "input#x.intext")
	private WebElement celsiusInputBox;

	@FindBy(how = How.CSS, using = "input.outtext")
	private WebElement fahrenheitInputBox;

	@FindBy(how = How.XPATH, using = "//button[@title=\"Convert\"]")
	private WebElement convertBtn;
	
	/**
	 * @return the convertBtn
	 */
	public WebElement getConvertBtn() {
		return convertBtn;
	}
		
	/**
	 * @return the celsiusInputBox
	 */
	public WebElement getCelsiusInputBox() {
		return celsiusInputBox;
	}

	/**
	 * @return the fahrenheitInputBox
	 */
	public WebElement getFahrenheitInputBox() {
		return fahrenheitInputBox;
	}

	/**
	 * @return the rapidTablesLink
	 */
	public WebElement getRapidTablesLink() {
		return rapidTablesLink;
	}

	/**
	 * @return the pageTitle
	 */
	public WebElement getPageTitle() {
		return pageTitle;
	}

	/**
	 * class constructor to initialize the driver instance and webelement using pagefactory
	 * @param webDriver
	 */
	public TemperatureConvertorApplicationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	/**
	 * to launching the application
	 * @param url
	 */
	public void lanuch_Application(String url) {
		webDriver.get(url);
		log.info(url +" launch");
	}

	/**
	 * to verify application contains the desired doc string
	 * @param docString
	 * @throws IOException
	 */
	public void page_should_contains_title(String docString) throws IOException {
		String pageContent = getRapidTablesLink().getText() + "\n" + getPageTitle().getText();
		assertEquals(docString, pageContent);
		
		log.info(docString+" is verified");
		objCommonUtils.captureScreenShot(webDriver, "docString");
	}

	/**
	 * To enter the celsius value
	 * @param celsiusValue
	 * @throws IOException
	 */
	public void enterCelsiusValue(double celsiusValue) throws IOException {
		objCommonUtils.enterInput(getCelsiusInputBox(), celsiusValue+"");
		
		log.info(celsiusValue+" value entered");
		objCommonUtils.captureScreenShot(webDriver, getCelsiusInputBox().getAttribute("name"));
	}
	
	/**
	 * to click on convert button
	 */
	public void click_on_convert_button() {
		if(getConvertBtn().isEnabled()) {
			getConvertBtn().click();
			log.info("convert button clicked");
		}
	}
	
	/**
	 * @author Bhagwat
	 * @param fah
	 * @throws IOException 
	 * to check the output is correct after converting the values
	 */
	public void check_in_output_box(double expectedFahValue) throws IOException {
		if(getFahrenheitInputBox().isDisplayed()) {
			String getFahValue = getFahrenheitInputBox().getAttribute("value");
			System.err.println(getFahValue);
			double actualFahValue = Double.parseDouble(getFahValue);
			Assert.assertTrue(expectedFahValue == actualFahValue);
			log.info(expectedFahValue+" value found in the eoutput");
			objCommonUtils.captureScreenShot(webDriver, getFahrenheitInputBox().getAttribute("name"));
		}
	}
}
