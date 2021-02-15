package com.mindtree.pages;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class Screenshot {  
	   private static int scrollTimeout = 0;  
	   public Screenshot(int timeout) {  
	     scrollTimeout = timeout;  
	   }  
	   private static String getFullHeight(WebDriver driver) {  
	     JavascriptExecutor js = (JavascriptExecutor) driver;  
	     return js.executeScript("return document.body.scrollHeight").toString();  
	   }  
	   private static int getFullWidth(WebDriver driver) {  
	     JavascriptExecutor js = (JavascriptExecutor) driver;  
	     return ((Long) js.executeScript("return window.innerWidth",  
	         new Object[0])).intValue();  
	   }  
	   private static int getWindowHeight(WebDriver driver) {  
	     JavascriptExecutor js = (JavascriptExecutor) driver;  
	     return ((Long) js.executeScript("return window.innerHeight",  
	         new Object[0])).intValue();  
	   }  
	   private static void waitForScrolling() {  
	     try {  
	       Thread.sleep(scrollTimeout);  
	     } catch (InterruptedException ignored) {  
	     }  
	   }  
	   private static BufferedImage getScreenshotNative(WebDriver wd) {  
	     ByteArrayInputStream imageArrayStream = null;  
	     TakesScreenshot takesScreenshot = (TakesScreenshot) new Augmenter().augment(wd);  
	     try {  
	       imageArrayStream = new ByteArrayInputStream(takesScreenshot.getScreenshotAs(OutputType.BYTES));  
	       return ImageIO.read(imageArrayStream);  
	     } catch (IOException e) {  
	       throw new RuntimeException("Can not parse screenshot data", e);  
	     } finally {  
	       try {  
	         if (imageArrayStream != null) {  
	           imageArrayStream.close();  
	         }  
	       } catch (IOException ignored) {  
	       }  
	     }  
	   }  
	   public static BufferedImage getScreenshot(WebDriver wd) {  
	     JavascriptExecutor js = (JavascriptExecutor) wd;  
	     int allH = Integer.parseInt(getFullHeight(wd));  
	     int allW = getFullWidth(wd);  
	     int winH = getWindowHeight(wd);  
	     int scrollTimes = allH / winH;  
	     int tail = allH - winH * scrollTimes;  
	     BufferedImage finalImage = new BufferedImage(allW, allH, BufferedImage.TYPE_4BYTE_ABGR);  
	     Graphics2D graphics = finalImage.createGraphics();  
	     for (int n = 0; n < scrollTimes; n++) {  
	       js.executeScript("scrollTo(0, arguments[0])", winH * n);  
	       waitForScrolling();  
	       BufferedImage part = getScreenshotNative(wd);  
	       graphics.drawImage(part, 0, n * winH, null);  
	     }  
	     if (tail > 0) {  
	       js.executeScript("scrollTo(0, document.body.scrollHeight)");  
	       waitForScrolling();  
	       BufferedImage last = getScreenshotNative(wd);  
	       BufferedImage tailImage = last.getSubimage(0, last.getHeight() - tail, last.getWidth(), tail);  
	       graphics.drawImage(tailImage, 0, scrollTimes * winH, null);  
	     }  
	     graphics.dispose();  
	     return finalImage;  
	   }  
	   public static void EShot(WebDriver wd, String filename) {  
	     try {  
	       ImageIO.write(getScreenshot(wd), "PNG", new File(filename));  
	     } catch (IOException e) {  
	       System.out.println(e);  
	     }  
	   }  
	   public static void main (String argc[]) throws InterruptedException{  
		   System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/" + "Drivers/" + "chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
	
	     Thread.sleep(5000);
	     driver.get("http://automationtesting.in/take-full-page-screenshot-using-shutterbug/");  
	     Thread.sleep(5000);
	    //Screenshot.EShot(driver, "d:/test" + ".png");
	     //Thread.sleep(5000);
	    // Shutterbug.shootPage(driver,ScrollStrategy.BOTH_DIRECTIONS).save("D:\\testing\\screenshots\\");
	     Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME,5000,true).save("D:\\testing\\screenshots\\s\\");
	      driver.quit();  
	   }
}