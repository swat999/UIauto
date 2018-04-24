package com.test.auto.uiAuto.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.auto.uiAuto.testBase.TestBase;

public class Listener extends TestBase implements ITestListener {

	public static final Logger log = Logger.getLogger(Listener.class.getName());

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {


		if (result.isSuccess()) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");

			String methodName = result.getName();

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {

				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "\\src\\main\\java\\com\\test\\auto\\uiAuto\\";
				File destFile = new File((String) reportDirectory + "\\\\scrennShots_pass\\" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".png");

				FileUtils.copyFile(srcFile, destFile);

				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
						+ "' height='100' width='100'/> </a>");

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	

	}

	public void onTestFailure(ITestResult result) {

		if (!result.isSuccess()) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");

			String methodName = result.getName();

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {

				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "\\src\\main\\java\\com\\test\\auto\\uiAuto\\";
				File destFile = new File((String) reportDirectory + "\\scrennShots_failed\\" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".png");

				FileUtils.copyFile(srcFile, destFile);

				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
						+ "' height='100' width='100'/> </a>");

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		log("Test started in listner : " + context.getName());
	}

	public void onFinish(ITestContext context) {

		log("Test eneded in listner : " + context.getName());
	}

}
