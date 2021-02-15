package com.mindtree.commonUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

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

	public static boolean verifyMail(String userName, String password, String message) {
		Folder folder = null;
		Store store = null;
		System.out.println("***READING MAILBOX...");
		try {
			Properties props = new Properties();
			props.put("mail.store.protocol", "imaps");
			Session session = Session.getInstance(props);
			store = session.getStore("imaps");
			store.connect("imap.outlook.com", userName, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
			System.err.println("TO : " + messages[0].getAllRecipients()[0]);
			for (int i = 0; i < messages.length; i++) {
				System.out.println("Reading MESSAGE # " + (i + 1) + "...");
				Message msg = messages[i];
				String strMailSubject = "", strMailBody = "";
				// Getting mail subject
				Object subject = msg.getSubject();
				// Getting mail body
				Object content = msg.getContent();
				// Casting objects of mail subject and body into String
				strMailSubject = (String) subject;
				//---- This is what you want to do------
				if (strMailSubject.contains(message)) {
					System.out.println(strMailSubject);
					break;
				}
			}
			return true;
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			if (folder != null) {
				try {
					folder.close(true);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (store != null) {
				try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
