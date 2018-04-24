package com.test.auto.uiAuto.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;
	public Properties or;

	public void init() {
		loadData();
		String log4jPath = "log4j.properties";
		PropertyConfigurator.configure(log4jPath);
		openBrowser(or.getProperty("browser"));
		openUrl(or.getProperty("url"));

	}

	public void openBrowser(String browser) {
		log("To initiate browser");
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		log("Browser initiated");
	}

	public void loadData() {
		try {
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\test\\auto\\uiAuto\\properties\\config.properties"));
			or = new Properties();
			or.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void openUrl(String url) {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		log("to open url : " + url);
		driver.get(url);
		log("url opened");

		log("To maximize window");
		driver.manage().window().maximize();
		log("window maximized");

	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	public void close_browser() {
		driver.close();
	}

}
