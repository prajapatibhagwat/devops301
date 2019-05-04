package com.mindtree.commonUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {

	/**
	 * @author Bhagwat
	 * @param element
	 * @param inputValue
	 */
	public void enterInput(WebElement element, String inputValue) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(inputValue);
		}
	}

	public void captureScreenShot(WebDriver driver, String name) throws IOException {

		File tempFile, desFile;

		desFile = new File(System.getProperty("user.dir") + "//images/" + name + "_" + getTime() + ".jpg");

		TakesScreenshot ts = (TakesScreenshot) driver;
		tempFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(tempFile, desFile);
	}

	public String getTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);

	}

}
